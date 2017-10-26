package com.enterprise.common.pojo;

import com.enterprise.common.enums.LinkItemTypes;
import com.enterprise.common.interfaces.IBean;

public class LinkItemBean implements IBean {
	private static final long serialVersionUID = -5395099692980081230L;
	private LinkItemTypes linkItemType;
	private String name;
	private String property;
	private String mapping;
	private String menuId;
	private String styleClass;
	
	
	public LinkItemBean(LinkItemTypes menuItemType) {
		this.linkItemType = menuItemType;
		this.name = menuItemType.name();
		this.property = menuItemType.getProperty();
		this.mapping = menuItemType.getMapping();
		this.menuId = menuItemType.getMenuId();
	}
	
	public LinkItemBean(LinkItemTypes menuItemType, String styleClass) {
		this.linkItemType = menuItemType;
		this.name = menuItemType.name();
		this.property = menuItemType.getProperty();
		this.mapping = menuItemType.getMapping();
		this.menuId = menuItemType.getMenuId();
		this.styleClass = styleClass;
	}
	
	public void reset() {
		this.styleClass = null;	
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkItemTypes getLinkItemType() {
		return linkItemType;
	}	
}