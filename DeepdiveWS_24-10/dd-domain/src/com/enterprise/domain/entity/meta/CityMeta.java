package com.enterprise.domain.entity.meta;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.enterprise.common.entity.IMeta;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  11 Mar 2014 3:08:21 PM
 * @author rafourie
 */
@Entity
@DiscriminatorValue("com.enterprise.domain.entity.meta.CityMeta")
public class CityMeta extends Meta implements IMeta, Serializable {

	private static final long serialVersionUID = -5252135771039218783L;
	private static final String ENTITY_CLASS = "com.enterprise.domain.entity.City";
	
	public CityMeta() {
		super.setEntityClass(ENTITY_CLASS);
	}

	@Override
	public IMeta clone() {
		CityMeta newInstance = new CityMeta();
		newInstance.setColumnName(this.getColumnName());
		newInstance.setEntityClass(this.getEntityClass());
		newInstance.setEntityGetter(this.getEntityGetter());
		newInstance.setEntitySetter(this.getEntitySetter());
		newInstance.setSubType(this.getSubType());
		return newInstance;
	}
}