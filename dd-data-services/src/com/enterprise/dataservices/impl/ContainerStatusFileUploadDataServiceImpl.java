package com.enterprise.dataservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.entity.IMeta;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.FileUploadSessionBean;
import com.enterprise.common.util.StringUtils;
import com.enterprise.dataservices.IFileUploadDataService;
import com.enterprise.domain.entity.Container;
import com.enterprise.domain.entity.ContainerStatus;
import com.enterprise.domain.entity.ContainerType;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.Shipment;
import com.enterprise.domain.entity.User;
import com.enterprise.domain.entity.meta.ContainerStatusMeta;
import com.enterprise.domain.entity.meta.Meta;
import com.enterprise.user.DeepDiveUser;

@Repository
public class ContainerStatusFileUploadDataServiceImpl extends AbstractFileUploadFunction implements IFileUploadDataService {

	@Autowired
	protected SessionFactory sessionFactory;
	
	@Override
	protected Class<? extends Meta> getMetaClass() {
		return ContainerStatusMeta.class;
	}

	@Override
	public void processLine(String[] columnArr, FileUploadSessionBean bean,
			Session session) throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			DataUploadWarning {
		Map<Integer,IMeta> indexMap = bean.getIndexMap();
		Map<String, IEntity> modelMap = new HashMap<String, IEntity>();
		ContainerStatus containerStatus = new ContainerStatus();
		modelMap.put(SubTypes.ContainerStatus.name(), containerStatus);
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
		String destPort = containerStatus.getDestPort();
		String containerNumber = containerStatus.getContainerNumber();
		/*if (StringUtils.isEmpty(destPort))
			throw new DataUploadWarning("Error on line [" + (bean.getCurrentLine() + 1) + "]: Destination Port is empty.", null);*/
		if (StringUtils.isEmpty(containerNumber))
			throw new DataUploadWarning("Error on line [" + (bean.getCurrentLine() + 1) + "]: Container number is empty.", null);
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
		containerStatus.setPkg(pkg);
		String shipmentNumber = containerStatus.getShipmentNumber();
		if(!StringUtils.isEmpty(shipmentNumber)){
			Shipment shipment = Shipment.load(shipmentNumber.trim(), session, packageId, null);
			containerStatus.setShipment(shipment);
		}
		Container container = new Container();
		ContainerType containerType = new ContainerType();
		container.setContainerNumber(containerNumber);
		containerType.setType(containerStatus.getContainerSize());
		container = this.loadContainer(container, session, packageId, containerType);
		containerStatus.setContainer(container);
		ContainerStatus hibernateContainerStatus = ContainerStatus.load(containerNumber, destPort, session, packageId);
		setDefaultDateValue(containerStatus, hibernateContainerStatus);
		
		if(null != hibernateContainerStatus){
			containerStatus.setId(hibernateContainerStatus.getId());
		}
		if (containerStatus.getId() == 0L)
			session.save(containerStatus);
		else {
			session.merge(containerStatus);
		}
	}
	
	@SuppressWarnings("unchecked")
	private Container loadContainer(Container container, Session session, long packageId, ContainerType containerType) {
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
			Long typeId = 0L;
			try {
				typeId = results.get(0);
			} catch (IndexOutOfBoundsException e) {
			}
			ContainerType containerTypeObj = new ContainerType();
			containerTypeObj.setId(typeId);


			Criteria criteria = tempSess.createCriteria(Container.class)
					.add(Restrictions.eq("containerNumber", container.getContainerNumber()));
			if(typeId != 0L)
				criteria.add(Restrictions.eq("containerType.id", typeId));
			criteria.addOrder(Order.asc("id"));
					
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
	
	private void setDefaultDateValue(ContainerStatus fileContainerStatus, ContainerStatus hibernateContainerStatus){
		if(hibernateContainerStatus!=null){
			if(hibernateContainerStatus.getEtaDate() != null && fileContainerStatus.getEtaDate() == null){
				fileContainerStatus.setEtaDate(hibernateContainerStatus.getEtaDate());
			}
			if(hibernateContainerStatus.getWarfAvailDate() != null && fileContainerStatus.getWarfAvailDate() == null){
				fileContainerStatus.setWarfAvailDate(hibernateContainerStatus.getWarfAvailDate());
			}
			if(hibernateContainerStatus.getWharfStoreDate() != null && fileContainerStatus.getWharfStoreDate() == null){
				fileContainerStatus.setWharfStoreDate(hibernateContainerStatus.getWharfStoreDate());
			}
			if(hibernateContainerStatus.getYardAvailableDate() != null && fileContainerStatus.getYardAvailableDate() == null){
				fileContainerStatus.setYardAvailableDate(hibernateContainerStatus.getYardAvailableDate());
			}
			if(hibernateContainerStatus.getEstDelDate() != null && fileContainerStatus.getEstDelDate() == null){
				fileContainerStatus.setEstDelDate(hibernateContainerStatus.getEstDelDate());
			}
			if(hibernateContainerStatus.getDeliveryDate() != null && fileContainerStatus.getDeliveryDate() == null){
				fileContainerStatus.setDeliveryDate(hibernateContainerStatus.getDeliveryDate());
			}
			if(hibernateContainerStatus.getDetentionDate() != null && fileContainerStatus.getDetentionDate() == null){
				fileContainerStatus.setDetentionDate(hibernateContainerStatus.getDetentionDate());
			}
			if(hibernateContainerStatus.getEmptyRetTargetDate() != null && fileContainerStatus.getEmptyRetTargetDate() == null){
				fileContainerStatus.setEmptyRetTargetDate(hibernateContainerStatus.getEmptyRetTargetDate());
			}
			if(hibernateContainerStatus.getDehireDate() != null && fileContainerStatus.getDehireDate() == null){
				fileContainerStatus.setDehireDate(hibernateContainerStatus.getDehireDate());
			}
		}
	}
}
