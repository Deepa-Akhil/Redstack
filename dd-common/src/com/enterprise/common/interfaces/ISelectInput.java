package com.enterprise.common.interfaces;

import java.util.List;

import com.enterprise.common.pojo.SelectOptionBean;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryValueElement;

public interface ISelectInput extends IBean {
	public abstract MandatoryValueElement getValueElement();
	public abstract List<SelectOptionBean> getOptions();
}