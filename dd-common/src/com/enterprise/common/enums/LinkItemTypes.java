package com.enterprise.common.enums;

public enum LinkItemTypes {
	home ("/home/page", "home", "menu.home"),
	aboutus ("/aboutus/page", "aboutus", "menu.aboutus"),
		career_opportunities ("/aboutus/careers/page", "aboutus", "menu.career_opportunities"),
		latest_projects ("/aboutus/latest_projects/page", "aboutus", "menu.latest_projects"),
	
	services ("/services/page", "services", "menu.services"),
		website_design ("/services/website_design/page", "services", "menu.website_design"),
		web_application_development ("/services/web_application_development/page", "services", "menu.web_application_development"),
		mobile_content_development ("/services/mobile_content_development/page", "services", "menu.mobile_content_development"),
		workflow_system_development ("/services/workflow_system_development/page", "services", "menu.workflow_system_development"),
		search_engine_optimisation ("/services/search_engine_optimisation/page", "services", "menu.search_engine_optimisation"),
		search_engine_marketing ("/services/search_engine_marketing/page", "services", "menu.search_engine_marketing"),
		cloud_hosting_and_administration ("/services/cloud_hosting_and_administration/page", "services", "menu.cloud_hosting_and_administration"),
		domain_name_registration ("/services/domain_name_registration/page", "services", "menu.domain_name_registration"),
		email_setup_and_administration ("/services/email_setup_and_administration/page", "services", "menu.email_setup_and_administration"),
		
	request_a_quote ("/request_a_quote/page", "requestaquote", "menu.request_a_quote"),
	contactus ("/contactus/page", "contactus", "menu.contactus"),
	search_results ("/search/results", "nocache", "menu.search_results");
	
	private final String mapping;
	private final String menuId;
	private final String property;
	
	LinkItemTypes (String mapping, String menuId, String property) {
		this.mapping = mapping;
		this.menuId = menuId;
		this.property = property;
	}

	public String getMapping() {
		return mapping;
	}

	public String getMenuId() {
		return menuId;
	}

	public String getProperty() {
		return property;
	}
}