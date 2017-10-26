package com.enterprise.common.pojo;

import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryValueElement;
import com.enterprise.common.xsd.webelementcomplextypes.impl.MandatoryValueElementImpl;

public class SurveyModuleInputBean implements IBean {
	private static final long serialVersionUID = -3476813039747483670L;
	private MandatoryValueElement localityElement = new MandatoryValueElementImpl();
	private MandatoryValueElement subLocalityElement = new MandatoryValueElementImpl();
	private MandatoryValueElement administrativeAreaLevel1Element = new MandatoryValueElementImpl();
	private MandatoryValueElement administrativeAreaLevel2Element = new MandatoryValueElementImpl();
	private MandatoryValueElement administrativeAreaLevel3Element = new MandatoryValueElementImpl();
	private MandatoryValueElement countryElement = new MandatoryValueElementImpl();
	private MandatoryValueElement ageElement = new MandatoryValueElementImpl();
	private MandatoryValueElement sexElement = new MandatoryValueElementImpl();
	private String javascript;
	
	public void reset() {
		localityElement = new MandatoryValueElementImpl();
		subLocalityElement = new MandatoryValueElementImpl();
		administrativeAreaLevel1Element = new MandatoryValueElementImpl();
		administrativeAreaLevel2Element = new MandatoryValueElementImpl();
		administrativeAreaLevel3Element = new MandatoryValueElementImpl();
		countryElement = new MandatoryValueElementImpl();
		ageElement = new MandatoryValueElementImpl();
		sexElement = new MandatoryValueElementImpl();
		javascript = null;
	}

	public MandatoryValueElement getLocalityElement() {
		return localityElement;
	}

	public MandatoryValueElement getSubLocalityElement() {
		return subLocalityElement;
	}

	public MandatoryValueElement getAdministrativeAreaLevel1Element() {
		return administrativeAreaLevel1Element;
	}

	public MandatoryValueElement getAdministrativeAreaLevel2Element() {
		return administrativeAreaLevel2Element;
	}

	public MandatoryValueElement getAdministrativeAreaLevel3Element() {
		return administrativeAreaLevel3Element;
	}

	public MandatoryValueElement getCountryElement() {
		return countryElement;
	}

	public MandatoryValueElement getAgeElement() {
		return ageElement;
	}

	public MandatoryValueElement getSexElement() {
		return sexElement;
	}

	public String getJavascript() {
		return javascript;
	}

	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}

}