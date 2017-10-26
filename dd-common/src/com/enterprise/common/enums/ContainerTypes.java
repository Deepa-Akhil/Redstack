package com.enterprise.common.enums;

public enum ContainerTypes {
	_20GP ("20GP", "GENERAL PURPOSE CONT.", "20G0", "GENERAL PURPOSE CONT.", 1);
	
	
	final String typeCode;
	final String typeDescription;
	
	final String sizeCode;
	final String sizeDescription;
	
	final int key;
	
	ContainerTypes(String typeCode, String typeDescription, String sizeCode, String sizeDescription, int key) {
		this.typeCode = typeCode;
		this.typeDescription = typeDescription;
		this.sizeCode = sizeCode;
		this.sizeDescription = sizeDescription;
		this.key = key;
	}
	
	public static ContainerTypes valueOfByTypeCode(String typeCode) {
		ContainerTypes[] containerTypes = ContainerTypes.values();
		for (ContainerTypes containerType : containerTypes)
			if (containerType.getTypeCode().equals(typeCode))
				return containerType;
		return null;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public String getSizeCode() {
		return sizeCode;
	}

	public String getSizeDescription() {
		return sizeDescription;
	}

	public int getKey() {
		return key;
	}
}