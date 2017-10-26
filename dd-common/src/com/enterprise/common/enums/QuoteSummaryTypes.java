package com.enterprise.common.enums;

import java.util.ArrayList;
import java.util.List;

public enum QuoteSummaryTypes {
	PERSONAL_DETAILS (null, "Personal Details", null, false, 1100),
	
	WEBSITE_DESIGN (null, "Website Design", "195", true, 2100),
		LESS_THAN_20_PAGES (QuoteSummaryTypes.WEBSITE_DESIGN, "Less than 20 pages", null, false, 2101),
		LESS_THAN_50_PAGES (QuoteSummaryTypes.WEBSITE_DESIGN, "Less than 50 pages", "75", true, 2102),
		LESS_THAN_100_PAGES (QuoteSummaryTypes.WEBSITE_DESIGN, "Less than 100 pages", "150", true, 2103),
		MORE_THAN_100_PAGES (QuoteSummaryTypes.WEBSITE_DESIGN, "More than 100 pages", "250", true, 2104),
		ADVANCED_MENU_STRUCTURES (QuoteSummaryTypes.WEBSITE_DESIGN, "Advanced Menus", null, false, 2105),
		LOGIN_AUTHENTICATION (QuoteSummaryTypes.WEBSITE_DESIGN, "Authentication", "95", true, 2106),
		MULTI_LANGUAGE_SUPPORT (QuoteSummaryTypes.WEBSITE_DESIGN, "Multi-Language", "45", true, 2107),
		JQUERY_IMAGE_SLIDER (QuoteSummaryTypes.WEBSITE_DESIGN, "JQuery Image Slider(s)", null, false, 2108),
		SOCIAL_MEDIA_INTEGRATION (QuoteSummaryTypes.WEBSITE_DESIGN, "Social Media Integration", null, false, 2109),
		GOOGLE_MAPS_INTEGRATION (QuoteSummaryTypes.WEBSITE_DESIGN, "Google Maps Integration", null, false, 2110),
		EMAIL_INTEGRATION (QuoteSummaryTypes.WEBSITE_DESIGN, "Email Integration", "95", true, 2111),
		
	MOBILE_CONTENT (null, "Mobile Content", "195", true, 2200),
	
	ENTERPRISE_FEATURES (null, "Enterprise Features", null, false, 2300),
		B2B_INTEGRATION (QuoteSummaryTypes.ENTERPRISE_FEATURES, "B2B Integration", "995", true, 2301),
		DATA_DRIVEN_SERVICES (QuoteSummaryTypes.ENTERPRISE_FEATURES, "Data Services", "795", true, 2302),
		DATABASE_DESIGN (QuoteSummaryTypes.ENTERPRISE_FEATURES, "Database (Domain) Design", "995", true, 2303),
		
	WORKFLOW_SOLUTION (null, "Workflow Solution", "1495", true, 2400),
	
	SEARCH_ENGINE_OPTIMISATION_LESS_THAN_20_PAGES (null, "Search Engine Optimisation", "95", true, 3100),
	SEARCH_ENGINE_OPTIMISATION_LESS_THAN_50_PAGES (null, "Search Engine Optimisation", "145", true, 3100),
	SEARCH_ENGINE_OPTIMISATION_LESS_THAN_100_PAGES (null, "Search Engine Optimisation", "195", true, 3100),
	SEARCH_ENGINE_OPTIMISATION_MORE_THAN_100_PAGES (null, "Search Engine Optimisation", "395", true, 3100),
	
	DIRECTORY_PLACEMENTS_AND_PUBLIC_RELATIONS (null, "Internet Marketing", "95", true, 3200),
	
	PAY_PER_CLICK (null, "Pay Per Click (PPC)", null, false, 3300),
	
	STANDARD_EMAIL_ADMINISTRATION (null, "Standard Email Administration", null, false, 4100),
		STANDARD_EMAIL_MAILBOXES (QuoteSummaryTypes.STANDARD_EMAIL_ADMINISTRATION, "Mailboxes (Standard)", "30", true, 4101),
		STANDARD_EMAIL_MOBILE_SYNC (QuoteSummaryTypes.STANDARD_EMAIL_ADMINISTRATION, "Mobile Sync", "8", true, 4102),
		
	EXCHANGE_EMAIL_ADMINISTRATION (null, "Exchange Email Administration", null, false, 4200),
		EXCHANGE_EMAIL_MAILBOXES (QuoteSummaryTypes.EXCHANGE_EMAIL_ADMINISTRATION, "Mailboxes (Exchange)", "120", true, 4201);
	
	private final QuoteSummaryTypes parent;
	private final String description;
	private final String price;
	private final boolean billable;
	private final int weight;
	
	QuoteSummaryTypes(QuoteSummaryTypes parent, String description, String price, boolean billable, int weight) {
		this.parent = parent;
		this.description = description;
		this.price = price;
		this.billable = billable;
		this.weight = weight;
	}
	
	public static QuoteSummaryTypes valueOfByDescription(String description) {
		for (QuoteSummaryTypes item : QuoteSummaryTypes.values()) {
			if (item.getDescription().equals(description)) {
				return item;
			}
		}
		return null;
	}
	
	public static List<QuoteSummaryTypes> valuesByParent(QuoteSummaryTypes parent) {
		List<QuoteSummaryTypes> values = new ArrayList<QuoteSummaryTypes>();
		for (QuoteSummaryTypes quote : QuoteSummaryTypes.values()) {
			QuoteSummaryTypes quoteParent = quote.getParent();
			if (quoteParent != null && quoteParent.equals(parent)) {
				values.add(quote);
			}
		}
		return values;
	}
	
	public static List<QuoteSummaryTypes> valuesByWebSitePagesTypes(WebSitePagesTypes[] pagesTypes) {
		List<QuoteSummaryTypes> values = new ArrayList<QuoteSummaryTypes>();
		for (QuoteSummaryTypes quote : QuoteSummaryTypes.values()) {
			for (WebSitePagesTypes page : pagesTypes) {
				if (quote.getDescription().equals(page.getDescription())) {
					values.add(quote);
					break;
				}
			}
		}
		return values;
	}

	public String getDescription() {
		return description;
	}

	public String getPrice() {
		return price;
	}

	public boolean isBillable() {
		return billable;
	}

	public QuoteSummaryTypes getParent() {
		return parent;
	}

	public int getWeight() {
		return weight;
	}
}