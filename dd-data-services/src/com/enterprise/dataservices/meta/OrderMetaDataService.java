package com.enterprise.dataservices.meta;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.Logger;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.domain.entity.meta.OrderMeta;

@Repository(value="orderMetaDataService")
public class OrderMetaDataService extends AbstractMetaDataService{

	@Autowired
	protected SessionFactory sessionFactory;
	
	public void load(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		Session session = sessionFactory.getCurrentSession();
		OrderMeta orderMeta = null;
		try {
			orderMeta = this.newOrderMeta(columnName, entity, getter, setter, subType);
			session.save(orderMeta);
		} catch (Throwable e) {
			Logger.warning(e.getMessage(), this.getClass());
			session.clear();
		}
	}
	
	private OrderMeta newOrderMeta(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		return (OrderMeta)this.setMetaProperties(new OrderMeta(), columnName, entity, getter, setter, subType);
	}
}
