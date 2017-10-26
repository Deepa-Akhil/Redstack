package com.enterprise.common.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.enterprise.common.collections.SelectOptionBeanAlphabeticalComparator;
import com.enterprise.common.interfaces.ISelectInput;
import com.enterprise.common.util.Constants;
import com.enterprise.common.util.StringUtils;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryValueElement;
import com.enterprise.common.xsd.webelementcomplextypes.impl.MandatoryValueElementImpl;

public class SelectInputBean implements ISelectInput {
	private static final long serialVersionUID = -2501144930230164313L;
	private List<SelectOptionBean> options = new ArrayList<SelectOptionBean>();
	private final MandatoryValueElement valueElement = new MandatoryValueElementImpl();
	private final boolean defaultOption;
	private boolean disabled = false;
	private boolean defaultDisabled;
	
	public SelectInputBean(boolean defaultOption, boolean defaultDisabled) {
		this(defaultOption);
		this.defaultDisabled = this.disabled = defaultDisabled;
	}
	
	public SelectInputBean(boolean defaultOption) {
		this.defaultOption = defaultOption;
		this.defaultDisabled = this.disabled = false;
		if (defaultOption) {
			SelectOptionBean select = new SelectOptionBean(null, Constants.DEFAULT_OPTION);
			options.add(select);
		}
	}
	
	public SelectInputBean(boolean defaultOption, List<SelectOptionBean> options) {
		this.defaultOption = defaultOption;
		this.defaultDisabled = this.disabled = false;
		if (defaultOption) {
			SelectOptionBean select = new SelectOptionBean(null, Constants.DEFAULT_OPTION);
			this.options.add(select);
		}
		this.options.addAll(options);
	}
	
	public SelectInputBean(final boolean defaultOption, String value, String description, boolean selected, boolean defaultDisabled) {
		this.defaultOption = defaultOption;
		this.defaultDisabled = this.disabled = defaultDisabled;
		final SelectOptionBean selectedOption = new SelectOptionBean(value, description);
		List<SelectOptionBean> options = new ArrayList<SelectOptionBean>() {
			private static final long serialVersionUID = 1L;
			{
				if (defaultOption) {
					add(new SelectOptionBean(null,Constants.DEFAULT_OPTION));
				}
				add(selectedOption);
			}
		};
		this.setOptions(options);
		if (selected) {
			this.valueElement.setValue(value);
		}
	}

	public List<SelectOptionBean> getOptions() {
		if (this.defaultOption) {
			this.options.remove(0);
			Collections.sort(this.options, new SelectOptionBeanAlphabeticalComparator());
			this.options.add(0, new SelectOptionBean(null, Constants.DEFAULT_OPTION));
		} else
			Collections.sort(this.options, new SelectOptionBeanAlphabeticalComparator());
		return this.options;
	}

	public void reset() {
		valueElement.setValue(null);
		//valueElement.setAttributes(null);
		valueElement.setTitle(null);
		valueElement.setError(null);
		//valueElement.setStyleClassAlt(null);
		this.disabled = defaultDisabled;
	}

	public MandatoryValueElement getValueElement() {
		return valueElement;
	}

	public void setOptions(Collection<SelectOptionBean> options) {
		this.options.clear();
		if (this.defaultOption) {
			SelectOptionBean select = new SelectOptionBean(null, Constants.DEFAULT_OPTION);
			this.options.add(select);
		}
		this.options.addAll(options);
	}
	
	public void clearOptions() {
		this.options.clear();
		if (this.defaultOption) {
			SelectOptionBean select = new SelectOptionBean(null, Constants.DEFAULT_OPTION);
			this.options.add(select);
		}
	}
	
	public void setSelectedOption(String value, String description, boolean disabled) {
		final SelectOptionBean selectedOption = new SelectOptionBean(value, description);
		List<SelectOptionBean> options = new ArrayList<SelectOptionBean>() {
			private static final long serialVersionUID = 7988498237638974936L;
			{
				add(selectedOption);
			}
		};
		this.setOptions(options);
		this.valueElement.setValue(value);
		this.disabled = disabled;
	}

	public boolean isDefaultOption() {
		return defaultOption;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
	public String getSelectedOptionDescription() {
		for (SelectOptionBean optionBean : options) {
			String selectedValue = this.valueElement.getValue();
			String optionValue = optionBean.getValue();
			if (!StringUtils.isEmpty(selectedValue)
					&& !StringUtils.isEmpty(optionValue)) {
				if (optionValue.equals(selectedValue))
					return optionBean.getDescription();
			}
		}
		return null;
	}
}