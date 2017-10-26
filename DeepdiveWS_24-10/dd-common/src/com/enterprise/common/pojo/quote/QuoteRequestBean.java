package com.enterprise.common.pojo.quote;

import java.io.Serializable;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.interfaces.IBean;

public class QuoteRequestBean extends AbstractStepable implements Serializable, IBean {
	private static final long serialVersionUID = 5162395116039095294L;
	private final String moduleRequestMapping = "/request_a_quote/input";
	private final String summaryRequestMapping = "/request_a_quote/input/quote_summary";
	private final QuoteSummaryBean quoteSummaryBean = new QuoteSummaryBean();
	private final IStepable personalDetailsBean = new PersonalDetailsBean(0);
	private final IStepable appDevelopmentBean = new AppDevelopmentBean(1, quoteSummaryBean);
	private final IStepable internetMarketingBean = new InternetMarketingBean(2, quoteSummaryBean);
	private final IStepable emailPlanBean = new EmailPlanBean(3, quoteSummaryBean);
	private final IStepable confirmationBean = new ConfirmationBean(4);

	public QuoteRequestBean() {
		this.initialise();
	}
	
	private void initialise() {
		super.getSteps().clear();
		super.getSteps().add(personalDetailsBean);
		super.getSteps().add(appDevelopmentBean);
		super.getSteps().add(internetMarketingBean);
		super.getSteps().add(emailPlanBean);
		super.setActiveStep(0);
	}
	
	public void reset() {
		this.personalDetailsBean.reset();
		this.appDevelopmentBean.reset();
		this.internetMarketingBean.reset();
		this.emailPlanBean.reset();
		this.confirmationBean.reset();
		this.quoteSummaryBean.reset();
		this.initialise();
	}

	public IStepable getPersonalDetailsBean() {
		return personalDetailsBean;
	}

	public IStepable getAppDevelopmentBean() {
		return appDevelopmentBean;
	}

	public IStepable getInternetMarketingBean() {
		return internetMarketingBean;
	}

	public IStepable getEmailPlanBean() {
		return emailPlanBean;
	}

	public String getModuleRequestMapping() {
		return moduleRequestMapping;
	}

	public IStepable getConfirmationBean() {
		return confirmationBean;
	}
	/*public List<QuoteSummaryTypes> getQuoteSummaryList() {
		Collections.sort(quoteSummaryList, new QuoteSummaryTypesWeightComparator());
		return quoteSummaryList;
	}
	
	public String getQuoteTotal() {
		int quoteTotal = 0;
		for (QuoteSummaryTypes quote : quoteSummaryList) {
			if (quote != null && quote.isBillable()) {
				quoteTotal += Integer.parseInt(quote.getPrice());
			}
		}
		return String.valueOf(quoteTotal);
	}*/

	public QuoteSummaryBean getQuoteSummaryBean() {
		return quoteSummaryBean;
	}

	public String getSummaryRequestMapping() {
		return summaryRequestMapping;
	}
}