package com.enterprise.dataservices.meta;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.Logger;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.domain.entity.meta.AirCarrierMeta;

@Repository(value="airCarrierMetaDataService")
public class AirCarrierMetaDataService extends AbstractMetaDataService {

	@Autowired
	protected SessionFactory sessionFactory;
	
	public void load(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		Session session = sessionFactory.getCurrentSession();
		AirCarrierMeta meta = null;
		try {
			meta = this.newAirCarrierMeta(columnName, entity, getter, setter, subType);
			session.save(meta);
		} catch (Throwable e) {
			Logger.warning(e.getMessage(), this.getClass());
			session.clear();
		}
	}
	
	private AirCarrierMeta newAirCarrierMeta(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		return (AirCarrierMeta)this.setMetaProperties(new AirCarrierMeta(), columnName, entity, getter, setter, subType);
	}
}