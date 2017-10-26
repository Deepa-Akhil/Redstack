package com.enterprise.dataservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.enterprise.common.enums.LoadType;
import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.util.CollectionUtils;
import com.enterprise.domain.entity.LoadHistory;
import com.enterprise.domain.entity.MenuFunction;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.SubPackageDetail;
import com.enterprise.domain.entity.User;
import com.enterprise.domain.entity.UserAccess;
import com.enterprise.user.DeepDiveUser;

@Repository
public class PackageDataServiceImpl {

	@Autowired
	protected SessionFactory sessionFactory;
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<com.enterprise.domain.entity.Package> getPackages()
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, 
			IllegalAccessException, InvocationTargetException, DataUploadWarning, DataUploadException {
		Session session = sessionFactory.openSession();
		List<Package> packages = null;
		try {
			DeepDiveUser sessionUser = (DeepDiveUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Criteria criteria = session.createCriteria(UserAccess.class);
			criteria.add(Restrictions.eq("user.id", sessionUser.getId()));
			criteria.add(Restrictions.ne("pkg.id", 1L));
			criteria.setProjection(Projections.property("pkg.id"));
			List<Long> packageIds = criteria.list();
			if(!CollectionUtils.isEmpty(packageIds)) {
				Criteria pkgCriteria = session.createCriteria(Package.class);
				pkgCriteria.setFetchMode("userAccesses", FetchMode.LAZY);
				pkgCriteria.setFetchMode("lastEditedUser", FetchMode.EAGER);
				pkgCriteria.add(Restrictions.eq("deleted", false));
				//pkgCriteria.setFetchMode("createdUser", FetchMode.EAGER);
				pkgCriteria.add(Restrictions.in("id", packageIds));
				pkgCriteria.addOrder(Order.desc("lastUpdated"));
				packages = pkgCriteria.list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return packages;
	}
	
	public void savePackage(Package pkg)throws ClassNotFoundException, SecurityException, 
				NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		DeepDiveUser sessionUser = (DeepDiveUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long id = pkg.getId();
		User user = new User();
		user.setId(sessionUser.getId());
		pkg.setCreatedUser(user);
		user.setId(sessionUser.getId());
		pkg.setLastEditedUser(user);
		pkg.setDeleted(false);
		sessionFactory.getCurrentSession().saveOrUpdate(pkg);
		if(id == 0){
			UserAccess userAccess = new UserAccess();
			userAccess.setUser(user);
			userAccess.setPkg(pkg);
			sessionFactory.getCurrentSession().save(userAccess);
		}
	}
	
	public void movePackageArcheived(long id)throws ClassNotFoundException, SecurityException, 
		NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Session session = sessionFactory.getCurrentSession();
		Package pkg = (Package) session.get(Package.class, id);
		pkg.setDeleted(true);
		session.update(pkg);
	}
	
	public void deleteSubPkg(long id)throws ClassNotFoundException, SecurityException, 
		NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Session session = sessionFactory.getCurrentSession();
		String hql1 = "delete from Meta WHERE subPkg.id = :id";
		Query query1 = session.createQuery(hql1);
		query1.setParameter("id", id);
		query1.executeUpdate();
		String hql2 = "delete from SubPackageDetail WHERE id = :id";
		Query query2 = session.createQuery(hql2);
		query2.setParameter("id", id);
		query2.executeUpdate();
	}
	
	@SuppressWarnings("deprecation")
	public Package getPackage(long packageId)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Package pkg = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(Package.class)
					.add(Restrictions.eq("id", packageId))
					.setFetchMode("lastEditedUser", FetchMode.EAGER);
			pkg = (Package)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return pkg;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<MenuFunction> getMenus()throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List<MenuFunction> functions = null;
		Session session = sessionFactory.openSession();
		try {
			DeepDiveUser sessionUser = (DeepDiveUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Criteria criteria = session.createCriteria(MenuFunction.class)
					.add(Restrictions.eq("user.id", sessionUser.getId()))
					.setFetchMode("user", FetchMode.EAGER)
					.addOrder(Order.asc("menuLevel"));
			functions = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return functions;
	}
	
	public void saveLoadHistory(long subPkgId, double successShipment, double failedShipment, int loadType, String fileName) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		DeepDiveUser sessionUser = (DeepDiveUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LoadHistory loadHist = new LoadHistory();
		SubPackageDetail subPackageDetail = getSubPackage(subPkgId);
		Package pkg = new Package();
		pkg.setId(subPackageDetail.getPkg().getId());
		User user = new User();
		user.setId(sessionUser.getId());
		loadHist.setSuccess(new BigDecimal(successShipment));
		loadHist.setFailure(new BigDecimal(failedShipment));
		loadHist.setUser(user);
		loadHist.setPkg(pkg);
		loadHist.setFileName(fileName);
		loadHist.setLoadType(loadType);
		loadHist.setWebload(true);
		sessionFactory.getCurrentSession().saveOrUpdate(loadHist);
		subPackageDetail.setLastUpdated(new Date());
		subPackageDetail.setLastEditedUser(user);
		if(loadType == 1)
			subPackageDetail.setLoadType(LoadType.S);
		else if(loadType == 2)
			subPackageDetail.setLoadType(LoadType.R);
		else if(loadType == 3)
			subPackageDetail.setLoadType(LoadType.I);
		else if(loadType == 4)
			subPackageDetail.setLoadType(LoadType.O);
		else if(loadType == 5)
			subPackageDetail.setLoadType(LoadType.C);
		sessionFactory.getCurrentSession().saveOrUpdate(subPackageDetail);
	}
	
	public void saveLoadHistory(long subPkgId, double successShipment, double failedShipment, int loadType, long userId, String fileName) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		LoadHistory loadHist = new LoadHistory();
		SubPackageDetail subPackageDetail = getSubPackage(subPkgId);
		Package pkg = new Package();
		pkg.setId(subPackageDetail.getPkg().getId());
		User user = new User();
		user.setId(userId);
		loadHist.setSuccess(new BigDecimal(successShipment));
		loadHist.setFailure(new BigDecimal(failedShipment));
		loadHist.setUser(user);
		loadHist.setPkg(pkg);
		loadHist.setLoadType(loadType);
		loadHist.setWebload(false);
		loadHist.setFileName(fileName);
		sessionFactory.getCurrentSession().saveOrUpdate(loadHist);
		subPackageDetail.setLastUpdated(new Date());
		subPackageDetail.setLastEditedUser(user);
		if(loadType == 1)
			subPackageDetail.setLoadType(LoadType.S);
		else if(loadType == 2)
			subPackageDetail.setLoadType(LoadType.R);
		else if(loadType == 3)
			subPackageDetail.setLoadType(LoadType.I);
		else if(loadType == 4)
			subPackageDetail.setLoadType(LoadType.O);
		else if(loadType == 5)
			subPackageDetail.setLoadType(LoadType.C);
		else if(loadType == 6)
			subPackageDetail.setLoadType(LoadType.CS);
		sessionFactory.getCurrentSession().saveOrUpdate(subPackageDetail);
	}
	
	public void saveLoadHistoryForEmail(long pkgId, double successShipment, double failedShipment, int loadType, long userId) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		LoadHistory loadHist = new LoadHistory();
		Package pkg = new Package();
		pkg.setId(pkgId);
		User user = new User();
		user.setId(userId);
		loadHist.setSuccess(new BigDecimal(successShipment));
		loadHist.setFailure(new BigDecimal(failedShipment));
		loadHist.setUser(user);
		loadHist.setPkg(pkg);
		loadHist.setLoadType(loadType);
		sessionFactory.getCurrentSession().saveOrUpdate(loadHist);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<LoadHistory> getLoadHistory(long pkgId) throws ClassNotFoundException, SecurityException, 
				NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Session session = sessionFactory.openSession();
		List<LoadHistory> loadHistories = new ArrayList<LoadHistory>();
		try {
			Criteria criteria = session.createCriteria(LoadHistory.class)
					.setFetchMode("pkg", FetchMode.EAGER)
					.setFetchMode("user", FetchMode.EAGER)
					.add(Restrictions.eq("pkg.id", pkgId))
					.addOrder(Order.desc("id"));
			loadHistories = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return loadHistories;
	}
	
	public User getUser(String emailId)throws ClassNotFoundException, SecurityException, 
		NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		User user = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(User.class)
					.add(Restrictions.eq("emailId", emailId));
			user = (User)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return user;
	}
	
