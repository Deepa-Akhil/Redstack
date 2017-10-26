package com.enterprise.common.pojo.quote;

import java.io.Serializable;
import java.util.List;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.enums.QuoteSummaryTypes;
import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.pojo.CheckboxInputBean;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryNumberElement;
import com.enterprise.common.xsd.webelementcomplextypes.impl.MandatoryNumberElementImpl;

public class EmailSectionBean implements Serializable, IBean {
	private static final long serialVersionUID = -394236935802727654L;
	private final QuoteSummaryBean quoteSummaryBean;
	private final CheckboxInputBean emailCheckboxInputBean;
	private final MandatoryNumberElement emailInboxQtyInputElement;
	private final MandatoryNumberElement emailMobileQtyInputElement;
	
	public EmailSectionBean(QuoteSummaryBean quoteSummaryBean) {
		this.quoteSummaryBean = quoteSummaryBean;
		emailCheckboxInputBean = new CheckboxInputBean(false, false);
		emailInboxQtyInputElement = new MandatoryNumberElementImpl();
		emailInboxQtyInputElement.setDisabled(String.valueOf(Boolean.TRUE));
		emailInboxQtyInputElement.setValue("1");
		emailMobileQtyInputElement = new MandatoryNumberElementImpl();
		emailMobileQtyInputElement.setDisabled(String.valueOf(Boolean.TRUE));
		emailMobileQtyInputElement.setValue("1");
	}

	public void reset() {
		emailCheckboxInputBean.reset();
		emailInboxQtyInputElement.setDisabled(String.valueOf(Boolean.TRUE));
		emailInboxQtyInputElement.setValue("1");
		emailInboxQtyInputElement.setStyle(null);
		emailMobileQtyInputElement.setDisabled(String.valueOf(Boolean.TRUE));
		emailMobileQtyInputElement.setValue("1");
		emailMobileQtyInputElement.setStyle(null);
	}
	
	public void emailCheckboxInputBeanOnChange() {
		boolean checked = emailCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.STANDARD_EMAIL_ADMINISTRATION);
			quoteSummaryBean.put(QuoteSummaryTypes.STANDARD_EMAIL_MAILBOXES);
			quoteSummaryBean.put(QuoteSummaryTypes.STANDARD_EMAIL_MOBILE_SYNC);
			emailInboxQtyInputElement.setDisabled(String.valueOf(Boolean.FALSE));
			this.emailInboxQtyInputElementValueOnChange();
			emailMobileQtyInputElement.setDisabled(String.valueOf(Boolean.FALSE));
			this.emailMobileQtyInputElementValueOnChange();
		} else {
			List<QuoteSummaryTypes> list = QuoteSummaryTypes.valuesByParent(QuoteSummaryTypes.STANDARD_EMAIL_ADMINISTRATION);
			for (QuoteSummaryTypes type : list) {
				quoteSummaryBean.remove(type);
			}
			quoteSummaryBean.remove(QuoteSummaryTypes.STANDARD_EMAIL_ADMINISTRATION);
			emailInboxQtyInputElement.setDisabled(String.valueOf(Boolean.TRUE));
			emailMobileQtyInputElement.setDisabled(String.valueOf(Boolean.TRUE));
		}
	}
	
	public void emailInboxQtyInputElementValueOnChange() {
		int qty = 0;
		QuoteSummaryItem item = quoteSummaryBean.get(QuoteSummaryTypes.STANDARD_EMAIL_MAILBOXES);
		if (item != null) {
			try {
				qty = Integer.parseInt(emailInboxQtyInputElement.getValue());
			} catch (NumberFormatException e) {
				/* no implementation */
			} finally {
				item.setQty(qty);
			}
		}
	}
	
	public void emailMobileQtyInputElementValueOnChange() {
		int qty = 0;
		QuoteSummaryItem item = quoteSummaryBean.get(QuoteSummaryTypes.STANDARD_EMAIL_MOBILE_SYNC);
		if (item != null) {
			try {
				qty = Integer.parseInt(emailMobileQtyInputElement.getValue());
			} catch (NumberFormatException e) {
				/* no implementation */
			} finally {
				item.setQty(qty);
			}
		}
	}

	public CheckboxInputBean getEmailCheckboxInputBean() {
		return emailCheckboxInputBean;
	}

	public MandatoryNumberElement getEmailInboxQtyInputElement() {
		return emailInboxQtyInputElement;
	}

	public MandatoryNumberElement getEmailMobileQtyInputElement() {
		return emailMobileQtyInputElement;
	}
}