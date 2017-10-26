package com.enterprise.businessservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.businessservices.PackageBussinessService;
import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.dataservices.impl.PackageDataServiceImpl;
import com.enterprise.domain.entity.LoadHistory;
import com.enterprise.domain.entity.MenuFunction;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.SubPackageDetail;
import com.enterprise.domain.entity.User;

@Service
public class PackageBussinessServiceImpl implements PackageBussinessService{
	
	@Autowired
	private PackageDataServiceImpl packageDataServiceImpl;
	
	public List<com.enterprise.domain.entity.Package> getPackages()
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException {
		return packageDataServiceImpl.getPackages();
	}

	@Transactional
	public void savePackage(Package pkg) throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		packageDataServiceImpl.savePackage(pkg);
	}
	
	@Transactional
	public void movePackageArcheived(long id)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		packageDataServiceImpl.movePackageArcheived(id);
	}
	
	@Transactional
	public void deleteSubPkg(long id)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		packageDataServiceImpl.deleteSubPkg(id);
	}
	
	public List<LoadHistory> getLoadHistory(long pkgId) throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return packageDataServiceImpl.getLoadHistory(pkgId);
	}
	
	public void saveLoadHistoryForEmail(long pkgId, double successShipment, double failedShipment, int loadType, long userId) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		packageDataServiceImpl.saveLoadHistoryForEmail(pkgId, successShipment, failedShipment, loadType, userId);
	}
	
	public User saveNewEmailUser(String emailId, long pkgId)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return packageDataServiceImpl.saveNewEmailUser(emailId, pkgId);
	}
	
	public List<SubPackageDetail> getSubPackageDetails(long pkgId) throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return packageDataServiceImpl.getSubPackageDetails(pkgId);
	}
	
	public List<MenuFunction> getMenus()throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return packageDataServiceImpl.getMenus();
	}
}
