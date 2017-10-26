package com.enterprise.businessservices;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.FileUploadSessionBean;
import com.enterprise.common.pojo.HeadingColumnMappedBean;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.SubPackageDetail;
import com.enterprise.domain.entity.User;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  04 Mar 2014 7:21:04 AM
 * @author rafourie
 */
public interface FileUploadBusinessService {
	public abstract FileUploadSessionBean processInvoiceFileContent(String[] textStr, FileUploadSessionBean bean)
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException;
	
	public abstract FileUploadSessionBean processShipmentFileContent(String[] textStr, FileUploadSessionBean bean)
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException;
	
	public abstract FileUploadSessionBean processCityFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException;
	
	public abstract FileUploadSessionBean processAirportFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException, DataUploadWarning;
	
	public abstract FileUploadSessionBean processSeaportFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException;
	
	public abstract FileUploadSessionBean processAirCarrierFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException;
	
	public abstract FileUploadSessionBean processSeaCarrierFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException;
	public Package getPackage(long packageId)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public void saveLoadHistory(long pkgId, double successShipment, double failedShipment, int loadType, String fileName) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public abstract User saveNewEmailUser(String emailId, long pkgId)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public abstract User getUser(String emailId)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public FileUploadSessionBean processRateFileContent(String[] textStr, FileUploadSessionBean bean) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
				IllegalAccessException, InvocationTargetException, DataUploadWarning;
	public FileUploadSessionBean processOrderFileContent(String[] textStr, FileUploadSessionBean bean) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
				IllegalAccessException, InvocationTargetException, DataUploadWarning;
	public List<HeadingColumnMappedBean> fetchAvailableHeadings(List<String> columnHeadingList, long packageId, long subPackageId);
	public void saveLoadHistory(long pkgId, double successShipment, double failedShipment, int loadType, long userId, String fileName) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public SubPackageDetail getSubPackage(long subPackageId)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public List<String> getLoadTypes(long pkgId) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public void resetRateProfile(long pkgId) throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public FileUploadSessionBean processCustomFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
		IllegalAccessException, InvocationTargetException, DataUploadWarning;
	public FileUploadSessionBean processContainerStatusFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException,
		IllegalAccessException, InvocationTargetException, DataUploadWarning;
	
}