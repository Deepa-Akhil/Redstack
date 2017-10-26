package com.enterprise.dataservices.meta;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.Logger;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.domain.entity.meta.RateMeta;

@Repository(value="rateMetaDataService")
public class RateMetaDataService extends AbstractMetaDataService {

	@Autowired
	protected SessionFactory sessionFactory;
	
	public void load(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		Session session = sessionFactory.getCurrentSession();
		RateMeta rateMeta = null;
		try {
			rateMeta = this.newRateMeta(columnName, entity, getter, setter, subType);
			session.save(rateMeta);
		} catch (Throwable e) {
			Logger.warning(e.getMessage(), this.getClass());
			session.clear();
		}
	}
	
	private RateMeta newRateMeta(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		return (RateMeta)this.setMetaProperties(new RateMeta(), columnName, entity, getter, setter, subType);
	}
}
