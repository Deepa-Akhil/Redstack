package com.enterprise.domain.entity.meta;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.enterprise.common.entity.IMeta;

@Entity
@DiscriminatorValue("com.enterprise.domain.entity.meta.InvoiceMeta")
public class InvoiceMeta extends Meta implements IMeta, Serializable {
	
	private static final long serialVersionUID = 8367291095260008785L;

	private static final String ENTITY_CLASS = "com.enterprise.domain.entity.Invoice";
	
	
	public InvoiceMeta() {
		super.setEntityClass(ENTITY_CLASS);
	}
	
	@Override
	public IMeta clone() {
		InvoiceMeta newInstance = new InvoiceMeta();
		newInstance.setColumnName(this.getColumnName());
		newInstance.setEntityClass(this.getEntityClass());
		newInstance.setEntityGetter(this.getEntityGetter());
		newInstance.setEntitySetter(this.getEntitySetter());
		newInstance.setSubType(this.getSubType());
		return newInstance;
	}
}