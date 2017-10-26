package com.enterprise.common.pojo.quote;

import java.io.Serializable;
import java.util.List;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.enums.QuoteSummaryTypes;
import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.pojo.CheckboxInputBean;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryNumberElement;
import com.enterprise.common.xsd.webelementcomplextypes.impl.MandatoryNumberElementImpl;

public class ExchangeSectionBean implements Serializable, IBean {
	private static final long serialVersionUID = -394236935802727654L;
	private final QuoteSummaryBean quoteSummaryBean;
	private final CheckboxInputBean exchangeCheckboxInputBean;
	private final MandatoryNumberElement exchangeInboxQtyInputElement;
	
	public ExchangeSectionBean(QuoteSummaryBean quoteSummaryBean) {
		this.quoteSummaryBean = quoteSummaryBean;
		exchangeCheckboxInputBean = new CheckboxInputBean(false, false);
		exchangeInboxQtyInputElement = new MandatoryNumberElementImpl();
		exchangeInboxQtyInputElement.setDisabled(String.valueOf(Boolean.TRUE));
		exchangeInboxQtyInputElement.setValue("1");
	}

	public void reset() {
		exchangeCheckboxInputBean.reset();
		exchangeInboxQtyInputElement.setDisabled(String.valueOf(Boolean.TRUE));
		exchangeInboxQtyInputElement.setValue("1");
	}
	
	public void exchangeCheckboxInputBeanOnChange() {
		boolean checked = exchangeCheckboxInputBean.isValue();
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.EXCHANGE_EMAIL_ADMINISTRATION);
			quoteSummaryBean.put(QuoteSummaryTypes.EXCHANGE_EMAIL_MAILBOXES);
			exchangeInboxQtyInputElement.setDisabled(String.valueOf(Boolean.FALSE));
			this.exchangeInboxQtyInputElementValueOnChange();
		} else {
			List<QuoteSummaryTypes> list = QuoteSummaryTypes.valuesByParent(QuoteSummaryTypes.EXCHANGE_EMAIL_ADMINISTRATION);
			for (QuoteSummaryTypes type : list) {
				quoteSummaryBean.remove(type);
			}
			quoteSummaryBean.remove(QuoteSummaryTypes.EXCHANGE_EMAIL_ADMINISTRATION);
			exchangeInboxQtyInputElement.setDisabled(String.valueOf(Boolean.TRUE));
		}
	}
	
	public void exchangeInboxQtyInputElementValueOnChange() {
		int qty = 0;
		QuoteSummaryItem item = quoteSummaryBean.get(QuoteSummaryTypes.EXCHANGE_EMAIL_MAILBOXES);
		if (item != null) {
			try {
				qty = Integer.parseInt(exchangeInboxQtyInputElement.getValue());
			} catch (NumberFormatException e) {
				/* no implementation */
			} finally {
				item.setQty(qty);
			}
		}
	}

	public CheckboxInputBean getExchangeCheckboxInputBean() {
		return exchangeCheckboxInputBean;
	}

	public MandatoryNumberElement getExchangeInboxQtyInputElement() {
		return exchangeInboxQtyInputElement;
	}
}