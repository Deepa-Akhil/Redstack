package com.enterprise.dataservices.meta;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.Logger;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.domain.entity.meta.SeaportMeta;

@Repository(value="seaportMetaDataService")
public class SeaportMetaDataService extends AbstractMetaDataService {

	@Autowired
	protected SessionFactory sessionFactory;
	
	public void load(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		Session session = sessionFactory.getCurrentSession();
		SeaportMeta meta = null;
		try {
			meta = this.newSeaportMeta(columnName, entity, getter, setter, subType);
			session.save(meta);
		} catch (Throwable e) {
			Logger.warning(e.getMessage(), this.getClass());
			session.clear();
		}
	}
	
	private SeaportMeta newSeaportMeta(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		return (SeaportMeta)this.setMetaProperties(new SeaportMeta(), columnName, entity, getter, setter, subType);
	}
}