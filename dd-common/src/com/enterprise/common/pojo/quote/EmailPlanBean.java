package com.enterprise.common.pojo.quote;

import java.io.Serializable;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.enums.InfoMessageTypes;
import com.enterprise.common.pojo.InfoMessageBean;

public class EmailPlanBean implements Serializable, IStepable {
	private static final long serialVersionUID = -8958481670677523601L;
	private final String requestMapping = "/request_a_quote/input/email/body";
	private final InfoMessageBean infoMessageBean = new InfoMessageBean();
	private final EmailSectionBean emailSectionBean;
	private final ExchangeSectionBean exchangeSectionBean;
	private int index = 0;
	
	public EmailPlanBean(int index, QuoteSummaryBean quoteSummaryBean) {
		this.index = index;
		String message = new String(
			"The main sections are all unselected by default.<br/>" + 
			"Check the box next to the section should it fall under your requirements.<br/>" + 
			"Fields marked with (<span class='font-red'>*</span>) indicates additional cost.<br/>" + 
			"Please give us as much information as possible."
		);
		infoMessageBean.setMessage(message, InfoMessageTypes.Info);
		emailSectionBean = new EmailSectionBean(quoteSummaryBean);
		exchangeSectionBean = new ExchangeSectionBean(quoteSummaryBean);
	}
	
	public void reset() {
		emailSectionBean.reset();
		exchangeSectionBean.reset();
	}

	public String getStepName() {
		return "Email Options";
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

	public EmailSectionBean getEmailSectionBean() {
		return emailSectionBean;
	}

	public ExchangeSectionBean getExchangeSectionBean() {
		return exchangeSectionBean;
	}

}