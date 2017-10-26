package com.enterprise.dataservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
import com.enterprise.domain.entity.ICarrier;
import com.enterprise.domain.entity.IPort;
import com.enterprise.domain.entity.Location;
import com.enterprise.domain.entity.LocationAlias;
import com.enterprise.domain.entity.LogisticsServiceProvider;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.Port;
import com.enterprise.domain.entity.RefType;
import com.enterprise.domain.entity.SeaCarrier;
import com.enterprise.domain.entity.Seaport;
import com.enterprise.domain.entity.Shipment;
import com.enterprise.domain.entity.ShipmentContainer;
import com.enterprise.domain.entity.User;
import com.enterprise.domain.entity.meta.Meta;
import com.enterprise.domain.entity.meta.ShipmentMeta;
import com.enterprise.user.DeepDiveUser;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  10 Mar 2014 5:01:32 PM
 * @author rafourie
 */
@Repository
public class ShipmentFileUploadDataServiceImpl extends AbstractFileUploadFunction implements IFileUploadDataService {
	
	@Autowired
	protected SessionFactory sessionFactory;

	@Override
	protected Class<? extends Meta> getMetaClass() {
		return ShipmentMeta.class;
	}
	
	@Override
	public void processLine(String[] columnArr, FileUploadSessionBean bean, Session session) 
			throws DataUploadWarning, DataUploadException, ClassNotFoundException, 
				NoSuchMethodException, SecurityException {
		Map<Integer,IMeta> indexMap = bean.getIndexMap();
		Map<String, IEntity> modelMap = new HashMap<String, IEntity>();
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
		Container container = new Container();
		modelMap.put(SubTypes.Container.name(), container);
		ContainerType containerType = new ContainerType();
		modelMap.put(SubTypes.ContainerType.name(), containerType);
		ShipmentContainer shipmentContainerObj = new ShipmentContainer();
		modelMap.put(SubTypes.ShipmentContainer.name(), shipmentContainerObj);
		RefType refType = new RefType();
		modelMap.put(SubTypes.RefType.name(), refType);
		for (int i = 0; i < columnArr.length; i++) {
			StringBuilder errorBuilder = new StringBuilder("Error on line [" + (bean.getCurrentLine() + 1) + "]: ");
			String columnValue = columnArr[i];
			IMeta meta = indexMap.get(new Integer(i));
			if (meta != null) {
				String entityClassName = meta.getEntityClass();
				Class<?> entityClass = Class.forName(entityClassName);
				String subTypeStr = meta.getSubType().getName();
				IEntity entity = modelMap.get(subTypeStr);
				if (entity != null) {
					String entitySetter = meta.getEntitySetter();
					Method setter = entityClass.getMethod(entitySetter, String.class);
					try {
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
		
		String shipmentNumber = shipment.getShipmentNumber();
		if (StringUtils.isEmpty(shipmentNumber))
			throw new DataUploadException("No column of uploaded shipment file is mapped to the required shipment " + 
					"number.", null);
		else if (shipment.getMode() == null)
			throw new DataUploadException("No column of uploaded shipment file is mapped to the required shipment " + 
					"transport mode.", null);
		long packageId = bean.getPackageId();
		Shipment hibernateShipment = (Shipment)Shipment.load(shipmentNumber, session, packageId, shipment.getMode());
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
		shipment.setPkg(pkg);
		if (hibernateShipment != null) {
			shipment.setId(hibernateShipment.getId());
			shipment.setServiceProvider(hibernateShipment.getServiceProvider());
			shipment.setOrigCity(hibernateShipment.getOrigCity());
			shipment.setDestCity(hibernateShipment.getDestCity());
			Shipment.deleteOrphans(hibernateShipment, bean.getUuid(), session);
			shipment.setShipmentContainers(hibernateShipment.getShipmentContainers());
		}
		if(bean.isLocationAliaseEnabled()){
			LocationAlias orgLocAlias = loadAliasLocation(originPort.getLocCd(), packageId, session);
			if(orgLocAlias!=null) {
				originPort.setLocCd(orgLocAlias.getLocation().getLocCd());
				originPort.setUniqueCd(orgLocAlias.getLocation().getUniqueCd());
			}
			LocationAlias destLocAlias = loadAliasLocation(destinationPort.getLocCd(), packageId, session);
			if(destLocAlias!=null) {
				destinationPort.setLocCd(destLocAlias.getLocation().getLocCd());
				destinationPort.setUniqueCd(destLocAlias.getLocation().getUniqueCd());
			}
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
		/*IF MODE = O then RATE_ID =  "MODE"-"ORIG_COUNTRY"-"ORIG_PORT"-"DEST COUNTRY"-"DEST PORT"-"CONTANER_CD"-"CARRIER_CD”  
		  for example: 0-CN-SHA-US-NYC-45GP-MSCU

		  IF MODE = A then RATE_ID =" "MODE"-"ORIG_CONUTRY"-"ORIG_PORT"-"DEST COUNTRY"-"DEST PORT"-"SERVICE_LEVEL"-"CARRIER_CD” 
		  for example: A-CN-SHA-US-NYC-EXP-SQ*/
		String laneId = null;
		String carrierID = null;
		if(shipment.getCarrier()!=null){
			//carrierID = Carrier.getCarrierCd(session, shipment.getCarrier().getId());
			carrierID=shipment.getCarrier().getCarrierCd();
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
		
		if(!StringUtils.isEmpty(refType.getRefValue())){
			RefType refTypeEx = RefType.load(refType.getRefValue(), session, shipment.getId());
			if (refTypeEx !=null) {
				refType.setId(refTypeEx.getId());
			}
			refType.setShipment(shipment);
			session.merge(refType);
		}
		
		String containerNoAll = container.getContainerNumber();
		if(!StringUtils.isEmpty(containerNoAll)){
			String[] containerNoArr = containerNoAll.split(",");
			if(null!=containerNoArr && containerNoArr.length>0 && containerNoArr.length == 1){
				String containerNType[] = containerNoAll.split(":");
				if(containerNType.length>1){
					/*if(!StringUtils.isEmpty(containerType.getType()) && !containerType.getType().equals(containerNType[1].trim())){
						throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as the Container type mismatch found. [" + containerType.getType() + "!= "+containerNType[1].trim()+"]" + 
								"The system expects a single Container type mapping.", null);
					}*/
					container.setContainerNumber(containerNType[0].trim());
					containerType.setType(containerNType[1].trim());
				}
				String containerNType2[] = containerNoAll.split("\\(");
				if(containerNType2.length>1){
					String contType = containerNType2[1].replaceAll("\\)", "").trim();
					/*if(!StringUtils.isEmpty(containerType.getType()) && !containerType.getType().equals(contType)){
						throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as the Container type mismatch found. [" + containerType.getType() + "!= "+containerNType[1].trim()+"]" + 
								"The system expects a single Container type mapping.", null);
					}*/
					if(StringUtils.isEmpty(containerNType2[0].trim())){
						throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as the Container Number [" + containerNType2[0].trim() +"]" + 
								" is empty.", null);
					}
					container.setContainerNumber(containerNType2[0].trim());
					containerType.setType(contType);
				}
				ShipmentContainer shipmentContainer 
				= this.loadShipmentContainer(shipment, container, shipmentContainerObj, bean.getUuid(), session, bean.getCurrentLine(), containerType);
				if (shipmentContainer != null) {
					if(shipment.getMode() == ModeTypes.O){
						laneId = shipment.getMode() + "-" + originCountry.getCountryCd() + "-" + originPort.getLocCd() + "-" + 
								destinationCountry.getCountryCd() + "-" +destinationPort.getLocCd() + "-" + 
								Container.getContainerType(session, shipmentContainer.getContainer().getId()) + 
								/*containerType.getType()+ */
								"-" + carrierID;
					}
					shipmentContainer.setLaneId(laneId);
					shipment.getShipmentContainers().add(shipmentContainer);
					session.saveOrUpdate(shipmentContainer);
				}
				
				double volume = 0f, weight = 0f, noOfPeieces = 0f, chargableWeight = 0f;
				for (ShipmentContainer shipmentContainerTemp : shipment.getShipmentContainers()) {
					volume += shipmentContainerTemp.getVolume() == null ? 0f : shipmentContainerTemp.getVolume().doubleValue() ;
					weight += shipmentContainerTemp.getWeight() == null ? 0f : shipmentContainerTemp.getWeight().doubleValue() ;
					noOfPeieces += shipmentContainerTemp.getNumberOfPieces() == null ? 0f : shipmentContainerTemp.getNumberOfPieces().doubleValue() ;
					chargableWeight += shipmentContainerTemp.getChargeableWeight() == null ? 0f : shipmentContainerTemp.getChargeableWeight().doubleValue() ;
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
						/*if(!StringUtils.isEmpty(containerType.getType()) && !containerType.getType().equals(containerNType[1].trim())){
							throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as the Container type mismatch found. [" + containerType.getType() + "!= "+containerNType[1].trim()+"]" + 
									"The system expects a single Container type mapping.", null);
						}*/
						container.setContainerNumber(containerNType[0].trim());
						containerType.setType(containerNType[1].trim());
					} else if(containerNType2.length>1){
						String contType = containerNType2[1].replaceAll("\\)", "").trim();
						/*if(!StringUtils.isEmpty(containerType.getType()) && !containerType.getType().equals(contType)){
							throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as the Container type mismatch found. [" + containerType.getType() + "!= "+containerNType2[1].trim()+"]" + 
									"The system expects a single Container type mapping.", null);
						}*/
						if(StringUtils.isEmpty(containerNType2[0].trim())){
							throw new DataUploadWarning("Error on line [" + bean.getCurrentLine() + "] as the Container Number [" + containerNType2[0].trim() +"]" + 
									" is empty.", null);
						}
						container.setContainerNumber(containerNType2[0].trim());
						containerType.setType(contType);
					} else {
						container.setContainerNumber(containerNo.trim());
					}
					container.setId(0L);
					container = this.loadContainer(container, session, bean.getCurrentLine(), shipment.getPkg().getId(), containerType);
					ShipmentContainer shipmentContainer 
						= this.loadShipmentContainer(shipment, container, shipmentContainerObj, bean.getUuid(), session, bean.getCurrentLine(), containerType);
					if (shipmentContainer != null) {
						if(shipment.getMode() == ModeTypes.O){
							laneId = shipment.getMode() + "-" + originCountry.getCountryCd() + "-" + originPort.getLocCd() + "-" + 
									destinationCountry.getCountryCd() + "-" +destinationPort.getLocCd() + "-" + 
									Container.getContainerType(session, shipmentContainer.getContainer().getId()) + 
									/*containerType.getType()+*/
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
	}
	
	private City loadCity(String locCd, String countryCd, Session session) {
		City city = null;
		if(!StringUtils.isEmpty(locCd)){
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
			ShipmentContainer shipmentContainerObj, UUID uuid, Session session, int currentLine, ContainerType containerType) {
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
			Container persistentContainer = this.loadContainer(container, session, currentLine, shipment.getPkg().getId(), containerType);
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
	
	/*private Container loadContainer(Container container, Session session) {
		Session tempSess = sessionFactory.openSession();
		Criteria criteria = tempSess.createCriteria(Container.class)
			.add(Restrictions.eq("containerNumber", container.getContainerNumber()))
			.add(Restrictions.eq("type", container.getType()));
		//Container entity = (Container)criteria.uniqueResult();
		@SuppressWarnings("unchecked")
		List<Container> list = criteria.list();
		Container entity = null;
		if(null != list && !list.isEmpty())
			entity = (Container)list.get(0);
		
		if (entity == null) {
			if (!container.isEmpty()) {
				try {
					tempSess.save(container);
				}catch (ConstraintViolationException e) {
					e.printStackTrace();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
				tempSess.close();
				return container;
			} else{
				tempSess.close();
				return null;
			}
		} else {
			if (!container.isEmpty()) {
				try {
					Transaction transaction = tempSess.beginTransaction();
					entity.setContainerDesc(container.getContainerDesc());
					tempSess.update(entity);
					transaction.commit();
				}catch (ConstraintViolationException e) {
					e.printStackTrace();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
				tempSess.close();
				return entity;
			} else{
				tempSess.close();
				return null;
			}
		}
	}*/
	
	/*@SuppressWarnings("deprecation")
	private Container loadContainer(Container container, Session session, int currentLine, long packageId, ContainerType containerType) {
		Criteria criteria = session.createCriteria(Container.class)
			.createAlias("containerType", "ct")
			.add(Restrictions.eq("containerNumber", container.getContainerNumber()))
			.add(Restrictions.eq("ct.type", containerType.getType()));
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
					//entity = (Container)((ContainerTypeAlias)list1.get(0)).getContainer();
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
	
	@SuppressWarnings("unchecked")
	private Container loadContainer(Container container, Session session, int currentLine, long packageId, ContainerType containerType) {
		if(container.isEmpty())
			return null;
		Session tempSess = sessionFactory.openSession();
		Container containerRetVal = new Container();
		try {
			String type = containerType.getType();
			String hql = "select ct.id from ContainerType ct join ct.containerTypeAlias cta " +
					"where ct.type =:type or cta.aliasTypeName = :aliasTypeName";
			Query query = tempSess.createQuery(hql);
			query.setParameter("type", type);
			query.setParameter("aliasTypeName", type);
			List<Long> results = query.list();
			/*if(!StringUtils.isEmpty(type) && CollectionUtils.isEmpty(results))
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


			Criteria criteria = tempSess.createCriteria(Container.class)
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
					tempSess.save(container);
					containerRetVal = container;
				}catch (ConstraintViolationException e) {
					e.printStackTrace();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Transaction transaction = tempSess.beginTransaction();
					entity.setContainerDesc(container.getContainerDesc());
					tempSess.update(entity);
					transaction.commit();
					containerRetVal = entity;
				}catch (ConstraintViolationException e) {
					e.printStackTrace();
				} catch (HibernateException e) {
					e.printStackTrace();
				}
			}
		} finally {
			tempSess.close();
		}
		return containerRetVal;
	}
	
	private void loadOriginandDestLocationdetails(Country originCountry, Country destCountry, Port originPort, Port destPort, 
			Shipment shipment, FileUploadSessionBean bean, Session session){
		String origCountryCd = originCountry.getCountryCd();
		if(!StringUtils.isEmpty(origCountryCd)){
			Country origCountry = Country.load(origCountryCd, session);
			if (origCountry != null) {
				shipment.setOrigCountryCd(origCountryCd);
			}
		}
		String origPortCd = originPort.getLocCd();
		if(!StringUtils.isEmpty(origPortCd)){
			IPort origPortForShipment = loadPortNotAgainstCountry(shipment, origPortCd, session);
			if (origPortForShipment != null) {
				shipment.setOrigLocCd(origPortCd);
			}
		}
		
		String destCountryCd = destCountry.getCountryCd();
		if(!StringUtils.isEmpty(destCountryCd)){
			Country destnCountry = Country.load(destCountryCd, session);
			if (destnCountry != null) {
				shipment.setDestCountryCd(destCountryCd);
			}
		}
		
		String destPortCd = destPort.getLocCd();
		if(!StringUtils.isEmpty(destPortCd)){
			IPort destPortForShipment = loadPortNotAgainstCountry(shipment, destPortCd, session);
			if (destPortForShipment != null) {
				shipment.setDestLocCd(destPortCd);
			}
		}
	}
	
	public static LocationAlias loadAliasLocation(String locCd, long pkgID, Session session) {
		Criteria criteria = session.createCriteria(LocationAlias.class)
			.add(Restrictions.eq("aliasName", locCd))
			.createAlias("pkg", "p1")
			.createAlias("location", "l1")
			.add(Restrictions.eq("p1.id", pkgID));
		LocationAlias locationAlias = (LocationAlias)criteria.uniqueResult();
		return locationAlias;
	}
}