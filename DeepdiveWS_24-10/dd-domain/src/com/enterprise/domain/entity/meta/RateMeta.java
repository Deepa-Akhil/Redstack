package com.enterprise.domain.entity.meta;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.enterprise.common.entity.IMeta;

@Entity
@DiscriminatorValue("com.enterprise.domain.entity.meta.RateMeta")
public class RateMeta extends Meta implements IMeta, Serializable {

	private static final long serialVersionUID = -525983580310677299L;

	private static final String ENTITY_CLASS = "com.enterprise.domain.entity.Rate";
	
	public RateMeta() {
		super.setEntityClass(ENTITY_CLASS);
	}
	
	@Override
	public IMeta clone() {
		RateMeta newInstance = new RateMeta();
		newInstance.setColumnName(this.getColumnName());
		newInstance.setEntityClass(this.getEntityClass());
		newInstance.setEntityGetter(this.getEntityGetter());
		newInstance.setEntitySetter(this.getEntitySetter());
		newInstance.setSubType(this.getSubType());
		return newInstance;
	}
}
