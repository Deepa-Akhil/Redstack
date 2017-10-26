package com.enterprise.businessservices;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.domain.entity.LoadHistory;
import com.enterprise.domain.entity.MenuFunction;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.SubPackageDetail;
import com.enterprise.domain.entity.User;

public interface PackageBussinessService {
	public List<com.enterprise.domain.entity.Package> getPackages()
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException;
	public void savePackage(Package pkg)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public void movePackageArcheived(long id)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public void deleteSubPkg(long id)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public List<LoadHistory> getLoadHistory(long pkgId) throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public void saveLoadHistoryForEmail(long pkgId, double successShipment, double failedShipment, int loadType, long userId) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public User saveNewEmailUser(String emailId, long pkgId)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public List<SubPackageDetail> getSubPackageDetails(long pkgId) throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
	public List<MenuFunction> getMenus()throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException;
}
