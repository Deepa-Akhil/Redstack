package com.enterprise.dataservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.entity.IMeta;
import com.enterprise.common.enums.ModeTypes;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.FileUploadSessionBean;
import com.enterprise.common.util.RandomUtils;
import com.enterprise.common.util.StringUtils;
import com.enterprise.dataservices.IFileUploadDataService;
import com.enterprise.domain.entity.AirCarrier;
import com.enterprise.domain.entity.Airport;
import com.enterprise.domain.entity.Branch;
import com.enterprise.domain.entity.CMF;
import com.enterprise.domain.entity.Carrier;
import com.enterprise.domain.entity.City;
import com.enterprise.domain.entity.Container;
import com.enterprise.domain.entity.ContainerType;
import com.enterprise.domain.entity.Country;
import com.enterprise.domain.entity.Currency;
import com.enterprise.domain.entity.ICarrier;
import com.enterprise.domain.entity.IPort;
import com.enterprise.domain.entity.Location;
import com.enterprise.domain.entity.LogisticsServiceProvider;
import com.enterprise.domain.entity.Order;
import com.enterprise.domain.entity.OrderExpedLine;
import com.enterprise.domain.entity.OrderLine;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.Port;
import com.enterprise.domain.entity.SeaCarrier;
import com.enterprise.domain.entity.Seaport;
import com.enterprise.domain.entity.Shipment;
import com.enterprise.domain.entity.ShipmentContainer;
import com.enterprise.domain.entity.User;
import com.enterprise.domain.entity.meta.Meta;
import com.enterprise.domain.entity.meta.OrderMeta;
import com.enterprise.user.DeepDiveUser;

@Repository
public class OrderFileUploadDataServiceImpl extends AbstractFileUploadFunction implements IFileUploadDataService {

	@Autowired
	protected SessionFactory sessionFactory;
	
	@Override
	protected Class<? extends Meta> getMetaClass() {
		return OrderMeta.class;
	}

