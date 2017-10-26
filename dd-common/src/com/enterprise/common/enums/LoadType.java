package com.enterprise.common.enums;

public enum LoadType {
	S ("Shipment"),
	R ("Rate"),
	I ("Invoice"),
	O ("Order"),
	C ("Custom"),
	CS ("Container status");
	
	final String load;
	
	LoadType(final String load) {
		this.load = load;
	}
	
	public static LoadType valueOfByMode(String load) {
		for (LoadType loadType : LoadType.values())
			if (loadType.getLoad().equals(load))
				return loadType;
		return null;
	}

	public String getLoad() {
		return load;
	}
}
