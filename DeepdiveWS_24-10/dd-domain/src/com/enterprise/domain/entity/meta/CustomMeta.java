package com.enterprise.domain.entity.meta;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.enterprise.common.entity.IMeta;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  03 Mar 2014 12:23:18 PM
 * @author rafourie
 */
@Entity
@DiscriminatorValue("com.enterprise.domain.entity.meta.CustomMeta")
public class CustomMeta extends Meta implements IMeta, Serializable {
	
	private static final long serialVersionUID = 518259320377795338L;
	private static final String ENTITY_CLASS = "com.enterprise.domain.entity.Custom";
	
	public CustomMeta() {
		super.setEntityClass(ENTITY_CLASS);
	}
	
	@Override
	public IMeta clone() {
		CustomMeta newInstance = new CustomMeta();
		newInstance.setColumnName(this.getColumnName());
		newInstance.setEntityClass(this.getEntityClass());
		newInstance.setEntityGetter(this.getEntityGetter());
		newInstance.setEntitySetter(this.getEntitySetter());
		newInstance.setSubType(this.getSubType());
		return newInstance;
	}
}