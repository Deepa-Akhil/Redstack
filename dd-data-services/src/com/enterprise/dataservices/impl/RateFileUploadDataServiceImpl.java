package com.enterprise.dataservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
import com.enterprise.common.util.CSVParser;
import com.enterprise.common.util.DateUtils;
import com.enterprise.common.util.StringUtils;
import com.enterprise.dataservices.IFileUploadDataService;
import com.enterprise.domain.entity.AirCarrier;
import com.enterprise.domain.entity.Airport;
import com.enterprise.domain.entity.Carrier;
import com.enterprise.domain.entity.City;
import com.enterprise.domain.entity.Country;
import com.enterprise.domain.entity.Currency;
import com.enterprise.domain.entity.ICarrier;
import com.enterprise.domain.entity.IPort;
import com.enterprise.domain.entity.Location;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.Port;
import com.enterprise.domain.entity.Rate;
import com.enterprise.domain.entity.RateDetail;
import com.enterprise.domain.entity.RateDetailCharges;
import com.enterprise.domain.entity.SeaCarrier;
import com.enterprise.domain.entity.Seaport;
import com.enterprise.domain.entity.User;
import com.enterprise.domain.entity.meta.Meta;
import com.enterprise.domain.entity.meta.RateMeta;
import com.enterprise.user.DeepDiveUser;

@Repository
public class RateFileUploadDataServiceImpl  extends AbstractFileUploadFunction implements IFileUploadDataService {

	@Autowired
	protected SessionFactory sessionFactory;
	
	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

	@Override
	protected Class<? extends Meta> getMetaClass() {
		return RateMeta.class;
	}

