package com.enterprise.dataservices.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.entity.IMeta;
import com.enterprise.common.pojo.FileUploadSessionBean;
import com.enterprise.dataservices.IFileUploadDataService;
import com.enterprise.domain.entity.City;
import com.enterprise.domain.entity.Country;
import com.enterprise.domain.entity.meta.CityMeta;
import com.enterprise.domain.entity.meta.Meta;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  04 Mar 2014 7:24:24 AM
 * @author rafourie
 */
@Repository
public class CityFileUploadDataServiceImpl extends AbstractFileUploadFunction implements IFileUploadDataService {
	
	@Override
	protected Class<? extends Meta> getMetaClass() {
		return CityMeta.class;
	}

	@Override
	public void processLine(String[] columnArr, FileUploadSessionBean bean, Session session) 
			throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
				IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map<Integer,IMeta> indexMap = bean.getIndexMap();
		Map<String, IEntity> modelMap = new HashMap<String, IEntity>();
		City city = new City();
		modelMap.put(city.getClass().getName(), city);
		Country country = new Country();
		modelMap.put(country.getClass().getName(), country);
		city.setCountry(country);
		for (int i = 0; i < columnArr.length; i++) {
			String columnValue = columnArr[i];
			IMeta meta = indexMap.get(new Integer(i));
			String entityClassName = meta.getEntityClass();
			Class<?> entityClass = Class.forName(entityClassName);
			IEntity entity = modelMap.get(entityClassName);
			String entitySetter = meta.getEntitySetter();
			Method setter = entityClass.getMethod(entitySetter, String.class);
			setter.invoke(entity, columnValue);
		}
		String uniqueCd = city.getUniqueCd();
		Criteria criteria = session.createCriteria(City.class)
			.add(Restrictions.eq("uniqueCd", uniqueCd));
		City loadedCity = (City)criteria.uniqueResult();
		if (loadedCity == null) {
			String countryCd = country.getCountryCd();
			criteria = session.createCriteria(Country.class)
				.add(Restrictions.eq("countryCd", countryCd));
			Country loadedCountry = (Country)criteria.uniqueResult();
			if (loadedCountry == null)
				session.save(country);
			else
				city.setCountry(loadedCountry);
			session.save(city);
		}
	}
}