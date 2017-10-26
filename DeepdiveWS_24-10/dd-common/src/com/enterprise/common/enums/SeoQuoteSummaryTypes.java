package com.enterprise.common.enums;

public enum SeoQuoteSummaryTypes {
	LESS_THAN_20_PAGES (QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_LESS_THAN_20_PAGES, WebSitePagesTypes.LESS_THAN_20_PAGES),
	LESS_THAN_50_PAGES (QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_LESS_THAN_50_PAGES, WebSitePagesTypes.LESS_THAN_50_PAGES),
	LESS_THAN_100_PAGES (QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_LESS_THAN_100_PAGES, WebSitePagesTypes.LESS_THAN_100_PAGES),
	MORE_THAN_100_PAGES (QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_MORE_THAN_100_PAGES, WebSitePagesTypes.MORE_THAN_100_PAGES);
	
	private final QuoteSummaryTypes quoteSummaryType;
	private final WebSitePagesTypes webSitePagesType;
	
	SeoQuoteSummaryTypes(QuoteSummaryTypes quoteSummaryType, WebSitePagesTypes webSitePagesType) {
		this.quoteSummaryType = quoteSummaryType;
		this.webSitePagesType = webSitePagesType;
	}
	
	public static SeoQuoteSummaryTypes valueOfByWebSitePagesTypes(WebSitePagesTypes webSitePagesTypes) {
		for (SeoQuoteSummaryTypes oType : SeoQuoteSummaryTypes.values()) {
			if (oType.getWebSitePagesType().equals(webSitePagesTypes)) {
				return oType;
			}
		}
		return null;
	}

	public QuoteSummaryTypes getQuoteSummaryType() {
		return quoteSummaryType;
	}

	public WebSitePagesTypes getWebSitePagesType() {
		return webSitePagesType;
	}
}