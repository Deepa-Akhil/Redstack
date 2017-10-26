package com.enterprise.common.enums;

public enum ProvinceTypes {
	WesternCape ("Western Cape"),
	EasternCape ("Eastern Cape"),
	NorthernCape ("Northern Cape"),
	KwaZuluNatal ("Kwa-Zulu Natal"),
	FreeState ("Free State"),
	NorthWest ("North West"),
	Gauteng ("Gauteng"),
	Limpopo ("Limpopo"),
	Mpumalanga ("Mpumalanga");
	
	private final String description;
	
	ProvinceTypes(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}