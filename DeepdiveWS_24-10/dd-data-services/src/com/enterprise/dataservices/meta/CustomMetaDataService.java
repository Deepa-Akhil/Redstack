package com.enterprise.dataservices.meta;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.Logger;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.domain.entity.meta.CustomMeta;

@Repository(value="customMetaDataService")
public class CustomMetaDataService extends AbstractMetaDataService {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void load(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		Session session = sessionFactory.getCurrentSession();
		CustomMeta customMeta = null;
		try {
			customMeta = this.newCustomMeta(columnName, entity, getter, setter, subType);
			session.save(customMeta);
		} catch (Throwable e) {
			Logger.warning(e.getMessage(), this.getClass());
			session.clear();
		}
	}
	
	private CustomMeta newCustomMeta(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		return (CustomMeta)this.setMetaProperties(new CustomMeta(), columnName, entity, getter, setter, subType);
	}
}