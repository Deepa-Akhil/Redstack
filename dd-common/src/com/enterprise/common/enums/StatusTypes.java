package com.enterprise.common.enums;

public enum StatusTypes {
	Active ("Active", "ACTIVE"),
	InActive ("Inactive", "INACTIVE"),
	Successful ("Successful", "SUCCESSFUL"),
	Failed ("Failed", "FAILED"),
	Reserved ("Reserved", "RESERVED"),
	InProgress ("InProgress", "INPROGRESS"),
	Completed ("Completed", "COMPLETED");
	
	private final String description;
	private final String xsdName;
	
	StatusTypes(String description, String xsdName) {
		this.description = description;
		this.xsdName = xsdName;
	}
	
	public static StatusTypes valueOfByXsdName(String xsdName) {
		StatusTypes[] values = StatusTypes.values();
		for (StatusTypes statusTypes : values) {
			if (statusTypes.getXsdName().equals(xsdName)) {
				return statusTypes;
			}
		}
		return null;
	}

	public String getDescription() {
		return description;
	}

	public String getXsdName() {
		return xsdName;
	}
	
	public String getName() {
		return this.name();
	}
}