package com.enterprise.common.entity;

import com.enterprise.common.enums.SubTypes;
//import com.enterprise.domain.entity.Package;
/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  11 Mar 2014 5:54:17 PM
 * @author rafourie
 */
public interface IMeta extends IEntity {
	public abstract String getEntityClass();
	public abstract String getEntitySetter();
	public abstract String getColumnName();
	public abstract SubTypes getSubType();
	public abstract void setSubType(SubTypes subType);
	public abstract void setColumnName(String name);
	public abstract IMeta clone() throws RuntimeException;
	public abstract void setParent(IMeta parent);
	public abstract IMeta getParent();
	public abstract String getEntityGetter();
	public abstract void setEntityGetter(String entityGetter);
	public abstract String getDiscriminatorValue();
	public abstract long getId();
	public abstract void setId(long id);
	/*public abstract void setPkg(Package pkg);
	public abstract Package getPkg();*/
}