	public User saveNewEmailUser(String emailId, long pkgId)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		User user = new User();
		user.setEmailId(emailId);
		user.setFirstName(emailId.split("@")[0]);
		user.setUserRole(1);
		user.setRegistrationDate(new Date());
		user.setUsername(emailId);
		user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		
		UserAccess userAccess = new UserAccess();
		Package pkg = new Package();
		pkg.setId(pkgId);
		userAccess.setUser(user);
		userAccess.setPkg(pkg);
		sessionFactory.getCurrentSession().saveOrUpdate(userAccess);
		return user;
	}
	
	public SubPackageDetail getSubPackage(long pkgId, String loadType)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		SubPackageDetail subPackageDetail = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(SubPackageDetail.class)
					.add(Restrictions.eq("pkg.id", loadType))
					.add(Restrictions.eq("loadType", loadType));
			subPackageDetail = (SubPackageDetail)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return subPackageDetail;
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<SubPackageDetail> getSubPackageDetails(long pkgId) throws ClassNotFoundException, SecurityException, 
				NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Session session = sessionFactory.openSession();
		List<SubPackageDetail> subPkgDetails = new ArrayList<SubPackageDetail>();
		try {
			Criteria criteria = session.createCriteria(SubPackageDetail.class)
					.setFetchMode("pkg", FetchMode.EAGER)
					.add(Restrictions.eq("pkg.id", pkgId))
					.addOrder(Order.desc("loadType"));
			subPkgDetails = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return subPkgDetails;
	}
	@SuppressWarnings("deprecation")
	public SubPackageDetail getSubPackage(long subPkgId)throws ClassNotFoundException, SecurityException, 
				NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		SubPackageDetail subPackageDetail = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(SubPackageDetail.class)
					.setFetchMode("lastEditedUser", FetchMode.EAGER)
					.setFetchMode("pkg", FetchMode.EAGER)
					.add(Restrictions.eq("id", subPkgId));
			subPackageDetail = (SubPackageDetail)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return subPackageDetail;
	}
	
	public void saveNewSubPackageDetails(SubPackageDetail subPkg)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		DeepDiveUser sessionUser = (DeepDiveUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		subPkg.setCreatedDate(new Date());
		subPkg.setLastUpdated(new Date());
		User user = new User();
		user.setId(sessionUser.getId());
		subPkg.setCreatedUser(user);
		subPkg.setLastEditedUser(user);
		sessionFactory.getCurrentSession().saveOrUpdate(subPkg);
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<String> getLoadTypes(long pkgId)throws ClassNotFoundException, SecurityException, 
				NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		List<String> loadTypes = new ArrayList<String>();
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(SubPackageDetail.class)
					.setFetchMode("lastEditedUser", FetchMode.EAGER)
					.add(Restrictions.eq("pkg.id", pkgId));
			List<SubPackageDetail> subPackageDetails = criteria.list();
			for (SubPackageDetail subPackageDetail : subPackageDetails) {
				loadTypes.add(subPackageDetail.getLoadType().getLoad());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return loadTypes;
	}
	
	public void resetRateProfile(long pkgId)throws ClassNotFoundException, SecurityException, 
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update RATE_DETAIL RD, RATE R set IS_ACTIVE=0 where R.ID=RD.RATE_ID and R.PACKAGE_ID="+pkgId;
		Query query = session.createSQLQuery(hql);
		int count = query.executeUpdate();
		System.out.println("---- count ------"+count);
	}
}
