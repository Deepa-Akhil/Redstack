package com.enterprise.common.pojo;

import java.io.Serializable;

import com.enterprise.common.util.StringUtils;

public class SelectOptionBean implements Serializable, Comparable<SelectOptionBean> {
	private static final long serialVersionUID = -4481609965994446043L;
	private String value;
	private String description;
	
	public SelectOptionBean() {/* no implementation */}
	
	public SelectOptionBean(String value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int compareTo(SelectOptionBean o) {
		if (o == null) {
			return -1;
		} else {
			String tDesc = StringUtils.isEmpty(this.description) ? "" : this.description;
			String oDesc = StringUtils.isEmpty(o.getDescription()) ? "" : o.getDescription();
			return tDesc.compareTo(oDesc);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof SelectOptionBean)) return false;
		SelectOptionBean oBean = (SelectOptionBean)o;
		if (oBean.getValue().equals(this.getValue())
				&& oBean.getDescription().equals(this.getDescription())) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() { 
	    int hash = 1;
	    hash = hash * 31 + (StringUtils.isEmpty(value) ? 0 : value.hashCode());
	    hash = hash * 31 + (StringUtils.isEmpty(description) ? 0 : description.hashCode());
	    return hash;
	}
}