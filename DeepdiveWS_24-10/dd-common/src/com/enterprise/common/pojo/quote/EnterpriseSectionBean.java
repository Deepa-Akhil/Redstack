package com.enterprise.common.pojo.quote;

import java.io.Serializable;
import java.util.List;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.enums.QuoteSummaryTypes;
import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.pojo.CheckboxInputBean;

public class EnterpriseSectionBean implements Serializable, IBean {
	private static final long serialVersionUID = -6095631623949988533L;
	private final QuoteSummaryBean quoteSummaryBean;
	private final CheckboxInputBean enterpriseCheckboxInputBean = new CheckboxInputBean(false, false);
	private final CheckboxInputBean b2bIntegrationCheckboxInputBean = new CheckboxInputBean(false, true);
	private final CheckboxInputBean dataDrivenServicesCheckboxInputBean = new CheckboxInputBean(false, true);
	private final CheckboxInputBean databaseDesignCheckboxInputBean = new CheckboxInputBean(false, true);

	public EnterpriseSectionBean(QuoteSummaryBean quoteSummaryBean) {
		this.quoteSummaryBean = quoteSummaryBean;
	}
	
	public void initEnterpriseSection(boolean checked) {
		b2bIntegrationCheckboxInputBean.setDisabled(!checked);
		dataDrivenServicesCheckboxInputBean.setDisabled(!checked);
		databaseDesignCheckboxInputBean.setDisabled(!checked);
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.ENTERPRISE_FEATURES);
			this.b2bIntegrationCheckboxInputBeanOnChange();
			this.dataDrivenServicesCheckboxInputBeanOnChange();
			this.databaseDesignCheckboxInputBeanOnChange();
		} else {
			List<QuoteSummaryTypes> valuesByParent = QuoteSummaryTypes.valuesByParent(QuoteSummaryTypes.ENTERPRISE_FEATURES);
			for (QuoteSummaryTypes quoteSummaryTypes : valuesByParent) {
				quoteSummaryBean.remove(quoteSummaryTypes);
			}
			quoteSummaryBean.remove(QuoteSummaryTypes.ENTERPRISE_FEATURES);
		}
	}
	
	public void reset() {
		enterpriseCheckboxInputBean.reset();
		b2bIntegrationCheckboxInputBean.reset();
		dataDrivenServicesCheckboxInputBean.reset();
		databaseDesignCheckboxInputBean.reset();
	}
	
	public void b2bIntegrationCheckboxInputBeanOnChange() {
		boolean checked = b2bIntegrationCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.B2B_INTEGRATION);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.B2B_INTEGRATION);
		}
	}
	
	public void dataDrivenServicesCheckboxInputBeanOnChange() {
		boolean checked = dataDrivenServicesCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.DATA_DRIVEN_SERVICES);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.DATA_DRIVEN_SERVICES);
		}
	}
	
	public void databaseDesignCheckboxInputBeanOnChange() {
		boolean checked = databaseDesignCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.DATABASE_DESIGN);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.DATABASE_DESIGN);
		}
	}
	
	public CheckboxInputBean getEnterpriseCheckboxInputBean() {
		return enterpriseCheckboxInputBean;
	}

	public CheckboxInputBean getB2bIntegrationCheckboxInputBean() {
		return b2bIntegrationCheckboxInputBean;
	}

	public CheckboxInputBean getDataDrivenServicesCheckboxInputBean() {
		return dataDrivenServicesCheckboxInputBean;
	}

	public CheckboxInputBean getDatabaseDesignCheckboxInputBean() {
		return databaseDesignCheckboxInputBean;
	}
}