package com.enterprise.dataservices.meta;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.Logger;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.domain.entity.meta.InvoiceMeta;

@Repository(value="invoiceMetaDataService")
public class InvoiceMetaDataService extends AbstractMetaDataService {

	@Autowired
	protected SessionFactory sessionFactory;
	
	public void load(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		Session session = sessionFactory.getCurrentSession();
		InvoiceMeta invoiceMeta = null;
		try {
			invoiceMeta = this.newInvoiceMeta(columnName, entity, getter, setter, subType);
			session.save(invoiceMeta);
		} catch (Throwable e) {
			Logger.warning(e.getMessage(), this.getClass());
			session.clear();
		}
	}
	
	private InvoiceMeta newInvoiceMeta(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		return (InvoiceMeta)this.setMetaProperties(new InvoiceMeta(), columnName, entity, getter, setter, subType);
	}
}