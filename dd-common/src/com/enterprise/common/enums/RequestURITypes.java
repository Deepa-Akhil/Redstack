package com.enterprise.common.enums;

public enum RequestURITypes {
	root ("/", LanguageTypes.en, LinkItemTypes.home),
	root_index ("/index.jsp", LanguageTypes.en, LinkItemTypes.home),
	
	home ("/jsp/home.jsp", LanguageTypes.en, LinkItemTypes.home),
	
	aboutus ("/jsp/aboutus/page.jsp", LanguageTypes.en, LinkItemTypes.aboutus),
		career_opportunities ("/jsp/aboutus/careers/page.jsp", LanguageTypes.en, LinkItemTypes.career_opportunities),
		latest_projects ("/jsp/aboutus/latest_projects/page.jsp", LanguageTypes.en, LinkItemTypes.latest_projects),
	
	services ("/jsp/services/page.jsp", LanguageTypes.en, LinkItemTypes.services),
		website_design ("/jsp/services/website_design/page.jsp", LanguageTypes.en, LinkItemTypes.website_design),
		web_application_development ("/jsp/services/web_application_development/page.jsp", LanguageTypes.en, LinkItemTypes.web_application_development),
		mobile_content_development ("/jsp/services/mobile_content_development/page.jsp", LanguageTypes.en, LinkItemTypes.mobile_content_development),
		workflow_system_development ("/jsp/services/workflow_system_development/page.jsp", LanguageTypes.en, LinkItemTypes.workflow_system_development),
		search_engine_optimisation ("/jsp/services/search_engine_optimisation/page.jsp", LanguageTypes.en, LinkItemTypes.search_engine_optimisation),
		search_engine_marketing ("/jsp/services/search_engine_marketing/page.jsp", LanguageTypes.en, LinkItemTypes.search_engine_marketing),
		cloud_hosting_and_administration ("/jsp/services/cloud_hosting_and_administration/page.jsp", LanguageTypes.en, LinkItemTypes.cloud_hosting_and_administration),
		domain_name_registration ("/jsp/services/domain_name_registration/page.jsp", LanguageTypes.en, LinkItemTypes.domain_name_registration),
		email_setup_and_administration ("/jsp/services/email_setup_and_administration/page.jsp", LanguageTypes.en, LinkItemTypes.email_setup_and_administration),
	
	request_a_quote ("/jsp/request_a_quote/page.jsp", LanguageTypes.en, LinkItemTypes.request_a_quote),
	contactus ("/jsp/contactus/page.jsp", LanguageTypes.en, LinkItemTypes.contactus);
	
	private final String requestURI;
	private final LanguageTypes lang;
	private final LinkItemTypes menuItemType;
	
	RequestURITypes(String requestURI, LanguageTypes lang, LinkItemTypes menuItemType) {
		this.requestURI = requestURI;
		this.lang = lang;
		this.menuItemType = menuItemType;
	}
	
	public static RequestURITypes valueOfByRequestURI(String requestURI) {
		RequestURITypes[] values = RequestURITypes.values();
		for (RequestURITypes requestUriType : values) {
			if (requestUriType.getRequestURI().equals(requestURI)) {
				return requestUriType;
			}
		}
		return null;
	}
	
	public static RequestURITypes valueOfByMenuItemType(LinkItemTypes menuItemType) {
		RequestURITypes[] values = RequestURITypes.values();
		for (RequestURITypes requestUriType : values) {
			if (requestUriType.getMenuItemType().equals(menuItemType)) {
				return requestUriType;
			}
		}
		return null;
	}

	public LanguageTypes getLang() {
		return lang;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public LinkItemTypes getMenuItemType() {
		return menuItemType;
	}
	
	public String getRequestMapping() {
		return menuItemType.getMapping();
	}
}