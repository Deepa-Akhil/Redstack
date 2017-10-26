package com.enterprise.dataservices.meta;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.Logger;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.domain.entity.meta.CityMeta;

@Repository(value="cityMetaDataService")
public class CityMetaDataService extends AbstractMetaDataService {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void load(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		Session session = sessionFactory.getCurrentSession();
		CityMeta cityMeta = null;
		try {
			cityMeta = this.newCityMeta(columnName, entity, getter, setter, subType);
			session.save(cityMeta);
		} catch (Throwable e) {
			Logger.warning(e.getMessage(), this.getClass());
			session.clear();
		}
	}
	
	private CityMeta newCityMeta(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		return (CityMeta)this.setMetaProperties(new CityMeta(), columnName, entity, getter, setter, subType);
	}
}