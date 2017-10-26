package com.enterprise.dataservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.entity.IMeta;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.common.pojo.FileUploadSessionBean;
import com.enterprise.domain.entity.SeaCarrier;
import com.enterprise.domain.entity.meta.Meta;
import com.enterprise.domain.entity.meta.SeaCarrierMeta;

@Repository
public class SeaCarrierFileUploadDataServiceImpl extends AbstractFileUploadFunction {

	@Override
	protected Class<? extends Meta> getMetaClass() {
		return SeaCarrierMeta.class;
	}

	@Override
	public void processLine(String[] columnArr, FileUploadSessionBean bean,
			Session session) throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Map<Integer,IMeta> indexMap = bean.getIndexMap();
		Map<String, IEntity> modelMap = new HashMap<String, IEntity>();
		SeaCarrier seaCarrier = new SeaCarrier();
		modelMap.put(SubTypes.Carrier.name(), seaCarrier);
		for (int i = 0; i < columnArr.length; i++) {
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
					} catch (Throwable e) {
						String msg = "Could not set value [" + columnValue + "] as [" + meta.getColumnName() + 
							"]. Error occured on line [" + (bean.getCurrentLine() + 1) + "].";
						throw new IllegalArgumentException(msg);
					}
				}
			}
		}
		SeaCarrier hibernateSeaCarrier = SeaCarrier.load(seaCarrier.getCarrierCd(), session);
		if (hibernateSeaCarrier != null)
			seaCarrier.setId(hibernateSeaCarrier.getId());
		session.merge(seaCarrier);
	}
}