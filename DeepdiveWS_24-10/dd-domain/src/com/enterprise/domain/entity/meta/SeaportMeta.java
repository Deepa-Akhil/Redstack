package com.enterprise.domain.entity.meta;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.enterprise.common.entity.IMeta;

@Entity
@DiscriminatorValue("com.enterprise.domain.entity.meta.SeaportMeta")
public class SeaportMeta extends Meta implements IMeta, Serializable {

	private static final long serialVersionUID = -6533085041006640041L;

	private static final String ENTITY_CLASS = "com.enterprise.domain.entity.Seaport";
	
	
	public SeaportMeta() {
		super.setEntityClass(ENTITY_CLASS);
	}
	
	@Override
	public IMeta clone() {
		SeaportMeta newInstance = new SeaportMeta();
		newInstance.setColumnName(this.getColumnName());
		newInstance.setEntityClass(this.getEntityClass());
		newInstance.setEntityGetter(this.getEntityGetter());
		newInstance.setEntitySetter(this.getEntitySetter());
		newInstance.setSubType(this.getSubType());
		return newInstance;
	}
}