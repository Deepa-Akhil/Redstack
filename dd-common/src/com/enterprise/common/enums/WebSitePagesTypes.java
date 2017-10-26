package com.enterprise.common.enums;

public enum WebSitePagesTypes {
	LESS_THAN_20_PAGES  ("Less than 20 pages"),
	LESS_THAN_50_PAGES  ("Less than 50 pages"),
	LESS_THAN_100_PAGES ("Less than 100 pages"),
	MORE_THAN_100_PAGES ("More than 100 pages");
	
	private final String description;
	
	WebSitePagesTypes(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public static WebSitePagesTypes valueOfByDescription(String description) {
		for (WebSitePagesTypes item : WebSitePagesTypes.values()) {
			if (item.getDescription().equals(description)) {
				return item;
			}
		}
		return null;
	}
}