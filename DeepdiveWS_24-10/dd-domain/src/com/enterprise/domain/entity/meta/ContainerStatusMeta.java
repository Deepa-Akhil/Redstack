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
 * @author akhil
 */
@Entity
@DiscriminatorValue("com.enterprise.domain.entity.meta.ContainerStatusMeta")
public class ContainerStatusMeta extends Meta implements IMeta, Serializable {
	
	private static final long serialVersionUID = 5182543534377795338L;
	private static final String ENTITY_CLASS = "com.enterprise.domain.entity.ContainerStatus";
	
	public ContainerStatusMeta() {
		super.setEntityClass(ENTITY_CLASS);
	}
	
	@Override
	public IMeta clone() {
		ContainerStatusMeta newInstance = new ContainerStatusMeta();
		newInstance.setColumnName(this.getColumnName());
		newInstance.setEntityClass(this.getEntityClass());
		newInstance.setEntityGetter(this.getEntityGetter());
		newInstance.setEntitySetter(this.getEntitySetter());
		newInstance.setSubType(this.getSubType());
		return newInstance;
	}
}