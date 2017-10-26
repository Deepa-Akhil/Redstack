package com.enterprise.dataservices.meta;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.Logger;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.domain.entity.meta.ShipmentMeta;

@Repository(value="shipmentMetaDataService")
public class ShipmentMetaDataService extends AbstractMetaDataService {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void load(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		Session session = sessionFactory.getCurrentSession();
		ShipmentMeta shipmentMeta = null;
		try {
			shipmentMeta = this.newShipmentMeta(columnName, entity, getter, setter, subType);
			session.save(shipmentMeta);
		} catch (Throwable e) {
			Logger.warning(e.getMessage(), this.getClass());
			session.clear();
		}
	}
	
	private ShipmentMeta newShipmentMeta(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		return (ShipmentMeta)this.setMetaProperties(new ShipmentMeta(), columnName, entity, getter, setter, subType);
	}
}