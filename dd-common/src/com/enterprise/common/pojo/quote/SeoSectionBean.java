package com.enterprise.common.pojo.quote;

import java.io.Serializable;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.enums.QuoteSummaryTypes;
import com.enterprise.common.enums.SeoQuoteSummaryTypes;
import com.enterprise.common.enums.WebSitePagesTypes;
import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.pojo.CheckboxInputBean;

public class SeoSectionBean implements Serializable, IBean {
	private static final long serialVersionUID = -6095631623949988533L;
	private final QuoteSummaryBean quoteSummaryBean;
	private final CheckboxInputBean seoCheckboxInputBean;
	private final CheckboxInputBean linkBuildingCheckboxInputBean;
	private final CheckboxInputBean ppcCheckboxInputBean;

	public SeoSectionBean(QuoteSummaryBean quoteSummaryBean) {
		this.quoteSummaryBean = quoteSummaryBean;
		seoCheckboxInputBean = new CheckboxInputBean(false, true);
		linkBuildingCheckboxInputBean = new CheckboxInputBean(false, true);
		ppcCheckboxInputBean = new CheckboxInputBean(false, true);
	}
	
	public void reset() {
		seoCheckboxInputBean.reset();
		linkBuildingCheckboxInputBean.reset();
		ppcCheckboxInputBean.reset();
	}
	
	public void enableSeoSection(boolean webSiteRequired) {
		if (!webSiteRequired) {
			this.initSeoSection(false, null);
			this.seoCheckboxInputBean.setValue(false);
			this.linkBuildingCheckboxInputBean.setValue(false);
			this.ppcCheckboxInputBean.setValue(false);
		}
		this.seoCheckboxInputBean.setDisabled(!webSiteRequired);
		this.linkBuildingCheckboxInputBean.setDisabled(!webSiteRequired);
		this.ppcCheckboxInputBean.setDisabled(!webSiteRequired);
	}
	
	public void initSeoSection(boolean checked, WebSitePagesTypes webSitePagesType) {
		if (checked) {
			SeoQuoteSummaryTypes seoQuoteSummaryType = SeoQuoteSummaryTypes.valueOfByWebSitePagesTypes(webSitePagesType);
			quoteSummaryBean.put(seoQuoteSummaryType.getQuoteSummaryType());
		} else {
			QuoteSummaryTypes[] arr = new QuoteSummaryTypes[] {
				QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_LESS_THAN_20_PAGES,
				QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_LESS_THAN_50_PAGES,
				QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_LESS_THAN_100_PAGES,
				QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_MORE_THAN_100_PAGES,
				QuoteSummaryTypes.DIRECTORY_PLACEMENTS_AND_PUBLIC_RELATIONS,
				QuoteSummaryTypes.PAY_PER_CLICK
			};
			for (QuoteSummaryTypes quoteSummaryType : arr) {
				quoteSummaryBean.remove(quoteSummaryType);
			}
		}
	}
	
	/**
	 * When the total web design pages selected by the user changes under the application development section, its on-change method
	 * references this bean to update the summary if necessary.
	 * This method is invoked from the webDesignTotalPagesSelectInputBeanOnChange(...) method of an instance of a WebSiteSectionBean object
	 * living inside an instance of a AppDevelopmentBean object, living inside an instance of a QuoteRequestBean object.
	 * @see webDesignTotalPagesSelectInputBeanOnChange() method of the <li>com.enterprise.common.pojo.quote.WebSiteSectionBean</li>
	 * @see <li>com.enterprise.common.pojo.quote.AppDevelopmentBean</li>
	 * @see <li>com.enterprise.common.pojo.quote.QuoteRequestBean</li>
	 * @param webSitePagesType
	 */
	public void initSeoSection(WebSitePagesTypes webSitePagesType) {
		boolean checked = seoCheckboxInputBean.isValue();
		if (checked) {
			QuoteSummaryTypes[] arr = new QuoteSummaryTypes[] {
				QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_LESS_THAN_20_PAGES,
				QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_LESS_THAN_50_PAGES,
				QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_LESS_THAN_100_PAGES,
				QuoteSummaryTypes.SEARCH_ENGINE_OPTIMISATION_MORE_THAN_100_PAGES
			};
			for (QuoteSummaryTypes quoteSummaryType : arr) {
				quoteSummaryBean.remove(quoteSummaryType);
			}	
			SeoQuoteSummaryTypes seoQuoteSummaryType = SeoQuoteSummaryTypes.valueOfByWebSitePagesTypes(webSitePagesType);
			quoteSummaryBean.put(seoQuoteSummaryType.getQuoteSummaryType());
		}
	}
	
	public void seoCheckboxInputBeanOnChange(WebSiteSectionBean webSiteSectionBean) {
		String totalPagesSelectedItemStr = webSiteSectionBean.getWebDesignTotalPagesSelectInputBean().getValueElement().getValue();
		WebSitePagesTypes webSitePagesType  = WebSitePagesTypes.valueOf(totalPagesSelectedItemStr);
		boolean checked = seoCheckboxInputBean.isValue();
		this.initSeoSection(checked, webSitePagesType);
	}
	
	public void linkBuildingCheckboxInputBeanOnChange() {
		boolean checked = linkBuildingCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.DIRECTORY_PLACEMENTS_AND_PUBLIC_RELATIONS);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.DIRECTORY_PLACEMENTS_AND_PUBLIC_RELATIONS);
		}
	}
	
	public void ppcCheckboxInputBeanOnChange() {
		boolean checked = ppcCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.PAY_PER_CLICK);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.PAY_PER_CLICK);
		}
	}

	public CheckboxInputBean getSeoCheckboxInputBean() {
		return seoCheckboxInputBean;
	}

	public CheckboxInputBean getLinkBuildingCheckboxInputBean() {
		return linkBuildingCheckboxInputBean;
	}

	public CheckboxInputBean getPpcCheckboxInputBean() {
		return ppcCheckboxInputBean;
	}	
}