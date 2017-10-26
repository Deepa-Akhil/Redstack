package com.enterprise.common.pojo.quote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.enums.QuoteSummaryTypes;
import com.enterprise.common.enums.WebSitePagesTypes;
import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.pojo.CheckboxInputBean;
import com.enterprise.common.pojo.SelectInputBean;
import com.enterprise.common.pojo.SelectOptionBean;

public class WebSiteSectionBean implements Serializable, IBean {
	private static final long serialVersionUID = 61161132564624498L;
	private final QuoteSummaryBean quoteSummaryBean;
	private final CheckboxInputBean webSiteCheckboxInputBean = new CheckboxInputBean(false, false);
	private final SelectInputBean webDesignTotalPagesSelectInputBean = new SelectInputBean(false,true);
	private final CheckboxInputBean advancedMenuStructuresCheckboxInputBean = new CheckboxInputBean(false, true);
	private final CheckboxInputBean loginCheckboxInputBean = new CheckboxInputBean(false, true);
	private final CheckboxInputBean multipleLanguageCheckboxInputBean = new CheckboxInputBean(false, true);
	private final CheckboxInputBean imageSliderCheckboxInputBean = new CheckboxInputBean(false, true);
	private final CheckboxInputBean socialMediaIntegrationCheckboxInputBean = new CheckboxInputBean(false, true);
	private final CheckboxInputBean googleMapsIntegrationCheckboxInputBean = new CheckboxInputBean(false, true);
	private final CheckboxInputBean emailIntegrationCheckboxInputBean = new CheckboxInputBean(false, true);

	public WebSiteSectionBean(QuoteSummaryBean quoteSummaryBean) {
		this.quoteSummaryBean = quoteSummaryBean;
		List<SelectOptionBean> webDesignTotalPagesOptionList = new ArrayList<SelectOptionBean>();
		WebSitePagesTypes[] webDesignTotalPagesArr = new WebSitePagesTypes[] {
			WebSitePagesTypes.LESS_THAN_20_PAGES,
			WebSitePagesTypes.LESS_THAN_50_PAGES,
			WebSitePagesTypes.LESS_THAN_100_PAGES,
			WebSitePagesTypes.MORE_THAN_100_PAGES
		};
		for (int i = 0; i < webDesignTotalPagesArr.length; i++) {
			WebSitePagesTypes totalPagesTypes = webDesignTotalPagesArr[i];
			SelectOptionBean option = new SelectOptionBean(totalPagesTypes.name(), totalPagesTypes.getDescription());
			webDesignTotalPagesOptionList.add(option);
		}
		webDesignTotalPagesSelectInputBean.setOptions(webDesignTotalPagesOptionList);
	}
	
