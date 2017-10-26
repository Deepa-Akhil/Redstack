package com.enterprise.validation.contact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enterprise.common.jaxb.InputElement;
import com.enterprise.common.jaxb.WebElementsJAXBHelper;
import com.enterprise.common.pojo.ContactDataBean;

@Component
public class ContactInputValidator {
	private static final String P_VALUE_IS_MANDATORY = "contact.value_is_mandatory_1";
	private static final String P_EMAIL_REQUIRED_FORMATTED = "contact.email_required_and_correctly_formatted";
	private static final String P_CELLPHONE_NUMBER_INVALID = "contact.cellphone_number_invalid";
	
	@Autowired
	private WebElementsJAXBHelper helper;
	
	public boolean validateInput(ContactDataBean data) {
		List<Boolean> evalList = new ArrayList<Boolean>();
		validateInput(data, evalList);
		return !evalList.contains(Boolean.valueOf(false));
	}
	
	private void validateInput(ContactDataBean data, List<Boolean> evalList) {
		InputElement fullNamesInputElement = data.getFullNamesInputElement();
		fullNamesInputElement = helper.validate(fullNamesInputElement, P_VALUE_IS_MANDATORY, P_VALUE_IS_MANDATORY);
		evalList.add(fullNamesInputElement.isValid());
		InputElement cellphoneNumberInputElement = data.getCellphoneNumberInputElement();
		cellphoneNumberInputElement = helper.validate(cellphoneNumberInputElement, P_CELLPHONE_NUMBER_INVALID, P_CELLPHONE_NUMBER_INVALID);
		evalList.add(cellphoneNumberInputElement.isValid());
		InputElement emailInputElement = data.getEmailInputElement();
		emailInputElement = helper.validate(emailInputElement, P_EMAIL_REQUIRED_FORMATTED, P_EMAIL_REQUIRED_FORMATTED);
		evalList.add(emailInputElement.isValid());
		InputElement textInputElement = data.getTextInputElement();
		textInputElement = helper.validate(textInputElement, P_VALUE_IS_MANDATORY, P_VALUE_IS_MANDATORY);
		evalList.add(textInputElement.isValid());
	}
}