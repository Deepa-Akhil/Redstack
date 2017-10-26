package com.enterprise.businessservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.businessservices.FileUploadBusinessService;
import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.FileUploadSessionBean;
import com.enterprise.common.pojo.HeadingColumnMappedBean;
import com.enterprise.dataservices.impl.AirCarrierFileUploadDataServiceImpl;
import com.enterprise.dataservices.impl.AirportFileUploadDataServiceImpl;
import com.enterprise.dataservices.impl.CityFileUploadDataServiceImpl;
import com.enterprise.dataservices.impl.ContainerStatusFileUploadDataServiceImpl;
import com.enterprise.dataservices.impl.CustomFileUploadDataServiceImpl;
import com.enterprise.dataservices.impl.InvoiceFileUploadDataServiceImpl;
import com.enterprise.dataservices.impl.OrderFileUploadDataServiceImpl;
import com.enterprise.dataservices.impl.PackageDataServiceImpl;
import com.enterprise.dataservices.impl.RateFileUploadDataServiceImpl;
import com.enterprise.dataservices.impl.SeaCarrierFileUploadDataServiceImpl;
import com.enterprise.dataservices.impl.SeaportFileUploadDataServiceImpl;
import com.enterprise.dataservices.impl.ShipmentFileUploadDataServiceImpl;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.SubPackageDetail;
import com.enterprise.domain.entity.User;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  04 Mar 2014 7:22:38 AM
 * @author rafourie
 */
@Service(value = "fileUploadBusinessService")
public class FileUploadBusinessServiceImpl implements FileUploadBusinessService {
	
	@Autowired
	private InvoiceFileUploadDataServiceImpl invoiceFileUploadDataService;
	
	@Autowired
	private ShipmentFileUploadDataServiceImpl shipmentFileUploadDataService;
	
	@Autowired
	private CityFileUploadDataServiceImpl cityFileUploadDataService;
	
	@Autowired
	private AirportFileUploadDataServiceImpl airportFileUploadDataServiceImpl;
	
	@Autowired
	private SeaportFileUploadDataServiceImpl seaportFileUploadDataServiceImpl;
	
	@Autowired
	private AirCarrierFileUploadDataServiceImpl airCarrierFileUploadDataServiceImpl;
	
	@Autowired
	private SeaCarrierFileUploadDataServiceImpl seaCarrierFileUploadDataServiceImpl;
	
	@Autowired
	private PackageDataServiceImpl packageDataServiceImpl;
	
	@Autowired
	private RateFileUploadDataServiceImpl rateFileUploadDataServiceImpl;
	
	@Autowired
	private OrderFileUploadDataServiceImpl orderFileUploadDataServiceImpl;
	
	@Autowired
	private CustomFileUploadDataServiceImpl customFileUploadDataServiceImpl;
	
	@Autowired
	private ContainerStatusFileUploadDataServiceImpl containerStatusFileUploadDataServiceImpl;
	
	@Transactional
	public FileUploadSessionBean processInvoiceFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException {
		return invoiceFileUploadDataService.processFileContent(textStr, bean);
	}

	@Transactional
	public FileUploadSessionBean processShipmentFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException {
		return shipmentFileUploadDataService.processFileContent(textStr, bean);
	}
	
	@Transactional
	public FileUploadSessionBean processCityFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException {
		return cityFileUploadDataService.processFileContent(textStr, bean);
	}
	
	@Transactional
	public FileUploadSessionBean processAirportFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, DataUploadWarning {
		return airportFileUploadDataServiceImpl.processFileContent(textStr, bean);
	}

	@Transactional
	public FileUploadSessionBean processSeaportFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		return seaportFileUploadDataServiceImpl.processFileContent(textStr, bean);
	}

	@Transactional
	public FileUploadSessionBean processAirCarrierFileContent(String[] textStr,	FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		return airCarrierFileUploadDataServiceImpl.processFileContent(textStr, bean);
	}
	
	@Transactional
	public FileUploadSessionBean processSeaCarrierFileContent(String[] textStr,	FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		return seaCarrierFileUploadDataServiceImpl.processFileContent(textStr, bean);
	}

	public Package getPackage(long packageId) throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		return packageDataServiceImpl.getPackage(packageId);
	}
	
	@Transactional
	public void saveLoadHistory(long pkgId, double successShipment, double failedShipment, int loadType, String fileName) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		packageDataServiceImpl.saveLoadHistory(pkgId, successShipment, failedShipment, loadType, fileName);
	}
	
	@Transactional
	public FileUploadSessionBean processRateFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, DataUploadWarning {
		return rateFileUploadDataServiceImpl.processFileContent(textStr, bean);
	}

	@Transactional
	public FileUploadSessionBean processOrderFileContent(String[] textStr,
			FileUploadSessionBean bean) throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			DataUploadWarning {
		return orderFileUploadDataServiceImpl.processFileContent(textStr, bean);
	}
	
	@Transactional
	public FileUploadSessionBean processCustomFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException, DataUploadWarning {
		return customFileUploadDataServiceImpl.processFileContent(textStr, bean);
	}
	
	@Transactional
	public List<HeadingColumnMappedBean> fetchAvailableHeadings(List<String> columnHeadingList, long packageId, long subPackageId){
		return orderFileUploadDataServiceImpl.fetchAvailableHeadings(columnHeadingList, packageId, subPackageId);
	}
	
	@Transactional
	public User saveNewEmailUser(String emailId, long pkgId)throws ClassNotFoundException, SecurityException, 
		NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return packageDataServiceImpl.saveNewEmailUser(emailId, pkgId);
	}
	
	public User getUser(String emailId)throws ClassNotFoundException, SecurityException, 
		NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return packageDataServiceImpl.getUser(emailId);
	}
	
	@Transactional
	public void saveLoadHistory(long pkgId, double successShipment, double failedShipment, int loadType, long userId, String fileName) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		packageDataServiceImpl.saveLoadHistory(pkgId, successShipment, failedShipment, loadType, userId, fileName);
	}

	public SubPackageDetail getSubPackage(long subPackageId)
			throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		return packageDataServiceImpl.getSubPackage(subPackageId);
	}
	
	public List<String> getLoadTypes(long pkgId) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return packageDataServiceImpl.getLoadTypes(pkgId);
	}
	
	@Transactional
	public void resetRateProfile(long pkgId) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		packageDataServiceImpl.resetRateProfile(pkgId);
	}
	
	@Transactional
	public FileUploadSessionBean processContainerStatusFileContent(
			String[] textStr, FileUploadSessionBean bean)
			throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			DataUploadWarning {
		return containerStatusFileUploadDataServiceImpl.processFileContent(textStr, bean);
	}
}