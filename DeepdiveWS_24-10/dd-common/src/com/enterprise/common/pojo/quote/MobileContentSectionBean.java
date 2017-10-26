package com.enterprise.common.pojo.quote;

import java.io.Serializable;
import java.util.List;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.enums.QuoteSummaryTypes;
import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.pojo.CheckboxInputBean;

public class MobileContentSectionBean implements Serializable, IBean {
	private static final long serialVersionUID = -6095631623949988533L;
	private final QuoteSummaryBean quoteSummaryBean;
	private final CheckboxInputBean mobileContentCheckboxInputBean = new CheckboxInputBean(false, true);

	public MobileContentSectionBean(QuoteSummaryBean quoteSummaryBean) {
		this.quoteSummaryBean = quoteSummaryBean;
	}
	
	public void initMobileContentSection(boolean checked) {
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.MOBILE_CONTENT);
		} else {
			List<QuoteSummaryTypes> valuesByParent = QuoteSummaryTypes.valuesByParent(QuoteSummaryTypes.MOBILE_CONTENT);
			for (QuoteSummaryTypes quoteSummaryTypes : valuesByParent) {
				quoteSummaryBean.remove(quoteSummaryTypes);
			}
			quoteSummaryBean.remove(QuoteSummaryTypes.MOBILE_CONTENT);
		}
	}
	
	public void enableMobileSection(boolean webSiteRequired) {
		this.mobileContentCheckboxInputBean.setDisabled(!webSiteRequired);
		if (!webSiteRequired) {
			this.mobileContentCheckboxInputBean.setValue(false);
			this.initMobileContentSection(false);
		}
	}
	
	public void mobileContentCheckboxInputBeanOnChange() {
		boolean checked = mobileContentCheckboxInputBean.isValue();
		this.initMobileContentSection(checked);
	}
	
	public void reset() {
		mobileContentCheckboxInputBean.reset();
	}

	public CheckboxInputBean getMobileContentCheckboxInputBean() {
		return mobileContentCheckboxInputBean;
	}	
}