package com.enterprise.domain.entity.meta;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.enterprise.common.entity.IMeta;

@Entity
@DiscriminatorValue("com.enterprise.domain.entity.meta.SeaCarrierMeta")
public class SeaCarrierMeta extends Meta implements IMeta, Serializable {

	private static final long serialVersionUID = -4688953356977665736L;

	private static final String ENTITY_CLASS = "com.enterprise.domain.entity.SeaCarrier";
	
	
	public SeaCarrierMeta() {
		super.setEntityClass(ENTITY_CLASS);
	}
	
	@Override
	public IMeta clone() {
		SeaCarrierMeta newInstance = new SeaCarrierMeta();
		newInstance.setColumnName(this.getColumnName());
		newInstance.setEntityClass(this.getEntityClass());
		newInstance.setEntityGetter(this.getEntityGetter());
		newInstance.setEntitySetter(this.getEntitySetter());
		newInstance.setSubType(this.getSubType());
		return newInstance;
	}
}