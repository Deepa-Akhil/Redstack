package com.enterprise.common.pojo;

import com.enterprise.common.interfaces.IBean;

public class CheckboxInputBean implements IBean {
	private static final long serialVersionUID = -4178674945349019893L;
	private boolean value = false;
	private boolean defaultValue;
	private boolean disabled = false;
	private boolean defaultDisabled;
	
	public CheckboxInputBean() {/* no implementation */}
	
	public CheckboxInputBean(boolean defaultValue, boolean defaultDisabled) {
		this.defaultDisabled = this.disabled = defaultDisabled;
		this.defaultValue = this.value = defaultValue;
	}
	
	public void reset() {
		this.disabled = defaultDisabled;
		this.value = defaultValue;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}