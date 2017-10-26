package com.enterprise.common.pojo.quote;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.enterprise.common.enums.InfoMessageTypes;
import com.enterprise.common.enums.ProvinceTypes;
import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.pojo.InfoMessageBean;
import com.enterprise.common.pojo.SelectInputBean;
import com.enterprise.common.pojo.SelectOptionBean;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryEmailInputElement;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryValueElement;
import com.enterprise.common.xsd.webelementcomplextypes.impl.MandatoryEmailInputElementImpl;
import com.enterprise.common.xsd.webelementcomplextypes.impl.MandatoryValueElementImpl;

public class PersonalDetailsBean implements Serializable, IStepable, IBean {
	private static final long serialVersionUID = 703600781229074048L;
	private final String requestMapping = "/request_a_quote/input/personal_details/body";
	private final InfoMessageBean infoMessageBean = new InfoMessageBean();
	private SelectInputBean provinceSelectInputBean = new SelectInputBean(true);
	private MandatoryValueElement fullNameInputElement = new MandatoryValueElementImpl();
	private MandatoryValueElement companyNameInputElement = new MandatoryValueElementImpl();
	private MandatoryValueElement companyWebAddressInputElement = new MandatoryValueElementImpl();
	private MandatoryEmailInputElement emailAddressInputElement = new MandatoryEmailInputElementImpl();
	private MandatoryValueElement phoneNumberInputElement = new MandatoryValueElementImpl();
	private int index = 0;
	
	public PersonalDetailsBean(int index) {
		this.index = index;
		String message = new String(
			"Fields marked with (<span class='font-red'>*</span>) are mandatory fields.<br/>" + 
			"The fields in error will have a red border and title text."
		);
		infoMessageBean.setMessage(message, InfoMessageTypes.Info);
		List<SelectOptionBean> provinceOptionList = new ArrayList<SelectOptionBean>();
		ProvinceTypes[] provinceArr = ProvinceTypes.values();
		for (ProvinceTypes provinceTypes : provinceArr) {
			SelectOptionBean option = new SelectOptionBean(provinceTypes.name(), provinceTypes.getDescription());
			provinceOptionList.add(option);
		}
		provinceSelectInputBean.setOptions(provinceOptionList);
	}
	
	public void reset() {
		fullNameInputElement = new MandatoryValueElementImpl();
		companyNameInputElement = new MandatoryValueElementImpl();
		companyWebAddressInputElement = new MandatoryValueElementImpl();
		emailAddressInputElement = new MandatoryEmailInputElementImpl();
		phoneNumberInputElement = new MandatoryValueElementImpl();
		provinceSelectInputBean.reset();
		//TODO: remove
		fullNameInputElement.setValue("Ramon Fourie");
		companyNameInputElement.setValue("VisualAge Technologies");
		companyWebAddressInputElement.setValue("www.visualage.co.za");
		emailAddressInputElement.setValue("contact@visualage.co.za");
		phoneNumberInputElement.setValue("(012) 997 7372");
	}

	public String getStepName() {
		return "Personal Details";
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getRequestMapping() {
		return requestMapping;
	}

	public MandatoryValueElement getFullNameInputElement() {
		return fullNameInputElement;
	}

	public MandatoryValueElement getCompanyNameInputElement() {
		return companyNameInputElement;
	}

	public MandatoryValueElement getCompanyWebAddressInputElement() {
		return companyWebAddressInputElement;
	}

	public MandatoryEmailInputElement getEmailAddressInputElement() {
		return emailAddressInputElement;
	}

	public MandatoryValueElement getPhoneNumberInputElement() {
		return phoneNumberInputElement;
	}

	public SelectInputBean getProvinceSelectInputBean() {
		return provinceSelectInputBean;
	}

	public InfoMessageBean getInfoMessageBean() {
		return infoMessageBean;
	}

}