	@Override
	public void processLine(String[] columnArr, FileUploadSessionBean bean,
			Session session) throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			DataUploadWarning {
		
		Map<Integer,IMeta> indexMap = bean.getIndexMap();
		//Map<Integer,ISubPkgMappingRule> ruleMap = bean.getRuleMap();
		Map<String, IEntity> modelMap = new HashMap<String, IEntity>();
		
		Order order = new Order();
		modelMap.put(SubTypes.Order.name(), order);
		Port orderOriginPort = new Port();
		modelMap.put(SubTypes.OrderOriginPort.name(), orderOriginPort);
		Port orderDestinationPort = new Port();
		modelMap.put(SubTypes.OrderDestinationPort.name(), orderDestinationPort);
		Currency currency = new Currency();
		modelMap.put(SubTypes.Currency.name(), currency);
		
		OrderLine orderLine = new OrderLine();
		modelMap.put(SubTypes.OrderLine.name(), orderLine);
		
		OrderExpedLine orderExpedLine = new OrderExpedLine();
		modelMap.put(SubTypes.OrderExpedLine.name(), orderExpedLine);
		Container container = new Container();
		modelMap.put(SubTypes.Container.name(), container);
		ContainerType containerType = new ContainerType();
		modelMap.put(SubTypes.ContainerType.name(), containerType);
		
		Shipment shipment = new Shipment();
		modelMap.put(SubTypes.Shipment.name(), shipment);
		City originCity = new City();
		modelMap.put(SubTypes.OriginCity.name(), originCity);
		City destinationCity = new City();
		modelMap.put(SubTypes.DestinationCity.name(), destinationCity);
		Country originCountry = new Country();
		modelMap.put(SubTypes.OriginCountry.name(), originCountry);
		Country destinationCountry = new Country();
		modelMap.put(SubTypes.DestinationCountry.name(), destinationCountry);
		LogisticsServiceProvider lsp = new LogisticsServiceProvider();
		modelMap.put(SubTypes.ServiceProvider.name(), lsp);
		Port originPort = new Port();
		modelMap.put(SubTypes.OriginPort.name(), originPort);
		Port destinationPort = new Port();
		modelMap.put(SubTypes.DestinationPort.name(), destinationPort);
		Branch originBranch = new Branch();
		modelMap.put(SubTypes.OriginBranch.name(), originBranch);
		Branch destinationBranch = new Branch();
		modelMap.put(SubTypes.DestinationBranch.name(), destinationBranch);
		CMF shipper = new CMF();
		modelMap.put(SubTypes.Shipper.name(), shipper);
		CMF consignee = new CMF();
		modelMap.put(SubTypes.Consignee.name(), consignee);
		Carrier carrier = new Carrier();
		modelMap.put(SubTypes.Carrier.name(), carrier);
		ShipmentContainer shipmentContainerObj = new ShipmentContainer();
		modelMap.put(SubTypes.ShipmentContainer.name(), shipmentContainerObj);
		for (int i = 0; i < columnArr.length; i++) {
			StringBuilder errorBuilder = new StringBuilder("Error on line [" + (bean.getCurrentLine() + 1) + "]: ");
			String columnValue = columnArr[i];
			IMeta meta = indexMap.get(new Integer(i));
			//long metaId = meta.getId();
			if (meta != null) {
				String entityClassName = meta.getEntityClass();
				Class<?> entityClass = Class.forName(entityClassName);
				String subTypeStr = meta.getSubType().getName();
				IEntity entity = modelMap.get(subTypeStr);
				if (entity != null) {
					String entitySetter = meta.getEntitySetter();
					Method setter = entityClass.getMethod(entitySetter, String.class);
					try {
						if(!(subTypeStr.equals("Shipment") && entitySetter.equals("setMode") && StringUtils.isEmpty(columnValue)))
							setter.invoke(entity, columnValue);
					} catch (InvocationTargetException e) {
						Throwable targetException = e.getTargetException();
						String messageLocal = null;
						if (targetException != null)
							messageLocal = targetException.getLocalizedMessage();
						else
							messageLocal = e.getLocalizedMessage();
						errorBuilder.append(StringUtils.isEmpty(messageLocal) 
								? "Could not set value [" + columnValue + "] as [" + meta.getColumnName() + "]."
								: messageLocal
							);
							throw new DataUploadWarning(errorBuilder.toString(), null);
					} catch (Throwable e) {
						String messageLocal = e.getLocalizedMessage();
						errorBuilder.append(StringUtils.isEmpty(messageLocal) 
							? "Could not set value [" + columnValue + "] as [" + meta.getColumnName() + "]."
							: messageLocal
						);
						throw new DataUploadWarning(errorBuilder.toString(), null);
					}
				}
			}
		}
		long packageId = bean.getPackageId();
		Package pkg = (Package) session.get(Package.class, packageId);
		pkg.setLastUpdated(new Date());
		long userId;
		if( SecurityContextHolder.getContext().getAuthentication() != null &&
				SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
			try {
				DeepDiveUser sessionUser = (DeepDiveUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				userId = sessionUser.getId();
			} catch (Exception e) {
				userId = bean.getUserId();
			}
		} else {
			userId = bean.getUserId();
		}
		User user = new User();
		user.setId(userId);
		pkg.setLastEditedUser(user);
		session.update(pkg);
		order.setPkg(pkg);
		shipment.setPkg(pkg);
		if (StringUtils.isEmpty(order.getOrderNumber()))
			throw new DataUploadException("No column of uploaded Order file is mapped to the required Order  " + 
					"number.", null);
		/*if (StringUtils.isEmpty(order.getBuyer()))
			throw new DataUploadException("No column of uploaded Order file is mapped to the required Buyer.", null);*/
		IPort origPort = this.loadPort(order, orderOriginPort, originCountry, session);
		order.setOrigPort(origPort);
		IPort destPort = this.loadPort(order, orderDestinationPort, destinationCountry, session);
		order.setOrigPort(destPort);
		
		Currency hibernateCurrency = this.loadCurrency(currency.getCurrencyCode(), session);
		if(null!=hibernateCurrency) 
			order.setCurrency(hibernateCurrency);
		
		Order hibernateOrder = (Order)Order.load(order.getOrderNumber(), session, packageId);
		if(null != hibernateOrder){
			order.setId(hibernateOrder.getId());
		}
		
		if (order.getId() == 0L)
			session.save(order);
		else session.merge(order);
		
		/*if (StringUtils.isEmpty(orderLine.getLineID()))
			return;*/
		if (StringUtils.isEmpty(orderLine.getItemRef1()))
			throw new DataUploadException("No column of uploaded Order file is mapped to the required Item  " + 
					"Ref 1.", null);
		
		orderLine.setOrderData(order);
		OrderLine hibernateOrderLine = (OrderLine)OrderLine.load(orderLine.getLineID(), session, order.getId());
		if(null != hibernateOrderLine){
			orderLine.setId(hibernateOrderLine.getId());
		} else {
			hibernateOrderLine = (OrderLine)OrderLine.loadUnique(orderLine.getItemRef1(), orderLine.getDescription() , session, order.getId());
			if(null != hibernateOrderLine){
				orderLine.setId(hibernateOrderLine.getId());
				orderLine.setLineID(hibernateOrderLine.getLineID());
			} else {
				orderLine.setLineID(RandomUtils.GenerateRandomNumber(10));
			}
		}
		
		if (orderLine.getId() == 0L)
			session.save(orderLine);
		else session.merge(orderLine);
		//session.save(orderLine);
		
		if(container.getContainerNumber()!=null && !container.getContainerNumber().equals("")){
			Container persistentContainer = this.loadContainer(container, session, bean.getCurrentLine()+1, shipment.getPkg().getId(), containerType);
			orderExpedLine.setContainer(persistentContainer);
		}
		orderExpedLine.setOrderLine(orderLine);
		//session.save(orderExpedLine);
		String shipmentNumber = orderExpedLine.getShipmentNumber();
		/*if (StringUtils.isEmpty(shipmentNumber))
			return;*/
		
		if(null!=shipmentNumber && !shipmentNumber.equals("") && null!=shipment.getMode() && !shipment.getMode().equals("")){
			shipment.setShipmentNumber(shipmentNumber);
			
			Shipment hibernateShipment = processShipmentData(shipment, session, bean, shipmentContainerObj, shipper, consignee, 
					originCity, destinationCity, originCountry, destinationCountry, originPort, destinationPort, 
					carrier, container,	originBranch, destinationBranch, lsp, containerType);
			/*OrderExpedLine hibernateExpedLine = (OrderExpedLine)OrderExpedLine.load(shipmentNumber, session, orderLine.getId());
			if(null != hibernateExpedLine) {
				orderExpedLine.setId(hibernateExpedLine.getId());
			}*/
			orderExpedLine.setShipment(hibernateShipment);
		}
		
		if (orderExpedLine.getId() == 0L)
			session.save(orderExpedLine);
		else session.merge(orderExpedLine);
		/*String hql = "UPDATE OrderLine set orderData.id = :orderId WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", orderLine.getId());
		query.setParameter("orderId", order.getId());
		query.executeUpdate();*/
		
		bean.getRateDetailIds().add(orderLine.getId());
		bean.getRateIds().add(orderExpedLine.getId());
	}

	private IPort loadPort(Order order, Location port, Country country, Session session) {
		ModeTypes mode = order.getMode();
		if(null!=mode) {
			switch (mode) {
				case O:
					Seaport seaport = null;
					String seaportUniqueCd = port.getUniqueCd();
					if (!StringUtils.isEmpty(seaportUniqueCd))
						seaport = Seaport.load(seaportUniqueCd, session);
					else {
						String countryCd = country.getCountryCd();
						String locCd = port.getLocCd();
						if(!StringUtils.isEmpty(locCd) && locCd.length()>=5 && StringUtils.isEmpty(countryCd)){
							countryCd = locCd.substring(0, 2);
							locCd = locCd.substring(2, locCd.length()).replaceAll("[^a-zA-Z0-9]", "");
							port.setLocCd(locCd);
							country.setCountryCd(countryCd);
						}
						seaport = Seaport.load(locCd, countryCd, session);
					}
					return seaport;
				default:
					Airport airport = null;
					String airportUniqueCd = port.getUniqueCd();
					if (!StringUtils.isEmpty(airportUniqueCd))
						airport = Airport.load(airportUniqueCd, session);
					else {
						String countryCd = country.getCountryCd();
						String locCd = port.getLocCd();
						if(!StringUtils.isEmpty(locCd) && locCd.length()>=5 && StringUtils.isEmpty(countryCd)){
							countryCd = locCd.substring(0, 2);
							locCd = locCd.substring(2, locCd.length()).replaceAll("[^a-zA-Z0-9]", "");
							port.setLocCd(locCd);
							country.setCountryCd(countryCd);
						}
						airport = Airport.load(locCd, countryCd, session);
					}
					return airport;
			}
		}
		return null;
	}
	
	private Currency loadCurrency(String currencyCd, Session session) {
		Currency currency = null;
		if (!StringUtils.isEmpty(currencyCd)) {
			Criteria criteria = session.createCriteria(Currency.class)
				.add(Restrictions.eq("currencyCode", currencyCd));
				currency = (Currency)criteria.uniqueResult();
		}
		return currency;
	}
	
	/*@SuppressWarnings("deprecation")
	private Container loadContainer(Container container, Session session, int currentLine, long packageId, ContainerType containerType) {
		Criteria criteria = session.createCriteria(Container.class)
			.add(Restrictions.eq("containerNumber", container.getContainerNumber()))
			.add(Restrictions.eq("type", containerType.getType()));
		@SuppressWarnings("unchecked")
		List<Container> list = criteria.list();
		Container entity = null;
		if(null != list && !list.isEmpty())
			entity = (Container)list.get(0);
		
		if (entity == null) {
			Criteria criteria1 = session.createCriteria(ContainerTypeAlias.class)
					.createAlias("container", "c")
					.createAlias("pkg", "p")
					.setFetchMode("container", FetchMode.EAGER)
					.setFetchMode("pkg", FetchMode.EAGER)
					.add(Restrictions.eq("c.containerNumber", container.getContainerNumber()))
					.add(Restrictions.eq("aliasTypeName", containerType.getType()))
					.add(Restrictions.eq("p.id", packageId));
			@SuppressWarnings("unchecked")
			List<ContainerTypeAlias> list1 = criteria1.list();
			if(!CollectionUtils.isEmpty(list1)){
				try {
					//entity = (Container)((ContainerTypeAlias)list1.get(0)).getContainerType();
					entity.setContainerDesc(container.getContainerDesc());
					session.update(entity);
				} catch (HibernateException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return entity;
			}
			if (!container.isEmpty()) {
				try {
					if(containerType.getType().length()>4){
						throw new DataUploadWarning("Error on line [" + currentLine + "] as [" + containerType.getType() + "] as the container type is invalid. " + 
								"The system expects a valid number that contains 4 characters(eg:40GP).", null);
					} else
					  session.save(container);
				}catch (ConstraintViolationException e) {
					e.printStackTrace();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
				return container;
			} else{
				return null;
			}
		} else {
			if (!container.isEmpty()) {
				try {
					entity.setContainerDesc(container.getContainerDesc());
					session.update(entity);
				}catch (ConstraintViolationException e) {
					e.printStackTrace();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
				return entity;
			} else{
				return null;
			}
		}
	}*/
	
	private Shipment processShipmentData(Shipment shipment, Session session, FileUploadSessionBean bean, 
			ShipmentContainer shipmentContainerObj, CMF shipper, CMF consignee, 
			City originCity, City destinationCity, Country originCountry, Country destinationCountry, 
			Port originPort, Port destinationPort, Carrier carrier, Container container,
			Branch originBranch, Branch destinationBranch, LogisticsServiceProvider lsp, ContainerType containerType) {
		if (shipment.getMode() == null)
			throw new DataUploadException("No column of uploaded Order file is mapped to the required Shipment  " + 
					"Mode.", null);
		String shipmentNumber = shipment.getShipmentNumber();
		if (StringUtils.isEmpty(shipmentNumber))
			throw new DataUploadException("No column of uploaded shipment file is mapped to the required shipment " + 
					"number.", null);
		else if (container.getContainerNumber() != null && !container.getContainerNumber().equals("") 
				&& (containerType.getType() == null || containerType.getType().equals("")) )
			throw new DataUploadException("No column of uploaded shipment file is mapped to the required Container " + 
					"Type.", null);
		long packageId = bean.getPackageId();
		Shipment hibernateShipment = (Shipment)Shipment.load(shipmentNumber, session, packageId, shipment.getMode());
		Package pkg = (Package) session.get(Package.class, packageId);
		shipment.setPkg(pkg);
		if (hibernateShipment != null) {
			shipment.setId(hibernateShipment.getId());
			shipment.setServiceProvider(hibernateShipment.getServiceProvider());
			shipment.setOrigCity(hibernateShipment.getOrigCity());
			shipment.setDestCity(hibernateShipment.getDestCity());
			Shipment.deleteOrphans(hibernateShipment, bean.getUuid(), session);
			shipment.setShipmentContainers(hibernateShipment.getShipmentContainers());
		}
		originCity = this.loadCity(originCity.getLocCd(), originCountry.getCountryCd(), session);
		shipment.setOrigCity(originCity);
		destinationCity = this.loadCity(destinationCity.getLocCd(), destinationCountry.getCountryCd(), session);
		shipment.setDestCity(destinationCity);
		IPort origPort = this.loadPort(shipment, originPort, originCountry, session);
		shipment.setOrigPort(origPort);
		IPort destPort = this.loadPort(shipment, destinationPort, destinationCountry, session);
		shipment.setDestPort(destPort);
		loadOriginandDestLocationdetails(originCountry, destinationCountry, originPort, destinationPort, shipment, bean, session);
		Branch origBranch = this.loadBranch(originBranch, originCountry, session);
		shipment.setOrigBranch(origBranch);
		Branch destBranch = this.loadBranch(destinationBranch, destinationCountry, session);
		shipment.setDestBranch(destBranch);
		lsp = this.loadServiceProvider(lsp, session);
		shipment.setServiceProvider(lsp);
		CMF shipperTemp = null;
		CMF consigneeTemp = null;
		if(!StringUtils.isEmpty(shipper.getClientCd()))
			shipperTemp = this.loadCMF(shipper, session);
		else {
			shipperTemp = this.loadAliasCMF(shipper, session);
		}
		shipment.setShipper(shipperTemp);
		if(!StringUtils.isEmpty(consignee.getClientCd()))
			consigneeTemp = this.loadCMF(consignee, session);
		else {
			consigneeTemp = this.loadAliasCMF(consignee, session);
		}
		shipment.setConsignee(consigneeTemp);
		ICarrier icarrier = null;
		if(!bean.isAliaseMatchingEnabled())
			icarrier = this.loadCarrier(shipment, carrier, session, bean.getCurrentLine());
		else
			icarrier = this.loadAliaseCarrier(shipment, carrier, session, packageId, bean);
		shipment.setCarrier(icarrier);
		//shipment.setLaneId(shipment.getMode() + "-" + originPort.getLocCd() + "-" + destinationPort.getLocCd());
		if(null!=consignee)
			shipment.setConsingeeAlias(consignee.getAlias());
		if(null!=shipper)
			shipment.setShipperAlias(shipper.getAlias());
		String laneId = null;
		String carrierID = null;
		if(shipment.getCarrier()!=null){
			carrierID = Carrier.getCarrierCd(session, shipment.getCarrier().getId());
		}
		if(shipment.getMode() == ModeTypes.A){
			laneId = shipment.getMode() + "-" + originCountry.getCountryCd() + "-" + originPort.getLocCd() + "-" + destinationCountry.getCountryCd() + "-" +destinationPort.getLocCd() + "-" + 
					shipment.getServiceLevel() + "-" + carrierID;
		}
		shipment.setLaneId(laneId);
		BigDecimal volumeShp = null, weightShp = null, noOfPeiecesShp = null, chargableWeightShp = null;
		if (shipmentContainerObj != null) {
			volumeShp = shipmentContainerObj.getVolume() == null ? new BigDecimal(0f) : shipmentContainerObj.getVolume();
			weightShp = shipmentContainerObj.getWeight() == null ? new BigDecimal(0f) : shipmentContainerObj.getWeight();
			noOfPeiecesShp = shipmentContainerObj.getNumberOfPieces() == null ? new BigDecimal(0f) : shipmentContainerObj.getNumberOfPieces();
			chargableWeightShp = shipmentContainerObj.getChargeableWeight() == null ? new BigDecimal(0f) : shipmentContainerObj.getChargeableWeight();
		}
		shipment.setVolume(volumeShp);
		shipment.setChargeableWeight(chargableWeightShp);
		shipment.setWeight(weightShp);
		shipment.setNumberOfPieces(noOfPeiecesShp);
		if (shipment.getId() == 0L)
			session.save(shipment);
		else session.merge(shipment);
		String containerNoAll = container.getContainerNumber();//added by Akhil for Managing more than one Shipment file
		if(!StringUtils.isEmpty(containerNoAll)){
			String[] containerNoArr = containerNoAll.split(",");
			if(null!=containerNoArr && containerNoArr.length>0 && containerNoArr.length == 1){
				String containerNType[] = containerNoAll.split(":");
				if(containerNType.length>1){
					if(!StringUtils.isEmpty(containerType.getType()) && containerType.getType().equals(containerNType[0].trim())){
						throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as the Container type mismatch found. [" + containerType.getType() + "!= "+containerNType[0].trim()+"]" + 
								"The system expects a single Container type mapping.", null);
					}
					container.setContainerNumber(containerNType[0].trim());
					containerType.setType(containerNType[1].trim());
				}
				String containerNType2[] = containerNoAll.split("\\(");
				if(containerNType2.length>1){
					String contType = containerNType2[1].replaceAll("\\)", "").trim();
					if(!StringUtils.isEmpty(containerType.getType()) && containerType.getType().equals(contType)){
						throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as the Container type mismatch found. [" + containerType.getType() + "!= "+containerNType[0].trim()+"]" + 
								"The system expects a single Container type mapping.", null);
					}
					container.setContainerNumber(containerNType2[0].trim());
					containerType.setType(contType);
				}
				ShipmentContainer shipmentContainer 
				= this.loadShipmentContainer(shipment, container, shipmentContainerObj, bean.getUuid(), session, bean.getCurrentLine()+1, containerType);
				if (shipmentContainer != null) {
					if(shipment.getMode() == ModeTypes.O){
						laneId = shipment.getMode() + "-" + originCountry.getCountryCd() + "-" + originPort.getLocCd() + "-" + 
								destinationCountry.getCountryCd() + "-" +destinationPort.getLocCd() + "-" + 
								Container.getContainerType(session, shipmentContainer.getContainer().getId()) + 
								"-" + carrierID;
					}
					shipmentContainer.setLaneId(laneId);
					shipment.getShipmentContainers().add(shipmentContainer);
					session.saveOrUpdate(shipmentContainer);
				}
				
				double volume = 0f, weight = 0f, noOfPeieces = 0f, chargableWeight = 0f;
				Set<Long> shipContIDs = new HashSet<Long>();
				for (ShipmentContainer shipmentContainerTemp : shipment.getShipmentContainers()) {
					if(!shipContIDs.contains(shipmentContainerTemp.getId())){
						volume += shipmentContainerTemp.getVolume() == null ? 0f : shipmentContainerTemp.getVolume().doubleValue() ;
						weight += shipmentContainerTemp.getWeight() == null ? 0f : shipmentContainerTemp.getWeight().doubleValue() ;
						noOfPeieces += shipmentContainerTemp.getNumberOfPieces() == null ? 0f : shipmentContainerTemp.getNumberOfPieces().doubleValue() ;
						chargableWeight += shipmentContainerTemp.getChargeableWeight() == null ? 0f : shipmentContainerTemp.getChargeableWeight().doubleValue() ;
						shipContIDs.add(shipmentContainerTemp.getId());
					}
				}
				shipment.setVolume(new BigDecimal(volume));
				shipment.setChargeableWeight(new BigDecimal(chargableWeight));
				shipment.setWeight(new BigDecimal(weight));
				shipment.setNumberOfPieces(new BigDecimal(noOfPeieces));
				session.merge(shipment);
			} else {
			    BigDecimal volume = null, weight = null, noOfPeieces = null, chargableWeight = null;
				if (shipmentContainerObj != null) {
					volume = shipmentContainerObj.getVolume() == null ? new BigDecimal(0f) : shipmentContainerObj.getVolume();
					weight = shipmentContainerObj.getWeight() == null ? new BigDecimal(0f) : shipmentContainerObj.getWeight();
					noOfPeieces = shipmentContainerObj.getNumberOfPieces() == null ? new BigDecimal(0f) : shipmentContainerObj.getNumberOfPieces();
					chargableWeight = shipmentContainerObj.getChargeableWeight() == null ? new BigDecimal(0f) : shipmentContainerObj.getChargeableWeight();
				}
				shipment.setVolume(volume);
				shipment.setChargeableWeight(chargableWeight);
				shipment.setWeight(weight);
				shipment.setNumberOfPieces(noOfPeieces);
				session.merge(shipment);
				int conLen = containerNoArr.length;
				double avgVol = volume.doubleValue()/conLen, avgWgt = weight.doubleValue()/conLen, avgNoPieces = noOfPeieces.doubleValue()/conLen, avgChrgWgt = chargableWeight.doubleValue()/conLen;
				for(String containerNo : containerNoArr){
					String containerNType[] = containerNo.split(":");
					String containerNType2[] = containerNo.split("\\(");
					if(containerNType.length>1){
						if(!StringUtils.isEmpty(containerType.getType()) && containerType.getType().equals(containerNType[0].trim())){
							throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as the Container type mismatch found. [" + containerType.getType() + "!= "+containerNType[0].trim()+"]" + 
									"The system expects a single Container type mapping.", null);
						}
						container.setContainerNumber(containerNType[0].trim());
						containerType.setType(containerNType[1].trim());
					}  else if(containerNType2.length>1){
						String contType = containerNType2[1].replaceAll("\\)", "").trim();
						if(!StringUtils.isEmpty(containerType.getType()) && containerType.getType().equals(contType)){
							throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as the Container type mismatch found. [" + containerType.getType() + "!= "+containerNType[0].trim()+"]" + 
									"The system expects a single Container type mapping.", null);
						}
						container.setContainerNumber(containerNType2[0].trim());
						containerType.setType(contType);
					} else {
						container.setContainerNumber(containerNo.trim());
					}
					container.setId(0L);
					container = this.loadContainer(container, session, bean.getCurrentLine()+1, shipment.getPkg().getId(), containerType);
					ShipmentContainer shipmentContainer 
						= this.loadShipmentContainer(shipment, container, shipmentContainerObj, bean.getUuid(), session, bean.getCurrentLine()+1, containerType);
					if (shipmentContainer != null) {
						if(shipment.getMode() == ModeTypes.O){
							laneId = shipment.getMode() + "-" + originCountry.getCountryCd() + "-" + originPort.getLocCd() + "-" + 
									destinationCountry.getCountryCd() + "-" +destinationPort.getLocCd() + "-" + 
									Container.getContainerType(session, shipmentContainer.getContainer().getId()) + 
									"-" + carrierID;
						}
						shipmentContainer.setLaneId(laneId);
						shipment.getShipmentContainers().add(shipmentContainer);
						shipmentContainer.setVolume(new BigDecimal(avgVol));
						shipmentContainer.setWeight(new BigDecimal(avgWgt));
						shipmentContainer.setNumberOfPieces(new BigDecimal(avgNoPieces));
						shipmentContainer.setChargeableWeight(new BigDecimal(avgChrgWgt));
						session.saveOrUpdate(shipmentContainer);
					}
				}
			}
		}
		return shipment;
	}
	
	private CMF loadCMF(CMF cmf, Session session) {
		Criteria criteria = session.createCriteria(CMF.class)
			.add(Restrictions.eq("clientCd", cmf.getClientCd()));
		CMF cmfEty = (CMF)criteria.uniqueResult();
		if (cmfEty == null) {
			session.save(cmf);
			return cmf;
		} else {
			cmfEty.setAlias(cmf.getAlias());
			return cmfEty;
		}
	}
	
	private CMF loadAliasCMF(CMF cmf, Session session) {
		Criteria criteria = session.createCriteria(CMF.class)
			.add(Restrictions.eq("searchAlias", cmf.getSearchAlias()));
		CMF cmfEty = null;
		if(criteria.list()!=null && !criteria.list().isEmpty()){
			cmfEty = (CMF)criteria.list().get(0);
			if (cmfEty == null) {
				//session.save(cmf);
			} else {
				cmfEty.setAlias(cmf.getAlias());
			}
		}
		return cmfEty;
	}
	
	private ICarrier loadAliaseCarrier(Shipment shipment, Carrier carrier, Session session, 
			long packageId, FileUploadSessionBean bean) {
		try {
			ModeTypes mode = shipment.getMode();
			String carrierCd = carrier.getCarrierCd();
			switch (mode) {
				case O:
					SeaCarrier seaCarrier = SeaCarrier.loadAlias(carrierCd, session, packageId);
					return seaCarrier;
				default:
					AirCarrier airCarrier = AirCarrier.loadAlias(carrierCd, session, packageId);
					return airCarrier;
			}
		} catch (Exception e) {
			/*throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as [" + carrier.getCarrierCd() + "] as the Carrier code is invalid. " + 
						"The system expects a valid Carrier code no longer than 4 characters", null);*/
			return null;
		}
	}
	
	private ShipmentContainer loadShipmentContainer(Shipment shipment, Container container, 
			ShipmentContainer shipmentContainerObj, UUID uuid, Session session, int currLine, ContainerType containerType) {
		//Session tempSess = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(ShipmentContainer.class)
			.createAlias("shipment", "s1")
			.add(Restrictions.eq("s1.shipmentNumber", shipment.getShipmentNumber()))
			.createAlias("container", "c1")
			.add(Restrictions.eq("c1.containerNumber", container.getContainerNumber()))
			.add(Restrictions.eq("uuid", uuid.toString()));
		ShipmentContainer shipmentContainer = (ShipmentContainer)criteria.uniqueResult();
		//tempSess.close();
		if (shipmentContainer == null) {
			Container persistentContainer = this.loadContainer(container, session, currLine, shipment.getPkg().getId(), containerType);
			if (persistentContainer != null) {
				shipmentContainer = new ShipmentContainer();
				shipmentContainer.setUuid(uuid.toString());
				shipmentContainer.setShipment(shipment);
				shipmentContainer.setContainer(persistentContainer);
			} else {
				/* this shipment file has no container details. return null to break the shipment/container relationship */
				return null;
			}
		} else
			shipmentContainer.setUuid(uuid.toString());
		shipmentContainer.setWeight(shipmentContainerObj.getWeight());
		shipmentContainer.setChargeableWeight(shipmentContainerObj.getChargeableWeight());
		shipmentContainer.setVolume(shipmentContainerObj.getVolume());
		shipmentContainer.setNumberOfPieces(shipmentContainerObj.getNumberOfPieces());
		//shipmentContainer.setShipmentType(shipmentContainerObj.getShipmentType());
		return shipmentContainer;
	}
	
	private IPort loadPort(Shipment shipment, Location port, Country country, Session session) {
		ModeTypes mode = shipment.getMode();
		switch (mode) {
			case O:
				Seaport seaport = null;
				String seaportUniqueCd = port.getUniqueCd();
				if (!StringUtils.isEmpty(seaportUniqueCd))
					seaport = Seaport.load(seaportUniqueCd, session);
				else {
					String countryCd = country.getCountryCd();
					String locCd = port.getLocCd();
					seaport = Seaport.load(locCd, countryCd, session);
				}
				return seaport;
			default:
				Airport airport = null;
				String airportUniqueCd = port.getUniqueCd();
				if (!StringUtils.isEmpty(airportUniqueCd))
					airport = Airport.load(airportUniqueCd, session);
				else {
					String countryCd = country.getCountryCd();
					String locCd = port.getLocCd();
					airport = Airport.load(locCd, countryCd, session);
				}
				return airport;
		}
	}
	
	private Branch loadBranch(Branch branch, Country country, Session session) {
		String uniqueCd = branch.getUniqueCd();
		if (!StringUtils.isEmpty(uniqueCd))
			return Branch.load(uniqueCd, session);
		else {
			String locCd = branch.getLocCd();
			String countryCd = country.getCountryCd();
			if(!StringUtils.isEmpty(locCd) && locCd.length()>=5 && StringUtils.isEmpty(countryCd)){
				countryCd = locCd.substring(0, 2);
				locCd = locCd.substring(2, locCd.length()).replaceAll("[^a-zA-Z0-9]", "");
				country.setCountryCd(countryCd);
				branch.setLocCd(locCd);
			}
			return Branch.load(locCd, countryCd, session);
		}
	}
	
	private ICarrier loadCarrier(Shipment shipment, Carrier carrier, Session session, int currentLine) {
		try {
			ModeTypes mode = shipment.getMode();
			String carrierCd = carrier.getCarrierCd();
			switch (mode) {
				case O:
					SeaCarrier seaCarrier = SeaCarrier.load(carrierCd, session);
					return seaCarrier;
				default:
					AirCarrier airCarrier = AirCarrier.load(carrierCd, session);
					return airCarrier;
			}
		} catch (Exception e) {
			/*throw new DataUploadWarning("Error on line [" + currentLine + "] as [" + carrier.getCarrierCd() + "] as the Carrier code is invalid. " + 
						"The system expects a valid Carrier code no longer than 4 characters", null);*/
			return null;
		}
	}
	
	private City loadCity(String locCd, String countryCd, Session session) {
		City city = null;
		if (!StringUtils.isEmpty(locCd)
				&& !StringUtils.isEmpty(countryCd)) {
			if(locCd.length()>=5 && StringUtils.isEmpty(countryCd)){
				countryCd = locCd.substring(0, 2);
				locCd = locCd.substring(2, locCd.length()).replaceAll("[^a-zA-Z0-9]", "");
			}
			if (!StringUtils.isEmpty(countryCd)) {
				StringBuilder uniqueCdStrBuilder = new StringBuilder()
					.append(countryCd)
					.append(locCd);
				Criteria criteria = session.createCriteria(City.class)
					.add(Restrictions.eq("uniqueCd", uniqueCdStrBuilder.toString()));
				city = (City)criteria.uniqueResult();
			}
		}
		return city;
	}
	
	private LogisticsServiceProvider loadServiceProvider(LogisticsServiceProvider lsp, Session session) {
		Criteria criteria = session.createCriteria(LogisticsServiceProvider.class)
			.add(Restrictions.eq("clientCd", lsp.getClientCd()));
		LogisticsServiceProvider hibernateLsp = (LogisticsServiceProvider)criteria.uniqueResult();
		if (hibernateLsp == null) {
			if (!StringUtils.isEmpty(lsp.getClientCd()))
				session.save(lsp);
			else lsp = null;
		} else lsp = hibernateLsp;
		return lsp;
	}
	
	@SuppressWarnings("unchecked")
	private Container loadContainer(Container container, Session session, int currentLine, long packageId, ContainerType containerType) {
		if(container.isEmpty())
			return null;
		Container containerRetVal = new Container();
		String type = containerType.getType();
		String hql = "select ct.id from ContainerType ct join ct.containerTypeAlias cta " +
				"where ct.type =:type or cta.aliasTypeName = :aliasTypeName";
		Query query = session.createQuery(hql);
		query.setParameter("type", type);
		query.setParameter("aliasTypeName", type);
		List<Long> results = query.list();
		/*if(StringUtils.isEmpty(type))
			throw new DataUploadWarning("Error on line [" + currentLine + "] as [" + containerType.getType() + "] as the container type is invalid. " + 
					"The system expects a valid type that contains 4 characters(eg:20GP) or a valid Alias.", null);*/
		Long typeId = 0L;
		try {
			typeId = results.get(0);
		} catch (IndexOutOfBoundsException e) {
			/*throw new DataUploadWarning("Error on line [" + currentLine + "] as [" + containerType.getType() + "] as the container type is invalid. " + 
					"The system expects a valid type that contains 4 characters(eg:20GP) or a valid Alias.", null);*/
		}
		ContainerType containerTypeObj = new ContainerType();
		containerTypeObj.setId(typeId);
		
		//Session tempSess = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Container.class)
			.add(Restrictions.eq("containerNumber", container.getContainerNumber()));
					//.add(Restrictions.eq("containerType.id", typeId));
		if(typeId != 0L)
			criteria.add(Restrictions.eq("containerType.id", typeId));
		List<Container> list = criteria.list();
		Container entity = null;
		if(null != list && !list.isEmpty())
			entity = (Container)list.get(0);
		
		if (entity == null) {
			try {
				if(typeId!=0L)
					container.setContainerType(containerTypeObj);
				session.save(container);
				containerRetVal = container;
			}catch (ConstraintViolationException e) {
				e.printStackTrace();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		} else {
			try {
				//Transaction transaction = tempSess.beginTransaction();
				entity.setContainerDesc(container.getContainerDesc());
				session.update(entity);
				//transaction.commit();
				containerRetVal = entity;
			}catch (ConstraintViolationException e) {
				e.printStackTrace();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
		//tempSess.close();
		return containerRetVal;
	}
	
	private void loadOriginandDestLocationdetails(Country originCountry, Country destCountry, Port originPort, Port destPort, 
			Shipment shipment, FileUploadSessionBean bean, Session session){
		String origCountryCd = originCountry.getCountryCd();
		if(!StringUtils.isEmpty(origCountryCd)){
			Country origCountry = Country.load(origCountryCd, session);
			if (origCountry == null) {
				throw new DataUploadWarning("Origin Country [" + origCountryCd + "] could not be loaded. Warning on line [" + bean.getCurrentLine() + 
						"] of the file.", null);
			} else {
				shipment.setOrigCountryCd(origCountryCd);
			}
		}
		String origPortCd = originPort.getLocCd();
		if(!StringUtils.isEmpty(origPortCd)){
			IPort origPortForShipment = loadPortNotAgainstCountry(shipment, origPortCd, session);
			if (origPortForShipment == null) {
				throw new DataUploadWarning("Origin Port CD [" + origPortCd + "] could not be loaded. Warning on line [" + bean.getCurrentLine() + 
						"] of the file.", null);
			} else {
				shipment.setOrigLocCd(origPortCd);
			}
		}
		
		String destCountryCd = destCountry.getCountryCd();
		if(!StringUtils.isEmpty(destCountryCd)){
			Country origCountry = Country.load(destCountryCd, session);
			if (origCountry == null) {
				throw new DataUploadWarning("Destination Country [" + destCountryCd + "] could not be loaded. Warning on line [" + bean.getCurrentLine() + 
						"] of the file.", null);
			} else {
				shipment.setDestCountryCd(destCountryCd);
			}
		}
		
		String destPortCd = destPort.getLocCd();
		if(!StringUtils.isEmpty(destPortCd)){
			IPort destPortForShipment = loadPortNotAgainstCountry(shipment, destPortCd, session);
			if (destPortForShipment == null) {
				throw new DataUploadWarning("Destination Port CD [" + destPortCd + "] could not be loaded. Warning on line [" + bean.getCurrentLine() + 
						"] of the file.", null);
			} else {
				shipment.setDestLocCd(destPortCd);
			}
		}
	}
	
	private IPort loadPortNotAgainstCountry(Shipment shipment, String locCd, Session session) {
		ModeTypes mode = shipment.getMode();
		switch (mode) {
			case O:
				Seaport seaport = Seaport.loadPortNotAgainstCountry(locCd, session);
				return seaport;
			default:
				Airport airport = Airport.loadPortNotAgainstCountry(locCd, session);
				return airport;
		}
	}
}
