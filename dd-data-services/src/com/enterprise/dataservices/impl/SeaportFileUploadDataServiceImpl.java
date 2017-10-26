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
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.FileUploadSessionBean;
import com.enterprise.domain.entity.Country;
import com.enterprise.domain.entity.Seaport;
import com.enterprise.domain.entity.meta.Meta;
import com.enterprise.domain.entity.meta.SeaportMeta;

@Repository
public class SeaportFileUploadDataServiceImpl extends AbstractFileUploadFunction {

	@Override
	protected Class<? extends Meta> getMetaClass() {
		return SeaportMeta.class;
	}

	@Override
	public void processLine(String[] columnArr, FileUploadSessionBean bean,
			Session session) throws ClassNotFoundException, SecurityException,
			NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Map<Integer,IMeta> indexMap = bean.getIndexMap();
		Map<String, IEntity> modelMap = new HashMap<String, IEntity>();
		Seaport seaport = new Seaport();
		modelMap.put(SubTypes.Seaport.name(), seaport);
		Country country = new Country();
		modelMap.put(SubTypes.Country.name(), country);
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
		String countryCd = country.getCountryCd();
		String uniqueCd = seaport.getUniqueCd();
		Seaport hibernateSeaport = (Seaport)Seaport.load(uniqueCd, session);
		if (hibernateSeaport != null) {
			seaport.setId(hibernateSeaport.getId());
			seaport.setCountry(hibernateSeaport.getCountry());
		} else {
			country = Country.load(countryCd, session);
			if (country == null)
				throw new DataUploadWarning("Country [" + countryCd + "] could not be loaded. Warning on line [" + bean.getCurrentLine() + 
						"] of the file.", null);
			seaport.setCountry(country);
		}
		session.merge(seaport);
	}
}