package com.enterprise.validation.quote;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enterprise.common.jaxb.InputElement;
import com.enterprise.common.jaxb.WebElementsJAXBHelper;
import com.enterprise.common.pojo.quote.EmailSectionBean;
import com.enterprise.common.pojo.quote.ExchangeSectionBean;
import com.enterprise.common.pojo.quote.EmailPlanBean;
import com.enterprise.common.pojo.quote.PersonalDetailsBean;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryNumberElement;

@Component
public class QuoteRequestInputValidator {
	@Autowired
	private WebElementsJAXBHelper helper;
	
	public boolean validateInput(PersonalDetailsBean data) {
		List<Boolean> evalList = new ArrayList<Boolean>();
		//Full Name
		InputElement fullNameInputElement = data.getFullNameInputElement();
		fullNameInputElement = helper.validate(fullNameInputElement, "Invalid Full Name.", "Invalid Full Name.");
		evalList.add(fullNameInputElement.isValid());
		//Company Name
		InputElement companyNameInputElement = data.getCompanyNameInputElement();
		companyNameInputElement = helper.validate(companyNameInputElement, "Invalid Company Name.", "Invalid Company Name.");
		evalList.add(companyNameInputElement.isValid());
		//Company Web Address
		InputElement companyWebAddressInputElement = data.getCompanyWebAddressInputElement();
		companyWebAddressInputElement = helper.validate(companyWebAddressInputElement, "Invalid Company Web Address.", "Invalid Company Web Address.");
		evalList.add(companyWebAddressInputElement.isValid());
		//Email Address
		InputElement emailAddressInputElement = data.getEmailAddressInputElement();
		emailAddressInputElement = helper.validate(emailAddressInputElement, "Invalid Email Address.", "Invalid Email Address.");
		evalList.add(emailAddressInputElement.isValid());
		//Phone Number
		InputElement phoneNumberInputElement = data.getPhoneNumberInputElement();
		phoneNumberInputElement = helper.validate(phoneNumberInputElement, "Invalid Phone Number.", "Invalid Phone Number.");
		evalList.add(phoneNumberInputElement.isValid());
		return !evalList.contains(Boolean.valueOf(false));
	}
	
	public boolean validateHostingSection(EmailPlanBean emailPlanBean) {
		List<Boolean> evalList = new ArrayList<Boolean>();
		EmailSectionBean emailSectionBean = emailPlanBean.getEmailSectionBean();
		MandatoryNumberElement emailInboxQtyInputElement = emailSectionBean.getEmailInboxQtyInputElement();
		boolean emailInboxQtyInputElementDisabled = Boolean.valueOf(emailInboxQtyInputElement.getDisabled());
		if (!emailInboxQtyInputElementDisabled) {
			emailInboxQtyInputElement = (MandatoryNumberElement)helper.validate(emailInboxQtyInputElement, "Quantity is not valid.", "Quantity is not valid.");
			evalList.add(emailInboxQtyInputElement.isValid());
		}
		MandatoryNumberElement emailMobileQtyInputElement = emailSectionBean.getEmailMobileQtyInputElement();
		boolean emailMobileQtyInputElementDisabled = Boolean.valueOf(emailMobileQtyInputElement.getDisabled());
		if (!emailMobileQtyInputElementDisabled) {
			emailMobileQtyInputElement = (MandatoryNumberElement)helper.validate(emailMobileQtyInputElement, "Quantity is not valid.", "Quantity is not valid.");
			evalList.add(emailMobileQtyInputElement.isValid());
		}
		if (!emailInboxQtyInputElementDisabled 
				&& !emailMobileQtyInputElementDisabled 
				&& emailInboxQtyInputElement.isValid() 
				&& emailMobileQtyInputElement.isValid()) {
			int emailInboxQtyInputElementIntValue = Integer.parseInt(emailInboxQtyInputElement.getValue());
			int emailMobileQtyInputElementIntValue = Integer.parseInt(emailMobileQtyInputElement.getValue());
			if (emailMobileQtyInputElementIntValue > emailInboxQtyInputElementIntValue) {
				String errorStr = "Mobile Sync mailbox quantity cannot exceed required mailbox quantity. Maximum value is " + emailInboxQtyInputElementIntValue;
				emailMobileQtyInputElement = (MandatoryNumberElement)helper.setElementAttributes(emailMobileQtyInputElement, errorStr, errorStr, false);
				evalList.add(false);
			} else
				emailMobileQtyInputElement = (MandatoryNumberElement)helper.setElementAttributes(emailMobileQtyInputElement, null, null, true);
		}
		ExchangeSectionBean exchangeSectionBean = emailPlanBean.getExchangeSectionBean();
		MandatoryNumberElement exchangeInboxQtyInputElement = exchangeSectionBean.getExchangeInboxQtyInputElement();
		boolean exchangeInboxQtyInputElementDisabled = Boolean.valueOf(exchangeInboxQtyInputElement.getDisabled());
		if (!exchangeInboxQtyInputElementDisabled) {
			exchangeInboxQtyInputElement = (MandatoryNumberElement)helper.validate(exchangeInboxQtyInputElement, "Quantity is not valid.", "Quantity is not valid.");
			evalList.add(exchangeInboxQtyInputElement.isValid());
		}
		return !evalList.contains(Boolean.valueOf(false));
	}
}