package com.enterprise.common.pojo.quote;

import java.io.Serializable;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.enums.InfoMessageTypes;
import com.enterprise.common.pojo.InfoMessageBean;

public class AppDevelopmentBean implements Serializable, IStepable {
	private static final long serialVersionUID = 8983728597900310864L;
	private final String requestMapping = "/request_a_quote/input/app_development/body";
	private final InfoMessageBean infoMessageBean = new InfoMessageBean();
	private final WebSiteSectionBean webSiteSectionBean;
	private final EnterpriseSectionBean enterpriseSectionBean;
	private final MobileContentSectionBean mobileContentSectionBean;
	private final WorkflowSectionBean workflowSectionBean;
	private int index = 0;
	
	public AppDevelopmentBean(int index, QuoteSummaryBean quoteSummaryBean) {
		this.index = index;
		String message = new String(
			"The main sections are all unselected by default.<br/>" + 
			"Check the box next to the section should it fall under your requirements.<br/>" + 
			"Fields marked with (<span class='font-red'>*</span>) indicates additional cost.<br/>" + 
			"Please give us as much information as possible."
		);
		infoMessageBean.setMessage(message, InfoMessageTypes.Info);
		webSiteSectionBean = new WebSiteSectionBean(quoteSummaryBean);
		enterpriseSectionBean = new EnterpriseSectionBean(quoteSummaryBean);
		mobileContentSectionBean = new MobileContentSectionBean(quoteSummaryBean);
		workflowSectionBean = new WorkflowSectionBean(quoteSummaryBean);
	}
	
	public void reset() {
		webSiteSectionBean.reset();
		enterpriseSectionBean.reset();
		mobileContentSectionBean.reset();
		workflowSectionBean.reset();
	}

	public String getStepName() {
		return "Development Options";
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getRequestMapping() {
		return requestMapping;
	}

	public InfoMessageBean getInfoMessageBean() {
		return infoMessageBean;
	}

	public WebSiteSectionBean getWebSiteSectionBean() {
		return webSiteSectionBean;
	}

	public EnterpriseSectionBean getEnterpriseSectionBean() {
		return enterpriseSectionBean;
	}

	public MobileContentSectionBean getMobileContentSectionBean() {
		return mobileContentSectionBean;
	}

	public WorkflowSectionBean getWorkflowSectionBean() {
		return workflowSectionBean;
	}
}