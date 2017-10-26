package com.enterprise.common.enums;

public enum BooleanTypes {
	Y (true),
	N (false);
	
	private final boolean booleanValue;
	
	BooleanTypes(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public boolean isBooleanValue() {
		return booleanValue;
	}
	
	public static BooleanTypes valueOfByBooleanValue(boolean booleanValue) {
		for (BooleanTypes validityType : BooleanTypes.values()) {
			if (validityType.isBooleanValue() == booleanValue) {
				return validityType;
			}
		}
		return null;
	}
	
	public String getName() {
		return name();
	}
}