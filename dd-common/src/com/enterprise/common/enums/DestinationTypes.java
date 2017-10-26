package com.enterprise.common.enums;

public enum DestinationTypes {
	Orig,
	Dest;
	
	DestinationTypes() {/* no implementation */}
	
	public String getName() {
		return this.name();
	}
}