	@Override
	public void processLine(String[] columnArr, FileUploadSessionBean bean,
			Session session) throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			DataUploadWarning {

		Map<Integer,IMeta> indexMap = bean.getIndexMap();
		Map<String, IEntity> modelMap = new HashMap<String, IEntity>();
		Rate rate = new Rate();
		modelMap.put(SubTypes.Rate.name(), rate);
		Country originCountry = new Country();
		modelMap.put(SubTypes.OriginCountry.name(), originCountry);
		Country destinationCountry = new Country();
		modelMap.put(SubTypes.DestinationCountry.name(), destinationCountry);
		RateDetail rateDetail = new RateDetail();
		modelMap.put(SubTypes.RateDetail.name(), rateDetail);
		RateDetailCharges rateDetailCharges = new RateDetailCharges();
		modelMap.put(SubTypes.RateDetailCharges.name(), rateDetailCharges);
		Port originPort = new Port();
		modelMap.put(SubTypes.OriginPort.name(), originPort);
		Port destinationPort = new Port();
		modelMap.put(SubTypes.DestinationPort.name(), destinationPort);
		Carrier carrier = new Carrier();
		modelMap.put(SubTypes.Carrier.name(), carrier);
		Currency currency = new Currency();
		modelMap.put(SubTypes.Currency.name(), currency);
		City originCity = new City();
		modelMap.put(SubTypes.OriginCity.name(), originCity);
		City destinationCity = new City();
		modelMap.put(SubTypes.DestinationCity.name(), destinationCity);
		Country originCityCountry = new Country();
		modelMap.put(SubTypes.OriginCityCountry.name(), originCityCountry);
		Country destinationCityCountry = new Country();
		modelMap.put(SubTypes.DestinationCityCountry.name(), destinationCityCountry);
		Currency currency1 = new Currency();modelMap.put(SubTypes.Currency1.name(), currency1);Currency currency2 = new Currency();modelMap.put(SubTypes.Currency2.name(), currency2);
		Currency currency3 = new Currency();modelMap.put(SubTypes.Currency3.name(), currency3);Currency currency4 = new Currency();modelMap.put(SubTypes.Currency4.name(), currency4);
		Currency currency5 = new Currency();modelMap.put(SubTypes.Currency5.name(), currency5);Currency currency6 = new Currency();modelMap.put(SubTypes.Currency6.name(), currency6);
		Currency currency7 = new Currency();modelMap.put(SubTypes.Currency7.name(), currency7);Currency currency8 = new Currency();modelMap.put(SubTypes.Currency8.name(), currency8);
		Currency currency9 = new Currency();modelMap.put(SubTypes.Currency9.name(), currency9);Currency currency10 = new Currency();modelMap.put(SubTypes.Currency10.name(), currency10);
		Currency currency11 = new Currency();modelMap.put(SubTypes.Currency11.name(), currency11);Currency currency12 = new Currency();modelMap.put(SubTypes.Currency12.name(), currency12);
		Currency currency13 = new Currency();modelMap.put(SubTypes.Currency13.name(), currency13);Currency currency14 = new Currency();modelMap.put(SubTypes.Currency14.name(), currency14);
		Currency currency15 = new Currency();modelMap.put(SubTypes.Currency15.name(), currency15);Currency currency16 = new Currency();modelMap.put(SubTypes.Currency16.name(), currency16);
		Currency currency17 = new Currency();modelMap.put(SubTypes.Currency17.name(), currency17);Currency currency18 = new Currency();modelMap.put(SubTypes.Currency18.name(), currency18);
		Currency currency19 = new Currency();modelMap.put(SubTypes.Currency19.name(), currency19);Currency currency20 = new Currency();modelMap.put(SubTypes.Currency20.name(), currency20);
		Currency origPenCur = new Currency();modelMap.put(SubTypes.OrigPenCur.name(), currency19);Currency origPortPenCur = new Currency();modelMap.put(SubTypes.OrigPortPenCur.name(), currency20);
		Currency destPenCur = new Currency();modelMap.put(SubTypes.DestPenCur.name(), currency19);Currency destPortPenCur = new Currency();modelMap.put(SubTypes.DestPortPenCur.name(), currency20);
		boolean isChrgExists = false;
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
						if(subTypeStr.equals("RateDetailCharges")){
							if(setter.getName().startsWith("setCharge")){
								String arr[] = setter.getName().split("setCharge");
								String hearder = bean.getContent();
								String[] hearderValues = null;
								if(!bean.isMailProcess()||bean.isCSVFile())
									hearderValues = CSVParser.getInstance().parse(hearder);
								 else 
									 hearderValues = hearder.split("#!#!");
								String desc = hearderValues[i];
								String entitySetter1 = "setChargeDesc"+arr[1];
								Method setter1 = entityClass.getMethod(entitySetter1, String.class);
								setter1.invoke(entity, desc);
								isChrgExists = true;
							}
						}
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
		if(rate.getMode()==null){
			throw new DataUploadException("No column of uploaded rate file is mapped to the required transport mode.", null);
		} else if(originPort.getLocCd()==null){
			throw new DataUploadException("No column of uploaded rate file is mapped to the required origin code.", null);
		} else if(destinationPort.getLocCd()==null){
			throw new DataUploadException("No column of uploaded rate file is mapped to the required destination code.", null);
		}/* else if(currency.getCurrencyCode()==null){
			throw new DataUploadException("No column of uploaded rate file is mapped to the required Local Currency.", null);
		}*/
		/*IF MODE = O then RATE_ID =  "MODE"-"ORIG_COUNTRY"-"ORIG_PORT"-"DEST COUNTRY"-"DEST PORT"-"CONTANER_CD"-"CARRIER_CD”  
		  for example: 0-CN-SHA-US-NYC-45GP-MSCU

		  IF MODE = A then RATE_ID =" "MODE"-"ORIG_CONUTRY"-"ORIG_PORT"-"DEST COUNTRY"-"DEST PORT"-"SERVICE_LEVEL"-"CARRIER_CD” 
		  for example: A-CN-SHA-US-NYC-EXP-SQ*/
		//String laneId = rate.getMode() + "-" + originPort.getLocCd() + "-" + destinationPort.getLocCd();
		String laneId = null;
		if(rate.getMode() == ModeTypes.O){
			laneId = rate.getMode() + "-" + originCityCountry.getCountryCd() + "-" + originPort.getLocCd() + "-" + destinationCityCountry.getCountryCd() + "-" +destinationPort.getLocCd() + "-" + 
					rateDetail.getContainerSize() + "-" +carrier.getCarrierCd();
		} else {
			laneId = rate.getMode() + "-" + originCityCountry.getCountryCd() + "-" + originPort.getLocCd() + "-" + destinationCityCountry.getCountryCd() + "-" +destinationPort.getLocCd() + "-" + 
					rateDetail.getServiceType() + "-" +carrier.getCarrierCd();
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
		rate.setPkg(pkg);
		rate.setLaneId(laneId);
		rate.setLastEditedUser(user);
		List<Rate> hibernateRates = Rate.load(laneId, session, packageId);
		if(null != hibernateRates && !hibernateRates.isEmpty()){
			for (Rate hibernateRate : hibernateRates) {
				try {
					Date hbrntStartDt = fmt.parse(fmt.format(hibernateRate.getValidityStartDate()));
					Date hbrntEndDt = fmt.parse(fmt.format(hibernateRate.getValidityEndDate()));
					Date fileStartDt = fmt.parse(fmt.format(rate.getValidityStartDate()));
					Date fileEndDt = fmt.parse(fmt.format(rate.getValidityEndDate()));
					if(!DateUtils.isValidPeriod(fileStartDt, fileEndDt)) {
						throw new DataUploadException("The validity period is not valid.", null);
					} else if(/*hbrntStartDt.equals(fileStartDt) && hbrntEndDt.equals(fileEndDt)*/
							DateUtils.isThisPeriodWithinRange(hbrntStartDt, hbrntEndDt, fileStartDt, fileEndDt)) {
						rate.setId(hibernateRate.getId());
						bean.getRateIds().add(rate.getId());
					} else if(isPeriodConflict(hbrntStartDt, hbrntEndDt, fileStartDt, fileEndDt) 
							&& !bean.isConflictChecked()){
						throw new DataUploadException("There is a conflict in validity date. Please select \"Process Date Conflicts\" checkbox if you want to merge automatically.", null);
					} else if(bean.isConflictChecked() && isPeriodConflict(hbrntStartDt, hbrntEndDt, fileStartDt, fileEndDt)){
						if(hbrntEndDt.after(fileStartDt)){
							Date newEndDate = DateUtils.dateMinusOne(fileStartDt);
							Rate.resetOldEndDate(hibernateRate.getId(), newEndDate, session);
						}
						if(hbrntEndDt.after(fileEndDt)){
							Date newStartDate = DateUtils.datePlusOne(fileEndDt);
							Rate.resetOldStartDate(hibernateRate.getId(), newStartDate, session);
						}
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			//rate.setId(hibernateRate.getId());
		}
		Currency hibernateCurrency = this.loadCurrency(currency.getCurrencyCode(), session);
		if(null!=hibernateCurrency) 
			rateDetail.setLocalCurId(hibernateCurrency);
		/*else{
			StringBuilder errorBuilder = new StringBuilder("Error on line [" + (bean.getCurrentLine() + 1) + "]: ");
			throw new DataUploadWarning(errorBuilder.append("The System requires a valid Local Currency Value.").toString(), null);
		}*/
		/*if(null==rateDetail.getChargeCategory()){
			throw new DataUploadException("No column of uploaded Rate file is mapped to the required Charge Category ", null);
		}
		if(null==rateDetail.getChargeLevel()) {
			throw new DataUploadException("No column of uploaded Rate file is mapped to the required Charge Level ", null);
		}*/
		ICarrier icarrier = this.loadCarrier(rate, carrier, session);
		if(!bean.isAliaseMatchingEnabled())
			icarrier = this.loadCarrier(rate, carrier, session);
		else
			icarrier = this.loadAliaseCarrier(rate, carrier, session, packageId);
		if(null!=icarrier) 
			rateDetail.setCarrier(icarrier);
		Country orgCountry = loadCountry(originCountry.getCountryCd(), session);
		rate.setOriginCountry(orgCountry);
		Country destCountry = loadCountry(destinationCountry.getCountryCd(), session);
		rate.setDestCountry(destCountry);
		rate.setOriginCityCountry(loadCountry(originCityCountry.getCountryCd(), session));
		rate.setDestCityCountry(loadCountry(destinationCityCountry.getCountryCd(), session));
		if(orgCountry == null){
			throw new DataUploadWarning("Origin Port Country [" + originCountry.getCountryCd() + "] could not be loaded. Warning on line [" + bean.getCurrentLine()+1 + 
					"] of the file.", null);
		}
		if(destCountry == null){
			throw new DataUploadWarning("Destination Port Country [" + destinationCountry.getCountryCd() + "] could not be loaded. Warning on line [" + bean.getCurrentLine()+1 + 
					"] of the file.", null);
		}
		IPort origPort = this.loadPort(rate, originPort, originCountry, session);
		rate.setOrigPort(origPort);
		IPort destPort = this.loadPort(rate, destinationPort, destinationCountry, session);
		rate.setDestPort(destPort);
		if(rate.getOrigPort() == null){
			throw new DataUploadWarning("Origin Port CD [" + originPort.getLocCd() + "] could not be loaded. Warning on line [" + bean.getCurrentLine()+1 + 
					"] of the file.", null);
		}
		if(rate.getDestPort() == null){
			throw new DataUploadWarning("Destination Port CD [" + destinationPort.getLocCd() + "] could not be loaded. Warning on line [" + bean.getCurrentLine()+1 + 
					"] of the file.", null);
		}
		if(!StringUtils.isEmpty(originCityCountry.getCountryCd())){
			if(!StringUtils.isEmpty(originCity.getLocCd())){
				City origCity = this.loadCity(originCity.getLocCd(), originCityCountry.getCountryCd(), session);
				rateDetail.setOrigCity(origCity);
			}
		}
		if(!StringUtils.isEmpty(destinationCityCountry.getCountryCd())){
			if(!StringUtils.isEmpty(destinationCity.getLocCd())){
				City destCity = this.loadCity(destinationCity.getLocCd(), destinationCityCountry.getCountryCd(), session);
				rateDetail.setDestCity(destCity);
			}
		}
		if (rate.getId() == 0L)
			session.save(rate);
		else session.merge(rate);
		
		if(origPenCur.getId()!=0)
			rateDetail.setOrigPenCur(origPenCur);
		if(origPortPenCur.getId()!=0)
			rateDetail.setOrigPortPenCur(origPortPenCur);
		if(destPenCur.getId()!=0)
			rateDetail.setDestPenCur(destPenCur);
		if(destPortPenCur.getId()!=0)
			rateDetail.setDestPortPenCur(destPortPenCur);
		session.save(rateDetail);
		if(isChrgExists){
			rateDetailCharges.setRateDetail(rateDetail);
			rateDetailCharges.setCurrency1(loadCurrency(currency1.getCurrencyCode(), session));rateDetailCharges.setCurrency11(loadCurrency(currency11.getCurrencyCode(), session));
			rateDetailCharges.setCurrency2(loadCurrency(currency2.getCurrencyCode(), session));rateDetailCharges.setCurrency12(loadCurrency(currency12.getCurrencyCode(), session));
			rateDetailCharges.setCurrency3(loadCurrency(currency3.getCurrencyCode(), session));rateDetailCharges.setCurrency13(loadCurrency(currency13.getCurrencyCode(), session));
			rateDetailCharges.setCurrency4(loadCurrency(currency4.getCurrencyCode(), session));rateDetailCharges.setCurrency14(loadCurrency(currency14.getCurrencyCode(), session));
			rateDetailCharges.setCurrency5(loadCurrency(currency5.getCurrencyCode(), session));rateDetailCharges.setCurrency15(loadCurrency(currency15.getCurrencyCode(), session));
			rateDetailCharges.setCurrency6(loadCurrency(currency6.getCurrencyCode(), session));rateDetailCharges.setCurrency16(loadCurrency(currency16.getCurrencyCode(), session));
			rateDetailCharges.setCurrency7(loadCurrency(currency7.getCurrencyCode(), session));rateDetailCharges.setCurrency17(loadCurrency(currency17.getCurrencyCode(), session));
			rateDetailCharges.setCurrency8(loadCurrency(currency8.getCurrencyCode(), session));rateDetailCharges.setCurrency18(loadCurrency(currency18.getCurrencyCode(), session));
			rateDetailCharges.setCurrency9(loadCurrency(currency9.getCurrencyCode(), session));rateDetailCharges.setCurrency19(loadCurrency(currency19.getCurrencyCode(), session));
			rateDetailCharges.setCurrency10(loadCurrency(currency10.getCurrencyCode(), session));rateDetailCharges.setCurrency20(loadCurrency(currency20.getCurrencyCode(), session));
			session.save(rateDetailCharges);
		}
		String hql = "UPDATE RateDetail set rate.id = :rateId WHERE id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", rateDetail.getId());
		query.setParameter("rateId", rate.getId());
		query.executeUpdate();
		
		bean.getRateDetailIds().add(rateDetail.getId());
		bean.getRateIds().add(rate.getId());
		
		/*List<RateDetail> hibernateRateDetails = this.loadRateDetails(rate, rateDetail, session);
		for (RateDetail rateDetail2 : hibernateRateDetails) {
			if(null != rateDetail2) {
				rateDetail.setId(rateDetail2.getId());
			}
			if (rateDetail.getId() == 0L)
				session.save(rateDetail);
			else session.merge(rateDetail);
			bean.getRateDetailIds().add(rateDetail.getId());
			bean.getRateIds().add(rate.getId());
			
			String hql = "UPDATE RateDetail set rate.id = :rateId WHERE id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", rateDetail.getId());
			query.setParameter("rateId", rate.getId());
			query.executeUpdate();
		}*/
		
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
	
	private ICarrier loadCarrier(Rate shipment, Carrier carrier, Session session) {
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
	}
	
	private ICarrier loadAliaseCarrier(Rate rate, Carrier carrier, Session session, long packageId) {
		ModeTypes mode = rate.getMode();
		String carrierCd = carrier.getCarrierCd();
		switch (mode) {
			case O:
				SeaCarrier seaCarrier = SeaCarrier.loadAlias(carrierCd, session, packageId);
				return seaCarrier;
			default:
				AirCarrier airCarrier = AirCarrier.loadAlias(carrierCd, session, packageId);
				return airCarrier;
		}
	}
	
	private IPort loadPort(Rate rate, Location port, Country country, Session session) {
		ModeTypes mode = rate.getMode();
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
	
	@SuppressWarnings({ "unchecked", "unused" })
	private List<RateDetail> loadRateDetails(Rate rate, RateDetail rateDetail, Session session) {
		List<RateDetail> rateDetailObjs = null;
		Carrier carrier = rateDetail.getCarrier();
		String containerSize = rateDetail.getContainerSize();
		Criteria criteria = session.createCriteria(RateDetail.class)
			.add(Restrictions.eq("rate.id", rate.getId()));
		if(null != carrier)
			criteria.add(Restrictions.eq("carrier", carrier));
		if(null != containerSize)
			criteria.add(Restrictions.eq("containerSize", containerSize));
		rateDetailObjs = criteria.list();
		return  rateDetailObjs;  
	}
	
	private City loadCity(String locCd, String countryCd, Session session) {
		City city = null;
		if (!StringUtils.isEmpty(locCd)
				&& !StringUtils.isEmpty(countryCd)) {
			StringBuilder uniqueCdStrBuilder = new StringBuilder()
				.append(countryCd)
				.append(locCd);
			Criteria criteria = session.createCriteria(City.class)
				.add(Restrictions.eq("uniqueCd", uniqueCdStrBuilder.toString()));
			city = (City)criteria.uniqueResult();
		}
		return city;
	}
	
	private Country loadCountry(String countryCd, Session session) {
		Country country = null;
		if (!StringUtils.isEmpty(countryCd)) {
			Criteria criteria = session.createCriteria(Country.class)
				.add(Restrictions.eq("countryCd", countryCd));
			country = (Country)criteria.uniqueResult();
		}
		return country;
	}
	
	private boolean isPeriodConflict(Date exstngStrtDt, Date exstngEndDt, Date fileStrtDt, Date fileEndDt) {
		if(DateUtils.isThisDateWithinRange(fileStrtDt, exstngStrtDt, exstngEndDt)){
			return true;
		} if(DateUtils.isThisDateWithinRange(fileEndDt, exstngStrtDt, exstngEndDt)){
			return true;
		}
		return false;
	}
}
