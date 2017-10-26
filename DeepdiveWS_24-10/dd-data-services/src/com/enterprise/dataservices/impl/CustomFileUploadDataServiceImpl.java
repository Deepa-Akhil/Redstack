package com.enterprise.dataservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import com.enterprise.domain.entity.Custom;
import com.enterprise.domain.entity.Package;
import com.enterprise.domain.entity.User;
import com.enterprise.domain.entity.meta.CustomMeta;
import com.enterprise.domain.entity.meta.Meta;
import com.enterprise.user.DeepDiveUser;

@Repository
public class CustomFileUploadDataServiceImpl  extends AbstractFileUploadFunction implements IFileUploadDataService {

	@Autowired
	protected SessionFactory sessionFactory;
	
	SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");

	@Override
	protected Class<? extends Meta> getMetaClass() {
		return CustomMeta.class;
	}

	@Override
	public void processLine(String[] columnArr, FileUploadSessionBean bean,
			Session session) throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			DataUploadWarning {

		Map<Integer,IMeta> indexMap = bean.getIndexMap();
		Map<String, IEntity> modelMap = new HashMap<String, IEntity>();
		Custom custom = new Custom();
		modelMap.put(SubTypes.Custom.name(), custom);
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
		custom.setPkg(pkg);
		Custom hibernateCustom = (Custom)Custom.load(custom.getJobNumber(), custom.getPartNo(), custom.getLineNo(), session, packageId);
		if(null != hibernateCustom){
			custom.setId(hibernateCustom.getId());
		}
		if (custom.getId() == 0L)
			session.save(custom);
		else {
			session.merge(custom);
		}
	}
}