	public void initWebDesignSection(boolean checked) {
		webDesignTotalPagesSelectInputBean.setDisabled(!checked);
		advancedMenuStructuresCheckboxInputBean.setDisabled(!checked);
		loginCheckboxInputBean.setDisabled(!checked);
		multipleLanguageCheckboxInputBean.setDisabled(!checked);
		imageSliderCheckboxInputBean.setDisabled(!checked);
		socialMediaIntegrationCheckboxInputBean.setDisabled(!checked);
		googleMapsIntegrationCheckboxInputBean.setDisabled(!checked);
		emailIntegrationCheckboxInputBean.setDisabled(!checked);
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.WEBSITE_DESIGN);
			String totalPagesTypeStr = webDesignTotalPagesSelectInputBean.getValueElement().getValue();
			WebSitePagesTypes totalPagesType = WebSitePagesTypes.valueOf(totalPagesTypeStr);
			QuoteSummaryTypes quoteSummaryType = QuoteSummaryTypes.valueOfByDescription(totalPagesType.getDescription());
			quoteSummaryBean.put(quoteSummaryType);
			this.advancedMenuStructuresCheckboxInputBeanOnChange();
			this.loginCheckboxInputBeanOnChange();
			this.multipleLanguageCheckboxInputBeanOnChange();
			this.imageSliderCheckboxInputBeanOnChange();
			this.socialMediaIntegrationCheckboxInputBeanOnChange();
			this.googleMapsIntegrationCheckboxInputBeanOnChange();
			this.emailIntegrationCheckboxInputBeanOnChange();
		} else {
			List<QuoteSummaryTypes> valuesByParent = QuoteSummaryTypes.valuesByParent(QuoteSummaryTypes.WEBSITE_DESIGN);
			for (QuoteSummaryTypes quoteSummaryTypes : valuesByParent) {
				quoteSummaryBean.remove(quoteSummaryTypes);
			}
			quoteSummaryBean.remove(QuoteSummaryTypes.WEBSITE_DESIGN);
		}
	}
	
	public void reset() {
		webSiteCheckboxInputBean.reset();
		webDesignTotalPagesSelectInputBean.reset();
		advancedMenuStructuresCheckboxInputBean.reset();
		loginCheckboxInputBean.reset();
		multipleLanguageCheckboxInputBean.reset();
		imageSliderCheckboxInputBean.reset();
		socialMediaIntegrationCheckboxInputBean.reset();
		googleMapsIntegrationCheckboxInputBean.reset();
		emailIntegrationCheckboxInputBean.reset();
	}
	
	public SelectInputBean getWebDesignTotalPagesSelectInputBean() {
		return webDesignTotalPagesSelectInputBean;
	}

	public CheckboxInputBean getWebSiteCheckboxInputBean() {
		return webSiteCheckboxInputBean;
	}

	public CheckboxInputBean getAdvancedMenuStructuresCheckboxInputBean() {
		return advancedMenuStructuresCheckboxInputBean;
	}

	public CheckboxInputBean getLoginCheckboxInputBean() {
		return loginCheckboxInputBean;
	}

	public CheckboxInputBean getMultipleLanguageCheckboxInputBean() {
		return multipleLanguageCheckboxInputBean;
	}

	public CheckboxInputBean getImageSliderCheckboxInputBean() {
		return imageSliderCheckboxInputBean;
	}

	public CheckboxInputBean getSocialMediaIntegrationCheckboxInputBean() {
		return socialMediaIntegrationCheckboxInputBean;
	}

	public CheckboxInputBean getGoogleMapsIntegrationCheckboxInputBean() {
		return googleMapsIntegrationCheckboxInputBean;
	}

	public CheckboxInputBean getEmailIntegrationCheckboxInputBean() {
		return emailIntegrationCheckboxInputBean;
	}
	
	public void webDesignTotalPagesSelectInputBeanOnChange(InternetMarketingBean internetMarketingBean) {
		List<QuoteSummaryTypes> webSiteTotalPagesSummaryTypes = QuoteSummaryTypes.valuesByWebSitePagesTypes(WebSitePagesTypes.values());
		for (QuoteSummaryTypes quoteSummaryTypes : webSiteTotalPagesSummaryTypes) {
			quoteSummaryBean.remove(quoteSummaryTypes);
		}
		String totalPagesTypeStr = webDesignTotalPagesSelectInputBean.getValueElement().getValue();
		WebSitePagesTypes totalPagesType = WebSitePagesTypes.valueOf(totalPagesTypeStr);
		QuoteSummaryTypes quoteSummaryType = QuoteSummaryTypes.valueOfByDescription(totalPagesType.getDescription());
		quoteSummaryBean.put(quoteSummaryType);
		internetMarketingBean.getSeoSectionBean().initSeoSection(totalPagesType);
	}
	
	public void advancedMenuStructuresCheckboxInputBeanOnChange() {
		boolean checked = advancedMenuStructuresCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.ADVANCED_MENU_STRUCTURES);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.ADVANCED_MENU_STRUCTURES);
		}
	}
	
	public void loginCheckboxInputBeanOnChange() {
		boolean checked = loginCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.LOGIN_AUTHENTICATION);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.LOGIN_AUTHENTICATION);
		}
	}
	
	public void multipleLanguageCheckboxInputBeanOnChange() {
		boolean checked = multipleLanguageCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.MULTI_LANGUAGE_SUPPORT);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.MULTI_LANGUAGE_SUPPORT);
		}
	}
	
	public void imageSliderCheckboxInputBeanOnChange() {
		boolean checked = imageSliderCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.JQUERY_IMAGE_SLIDER);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.JQUERY_IMAGE_SLIDER);
		}
	}
	
	public void socialMediaIntegrationCheckboxInputBeanOnChange() {
		boolean checked = socialMediaIntegrationCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.SOCIAL_MEDIA_INTEGRATION);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.SOCIAL_MEDIA_INTEGRATION);
		}
	}
	
	public void googleMapsIntegrationCheckboxInputBeanOnChange() {
		boolean checked = googleMapsIntegrationCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.GOOGLE_MAPS_INTEGRATION);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.GOOGLE_MAPS_INTEGRATION);
		}
	}
	
	public void emailIntegrationCheckboxInputBeanOnChange() {
		boolean checked = emailIntegrationCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.EMAIL_INTEGRATION);
		} else {
			quoteSummaryBean.remove(QuoteSummaryTypes.EMAIL_INTEGRATION);
		}
	}
}