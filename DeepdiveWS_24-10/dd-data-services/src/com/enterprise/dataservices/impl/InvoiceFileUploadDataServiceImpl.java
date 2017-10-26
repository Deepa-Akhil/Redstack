package com.enterprise.dataservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
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
import com.enterprise.domain.entity.Airport;
import com.enterprise.domain.entity.Country;
import com.enterprise.domain.entity.Currency;
import com.enterprise.domain.entity.IPort;
import com.enterprise.domain.entity.Invoice;
import com.enterprise.domain.entity.InvoiceDetail;
import com.enterprise.domain.entity.Location;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.Port;
import com.enterprise.domain.entity.Seaport;
import com.enterprise.domain.entity.Shipment;
import com.enterprise.domain.entity.User;
import com.enterprise.domain.entity.meta.InvoiceMeta;
import com.enterprise.domain.entity.meta.Meta;
import com.enterprise.user.DeepDiveUser;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  10 Mar 2014 5:01:32 PM
 * @author rafourie
 */
@Repository
public class InvoiceFileUploadDataServiceImpl extends AbstractFileUploadFunction implements IFileUploadDataService {

	@Override
	protected Class<? extends Meta> getMetaClass() {
		return InvoiceMeta.class;
	}
	
	@Override
	public void processLine(String[] columnArr, FileUploadSessionBean bean, Session session) 
			throws DataUploadWarning, DataUploadException, ClassNotFoundException, 
				NoSuchMethodException, SecurityException {
		Map<Integer,IMeta> indexMap = bean.getIndexMap();
		Map<String, IEntity> modelMap = new HashMap<String, IEntity>();
		Invoice invoice = new Invoice();
		modelMap.put(SubTypes.Invoice.name(), invoice);
		InvoiceDetail invoiceDetail = new InvoiceDetail();
		modelMap.put(SubTypes.InvoiceDetail.name(), invoiceDetail);
		Currency currency = new Currency();
		modelMap.put(SubTypes.Currency.name(), currency);
		Country country = new Country();
		modelMap.put(SubTypes.Country.name(), country);
		Port originPort = new Port();
		modelMap.put(SubTypes.OriginPort.name(), originPort);
		Port destinationPort = new Port();
		modelMap.put(SubTypes.DestinationPort.name(), destinationPort);
		Country originCountry = new Country();
		modelMap.put(SubTypes.OriginCountry.name(), originCountry);
		Country destinationCountry = new Country();
		modelMap.put(SubTypes.DestinationCountry.name(), destinationCountry);
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
		if(null == invoice.getShipmentNumber())
			throw new DataUploadException("No column of uploaded Invoice file is mapped to the required Shipment Number.", null);
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
		invoice.setPkg(pkg);
		Shipment shipment = Shipment.load(invoice.getShipmentNumber(), session, packageId, null);
		invoice.setShipment(shipment);
		
		if(shipment!=null){
			IPort origPort = this.loadPort(shipment, originPort, originCountry, session);
			invoice.setOrigPort(origPort);
			IPort destPort = this.loadPort(shipment, destinationPort, destinationCountry, session);
			invoice.setDestPort(destPort);
		}
		
		Currency hibernateCurrency = this.loadCurrency(currency.getCurrencyCode(), session);
		if(null != hibernateCurrency) 
			invoiceDetail.setLocalCurId(hibernateCurrency);
		
		Invoice hibernateInvoice = (Invoice)Invoice.load(invoice.getShipmentNumber(), invoice.getInvoiceNumber(), session, packageId);
		if(null != hibernateInvoice){
			invoice.setId(hibernateInvoice.getId());
		}
		Country billingCountry = loadCountry(country.getCountryCd(), session);
		invoice.setBillingCountry(billingCountry);
		if (invoice.getId() == 0L)
			session.save(invoice);
		else {
			/*String hql = "delete from InvoiceDetail where invoice.id = :invoiceId";
			Query query = session.createQuery(hql);
			query.setParameter("invoiceId", invoice.getId());
			query.executeUpdate();*/
			session.merge(invoice);
		}
		if(!invoice.isDetailNotNeeded() && !StringUtils.isEmpty(invoiceDetail.getChargeCode())) {
			invoiceDetail.setInvoice(invoice);
			session.save(invoiceDetail);
			
			bean.getRateDetailIds().add(invoiceDetail.getId());
			bean.getRateIds().add(invoice.getId());
		}
		
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
	
	private Country loadCountry(String countryCd, Session session) {
		Country country = null;
		if (!StringUtils.isEmpty(countryCd)) {
			Criteria criteria = session.createCriteria(Country.class)
				.add(Restrictions.eq("countryCd", countryCd));
			country = (Country)criteria.uniqueResult();
		}
		return country;
	}
	
	private IPort loadPort(Shipment shipment, Location port, Country country, Session session) {
		try {
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
		} catch (Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		String locCd = "AU*DRS";
		
		if(!StringUtils.isEmpty(locCd) && locCd.length()>=5){
			
			System.out.println("----------"+locCd.substring(0, 2));
			System.out.println("----------"+locCd.substring(2, locCd.length()).replaceAll("[^a-zA-Z0-9]", ""));
		}
	}
}