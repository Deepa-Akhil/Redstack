package com.enterprise.common.pojo.quote;

import java.io.Serializable;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.enums.InfoMessageTypes;
import com.enterprise.common.pojo.InfoMessageBean;

public class InternetMarketingBean implements Serializable, IStepable {
	private static final long serialVersionUID = -1557269794861308538L;
	private final String requestMapping = "/request_a_quote/input/internet_marketing/body";
	private final InfoMessageBean infoMessageBean = new InfoMessageBean();
	private final SeoSectionBean seoSectionBean;
	private int index = 0;
	
	public InternetMarketingBean(int index, QuoteSummaryBean quoteSummaryBean) {
		this.index = index;
		String message = new String(
			"The main sections are all unselected by default.<br/>" + 
			"Check the box next to the section should it fall under your requirements.<br/>" + 
			"Fields marked with (<span class='font-red'>*</span>) indicates additional cost.<br/>" + 
			"(<span class='font-red'>**</span>) Pay Per Click (PPC) setup is free, but monthly user activity will be billed seperately.<br/>" +
			"Please give us as much information as possible."
		);
		infoMessageBean.setMessage(message, InfoMessageTypes.Info);
		seoSectionBean = new SeoSectionBean(quoteSummaryBean);
	}
	
	public void reset() {
		seoSectionBean.reset();
	}

	public String getStepName() {
		return "Marketing Options";
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

	public SeoSectionBean getSeoSectionBean() {
		return seoSectionBean;
	}

}