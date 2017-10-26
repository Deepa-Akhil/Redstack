package com.enterprise.common.pojo;

import com.enterprise.common.interfaces.impl.AbstractRecordBean;
import com.enterprise.common.util.StringUtils;

public class SearchResultRecordBean extends AbstractRecordBean {
	private static final long serialVersionUID = 4446803938840859451L;
	private long recordId = 0L;
	private String mapping;
	private String title;
	private String description;
	private String menuId;

	@Override
	public long getRecordId() {
		return recordId;
	}

	public void setRecordId(long recordId) {
		this.recordId = recordId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}
	
	public String getDescriptionSubstring() {
		if (!StringUtils.isEmpty(description)) {
			if (description.length() > 200) {
				return description.substring(0, 200) + " ...";
			} else {
				return description;
			}
		}
		return null;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}