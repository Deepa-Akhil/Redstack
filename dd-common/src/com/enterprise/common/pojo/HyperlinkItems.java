package com.enterprise.common.pojo;

import com.enterprise.common.enums.LinkItemTypes;
import com.enterprise.common.util.Constants;

public final class HyperlinkItems {
	private static final LinkItemBean homeLinkItem = new LinkItemBean(LinkItemTypes.home, Constants.ACTIVE_STR);
	private static final LinkItemBean aboutUsLinkItem = new LinkItemBean(LinkItemTypes.aboutus);
	private static final LinkItemBean servicesLinkItem = new LinkItemBean(LinkItemTypes.services);
	private static final LinkItemBean requestAQuoteLinkItem = new LinkItemBean(LinkItemTypes.request_a_quote);
	private static final LinkItemBean contactUsLinkItem = new LinkItemBean(LinkItemTypes.contactus);
	private static final LinkItemBean careerOpportunitiesLinkItem = new LinkItemBean(LinkItemTypes.career_opportunities);
	private static final LinkItemBean latestProjectsLinkItem = new LinkItemBean(LinkItemTypes.latest_projects);
	private static final LinkItemBean searchResultsLinkItem = new LinkItemBean(LinkItemTypes.search_results);
	
	private static final LinkItemBean webSiteDesignLinkItem = new LinkItemBean(LinkItemTypes.website_design);
	private static final LinkItemBean webApplicationDevelopmentLinkItem = new LinkItemBean(LinkItemTypes.web_application_development);
	private static final LinkItemBean mobileContentDevelopmentLinkItem = new LinkItemBean(LinkItemTypes.mobile_content_development);
	private static final LinkItemBean workflowSystemDevelopmentLinkItem = new LinkItemBean(LinkItemTypes.workflow_system_development);
	private static final LinkItemBean searchEngineOptimisationLinkItem = new LinkItemBean(LinkItemTypes.search_engine_optimisation);
	private static final LinkItemBean searchEngineMarketingLinkItem = new LinkItemBean(LinkItemTypes.search_engine_marketing);
	private static final LinkItemBean cloudHostingAndAdministrationLinkItem = new LinkItemBean(LinkItemTypes.cloud_hosting_and_administration);
	private static final LinkItemBean domainNameRegistrationLinkItem = new LinkItemBean(LinkItemTypes.domain_name_registration);
	private static final LinkItemBean emailSetupAndAdministrationLinkItem = new LinkItemBean(LinkItemTypes.email_setup_and_administration);
	
	public static LinkItemBean getHomeLinkItem() {
		return homeLinkItem;
	}

	public static LinkItemBean getAboutUsLinkItem() {
		return aboutUsLinkItem;
	}

	public static LinkItemBean getServicesLinkItem() {
		return servicesLinkItem;
	}

	public static LinkItemBean getRequestAQuoteLinkItem() {
		return requestAQuoteLinkItem;
	}

	public static LinkItemBean getContactUsLinkItem() {
		return contactUsLinkItem;
	}

	public static LinkItemBean getCareerOpportunitiesLinkItem() {
		return careerOpportunitiesLinkItem;
	}

	public static LinkItemBean getLatestProjectsLinkItem() {
		return latestProjectsLinkItem;
	}

	public static LinkItemBean getSearchResultsLinkItem() {
		return searchResultsLinkItem;
	}

	public static LinkItemBean getWebSiteDesignLinkItem() {
		return webSiteDesignLinkItem;
	}

	public static LinkItemBean getWebsitedesignlinkitem() {
		return webSiteDesignLinkItem;
	}

	public static LinkItemBean getWebApplicationDevelopmentLinkItem() {
		return webApplicationDevelopmentLinkItem;
	}

	public static LinkItemBean getMobileContentDevelopmentLinkItem() {
		return mobileContentDevelopmentLinkItem;
	}

	public static LinkItemBean getWorkflowSystemDevelopmentLinkItem() {
		return workflowSystemDevelopmentLinkItem;
	}

	public static LinkItemBean getSearchEngineOptimisationLinkItem() {
		return searchEngineOptimisationLinkItem;
	}

	public static LinkItemBean getSearchEngineMarketingLinkItem() {
		return searchEngineMarketingLinkItem;
	}

	public static LinkItemBean getCloudHostingAndAdministrationLinkItem() {
		return cloudHostingAndAdministrationLinkItem;
	}

	public static LinkItemBean getDomainNameRegistrationLinkItem() {
		return domainNameRegistrationLinkItem;
	}

	public static LinkItemBean getEmailSetupAndAdministrationLinkItem() {
		return emailSetupAndAdministrationLinkItem;
	}
}