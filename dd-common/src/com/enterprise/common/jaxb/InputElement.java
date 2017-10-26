package com.enterprise.common.jaxb;

public interface InputElement {
	public abstract void setId(String id);
	public abstract String getId();
	public abstract void setName(String name);
	public abstract String getName();
	public abstract void setType(String type);
	public abstract String getType();
	public abstract void setStyle(String style);
	public abstract String getStyle();
	public abstract void setStyleClass(String styleClass);
	public abstract String getStyleClass();
	public abstract void setStyleClassAlt(String styleClass);
	public abstract String getStyleClassAlt();
	public abstract void setTitle(String title);
	public abstract String getTitle();
	public abstract void setError(String error);
	public abstract String getError();
	public abstract Boolean isValid();
	public abstract void setValid(Boolean valid);
}