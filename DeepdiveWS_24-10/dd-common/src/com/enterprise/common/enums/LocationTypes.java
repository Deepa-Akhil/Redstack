package com.enterprise.common.enums;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  17 Mar 2014 7:47:59 AM
 * @author rafourie
 */
public enum LocationTypes {
	City,
	Branch,
	Port;
	
	LocationTypes() {/* no implementation */}
	
	public String getName() {
		return this.name();
	}
}