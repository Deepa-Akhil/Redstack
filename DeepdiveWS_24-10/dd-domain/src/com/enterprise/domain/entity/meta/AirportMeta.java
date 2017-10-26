package com.enterprise.domain.entity.meta;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.enterprise.common.entity.IMeta;

@Entity
@DiscriminatorValue("com.enterprise.domain.entity.meta.AirportMeta")
public class AirportMeta extends Meta implements IMeta, Serializable {

	private static final long serialVersionUID = -8516956528841099774L;

	private static final String ENTITY_CLASS = "com.enterprise.domain.entity.Airport";
	
	
	public AirportMeta() {
		super.setEntityClass(ENTITY_CLASS);
	}
	
	@Override
	public IMeta clone() {
		AirportMeta newInstance = new AirportMeta();
		newInstance.setColumnName(this.getColumnName());
		newInstance.setEntityClass(this.getEntityClass());
		newInstance.setEntityGetter(this.getEntityGetter());
		newInstance.setEntitySetter(this.getEntitySetter());
		newInstance.setSubType(this.getSubType());
		return newInstance;
	}
}