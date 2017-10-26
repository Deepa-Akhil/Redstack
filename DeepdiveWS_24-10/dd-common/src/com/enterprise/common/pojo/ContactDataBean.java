package com.enterprise.common.pojo;

import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.jaxb.InputElement;
import com.enterprise.common.xsd.webelementcomplextypes.CellphoneNumberInputElement;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryEmailInputElement;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryValueElement;
import com.enterprise.common.xsd.webelementcomplextypes.impl.CellphoneNumberInputElementImpl;
import com.enterprise.common.xsd.webelementcomplextypes.impl.MandatoryEmailInputElementImpl;
import com.enterprise.common.xsd.webelementcomplextypes.impl.MandatoryValueElementImpl;

public class ContactDataBean implements IBean {
	private static final long serialVersionUID = 3310213733235650798L;
	private InfoMessageBean infoMessageBean = new InfoMessageBean();
	private final MandatoryValueElement fullNamesInputElement = new MandatoryValueElementImpl();
	private final CellphoneNumberInputElement cellphoneNumberInputElement = new CellphoneNumberInputElementImpl();
	private final MandatoryEmailInputElement emailInputElement = new MandatoryEmailInputElementImpl();
	private final MandatoryValueElement textInputElement = new MandatoryValueElementImpl();
	private boolean inputValid = true;
	private boolean feedbackSentForSession = false;
	
	public void init() {
		initElement(fullNamesInputElement, null, null, null, null);
		initElement(cellphoneNumberInputElement, null, null, null, null);
		initElement(emailInputElement, null, null, null, null);
		initElement(textInputElement, null, null, null, null);
	}
	
	private void initElement(InputElement element, String style, String styleClassAlt, String title, String error) {
		element.setStyle(style);
		element.setStyleClassAlt(styleClassAlt);
		element.setTitle(title);
		element.setError(error);
	}
	
	public void reset() {
		this.init();
		infoMessageBean.reset();
		fullNamesInputElement.setValue(null);
		cellphoneNumberInputElement.setNumber(null);
		emailInputElement.setValue(null);
		textInputElement.setValue(null);
		inputValid = true;
	}

	public MandatoryValueElement getFullNamesInputElement() {
		return fullNamesInputElement;
	}

	public MandatoryEmailInputElement getEmailInputElement() {
		return emailInputElement;
	}

	public MandatoryValueElement getTextInputElement() {
		return textInputElement;
	}

	public boolean isInputValid() {
		return inputValid;
	}

	public void setInputValid(boolean inputValid) {
		this.inputValid = inputValid;
	}

	public InfoMessageBean getInfoMessageBean() {
		return infoMessageBean;
	}

	public void setInfoMessageBean(InfoMessageBean infoMessageBean) {
		this.infoMessageBean = infoMessageBean;
	}

	public boolean isFeedbackSentForSession() {
		return feedbackSentForSession;
	}

	public void setFeedbackSentForSession(boolean feedbackSentForSession) {
		this.feedbackSentForSession = feedbackSentForSession;
	}
	
	@Override
	public String toString() {
		return "fullNamesInputElement[" + fullNamesInputElement.getValue() + "], " + 
			"cellphoneNumberInputElement[" + cellphoneNumberInputElement.getNumber() + "]" + 
			"emailInputElement[" + emailInputElement.getValue() + "], " + 
			"textInputElement[" + textInputElement.getValue() + "], " + 
			"inputValid[" + inputValid + "], " + 
			"feedbackSentForSession[" + feedbackSentForSession + "]";
	}

	public CellphoneNumberInputElement getCellphoneNumberInputElement() {
		return cellphoneNumberInputElement;
	}
}