package com.enterprise.common.pojo;

import java.util.ArrayList;
import java.util.List;

import com.enterprise.common.enums.LinkItemTypes;
import com.enterprise.common.enums.RequestURITypes;
import com.enterprise.common.util.Constants;

public class HyperlinkBean {
	private static final long serialVersionUID = 2656677875803817600L;
	private final List<LinkItemBean> hyperlinkItems = initHyperlinkItems(); 
	private final List<LinkItemBean> mainMenuItems = initMainMenuItems();
	
	private List<LinkItemBean> initHyperlinkItems() {
		List<LinkItemBean> hyperlinkItems = new ArrayList<LinkItemBean>();
		hyperlinkItems.add(HyperlinkItems.getHomeLinkItem());
		hyperlinkItems.add(HyperlinkItems.getAboutUsLinkItem());
		hyperlinkItems.add(HyperlinkItems.getServicesLinkItem());
		hyperlinkItems.add(HyperlinkItems.getRequestAQuoteLinkItem());
		hyperlinkItems.add(HyperlinkItems.getContactUsLinkItem());
		hyperlinkItems.add(HyperlinkItems.getCareerOpportunitiesLinkItem());
		hyperlinkItems.add(HyperlinkItems.getLatestProjectsLinkItem());
		hyperlinkItems.add(HyperlinkItems.getWebSiteDesignLinkItem());
		hyperlinkItems.add(HyperlinkItems.getWebApplicationDevelopmentLinkItem());
		hyperlinkItems.add(HyperlinkItems.getMobileContentDevelopmentLinkItem());
		hyperlinkItems.add(HyperlinkItems.getWorkflowSystemDevelopmentLinkItem());
		hyperlinkItems.add(HyperlinkItems.getSearchEngineOptimisationLinkItem());
		hyperlinkItems.add(HyperlinkItems.getSearchEngineMarketingLinkItem());
		hyperlinkItems.add(HyperlinkItems.getCloudHostingAndAdministrationLinkItem());
		hyperlinkItems.add(HyperlinkItems.getDomainNameRegistrationLinkItem());
		hyperlinkItems.add(HyperlinkItems.getEmailSetupAndAdministrationLinkItem());		
		return hyperlinkItems;
	}
	
	private List<LinkItemBean> initMainMenuItems() {
		List<LinkItemBean> mainMenuItems = new ArrayList<LinkItemBean>();
		LinkItemBean homeMenuItem = this.getHome();
		mainMenuItems.add(homeMenuItem);
		LinkItemBean aboutUsMenuItem = this.getAboutUs();
		mainMenuItems.add(aboutUsMenuItem);
		LinkItemBean servicesMenuItem = this.getServices();
		mainMenuItems.add(servicesMenuItem);
		LinkItemBean requestAQuoteMenuItem = this.getRequestAQuote();
		mainMenuItems.add(requestAQuoteMenuItem);
		LinkItemBean contactUsMenuItem = this.getContactUs();
		mainMenuItems.add(contactUsMenuItem);
		return mainMenuItems;
	}
	
	public void reset() {
		for (LinkItemBean menuItem : mainMenuItems) {
			if (menuItem != null) {
				menuItem.reset();
			}
		}
	}
	
	public void setActiveMenuItem(RequestURITypes requestType) {
		if (requestType != null) {
			this.reset();
			LinkItemTypes activeMenuItemType = requestType.getMenuItemType();
			LinkItemBean activeMenuItem = fetchMenuItemByMenuId(activeMenuItemType.getMenuId());
			if (activeMenuItem != null) {
				activeMenuItem.setStyleClass(Constants.ACTIVE_STR);
			}
		}
	}
	
	private LinkItemBean fetchMenuItemByMenuId(String menuId) {
		for (LinkItemBean menuItem : mainMenuItems) {
			if (menuItem.getMenuId().equals(menuId)) {
				return menuItem;
			}
		}
		return null;
	}
	
	private LinkItemBean fetchLinkItemByName(String name) {
		for (LinkItemBean linkItem : hyperlinkItems) {
			if (linkItem.getName().equals(name)) {
				return linkItem;
			}
		}
		return null;
	}
	
	public List<LinkItemBean> getMainMenuItems() {
		return mainMenuItems;
	}

	public List<LinkItemBean> getHyperlinkItems() {
		return hyperlinkItems;
	}
	
	public LinkItemBean getHome() {
		return fetchLinkItemByName(LinkItemTypes.home.name());
	}
	
	public LinkItemBean getAboutUs() {
		return fetchLinkItemByName(LinkItemTypes.aboutus.name());
	}
	
	public LinkItemBean getCareerOpportunities() {
		return fetchLinkItemByName(LinkItemTypes.career_opportunities.name());
	}
	
	public LinkItemBean getLatestProjects() {
		return fetchLinkItemByName(LinkItemTypes.latest_projects.name());
	}
	
	public LinkItemBean getServices() {
		return fetchLinkItemByName(LinkItemTypes.services.name());
	}
	
	public LinkItemBean getRequestAQuote() {
		return fetchLinkItemByName(LinkItemTypes.request_a_quote.name());
	}
	
	public LinkItemBean getContactUs() {
		return fetchLinkItemByName(LinkItemTypes.contactus.name());
	}
	
	public LinkItemBean getWebSiteDesign() {
		return fetchLinkItemByName(LinkItemTypes.website_design.name());
	}

	public LinkItemBean getWebApplicationDevelopment() {
		return fetchLinkItemByName(LinkItemTypes.web_application_development.name());
	}
	
	public LinkItemBean getMobileContentDevelopment() {
		return fetchLinkItemByName(LinkItemTypes.mobile_content_development.name());
	}
	
	public LinkItemBean getWorkflowSystemDevelopment() {
		return fetchLinkItemByName(LinkItemTypes.workflow_system_development.name());
	}
	
	public LinkItemBean getSearchEngineOptimisation() {
		return fetchLinkItemByName(LinkItemTypes.search_engine_optimisation.name());
	}
	
	public LinkItemBean getSearchEngineMarketing() {
		return fetchLinkItemByName(LinkItemTypes.search_engine_marketing.name());
	}
	
	public LinkItemBean getCloudHostingAndAdministration() {
		return fetchLinkItemByName(LinkItemTypes.cloud_hosting_and_administration.name());
	}
	
	public LinkItemBean getDomainNameRegistration() {
		return fetchLinkItemByName(LinkItemTypes.domain_name_registration.name());
	}
	
	public LinkItemBean getEmailSetupAndAdministration() {
		return fetchLinkItemByName(LinkItemTypes.email_setup_and_administration.name());
	}
}