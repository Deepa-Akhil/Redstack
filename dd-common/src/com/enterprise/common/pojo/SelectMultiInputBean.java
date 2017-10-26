package com.enterprise.common.pojo;

import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.util.CollectionUtils;
import com.enterprise.common.util.StringUtils;

public class SelectMultiInputBean implements IBean {
	private static final long serialVersionUID = 8787486620474267166L;
	private SortedSet<SelectOptionBean> availableOptions = new TreeSet<SelectOptionBean>();
	private SortedSet<SelectOptionBean> selectedOptions = new TreeSet<SelectOptionBean>();
	private String styleClassAlt;
	private String styleClass;
	private String title;
	private String availableCollection;
	private String selectedCollection;
		
	public String getAvailableCollection() {
		return availableCollection;
	}
	
	private SelectOptionBean fetchSelectOption(String optionStr, Set<SelectOptionBean> collection) {
		SelectOptionBean option = null;
		if (!CollectionUtils.isEmpty(collection) && !StringUtils.isEmpty(optionStr)) {
			for (SelectOptionBean selectOptionBean : collection) {
				if (optionStr.equals(selectOptionBean.getValue())) {
					option = selectOptionBean; break;
				}
			}
		}
		return option;
	}
	
	public void setAvailableCollection(String availableCollection) {
		this.availableCollection = availableCollection;
		if (!StringUtils.isEmpty(availableCollection)) {
			StringTokenizer strTokenizer = new StringTokenizer(availableCollection, ",");
			while (strTokenizer.hasMoreTokens()) {
				String selectedOptionStr = strTokenizer.nextToken();
				SelectOptionBean selectedOption = this.fetchSelectOption(selectedOptionStr, availableOptions);
				selectedOptions.add(selectedOption);
				availableOptions.remove(selectedOption);
			}
		}
	}
	
	public String getSelectedCollection() {
		return selectedCollection;
	}
	
	public void setSelectedCollection(String selectedCollection) {
		this.selectedCollection = selectedCollection;
		if (!StringUtils.isEmpty(selectedCollection)) {
			StringTokenizer strTokenizer = new StringTokenizer(selectedCollection, ",");
			while (strTokenizer.hasMoreTokens()) {
				String selectedOptionStr = strTokenizer.nextToken();
				SelectOptionBean selectedOption = this.fetchSelectOption(selectedOptionStr, selectedOptions);
				availableOptions.add(selectedOption);
				selectedOptions.remove(selectedOption);
			}
		}
	}

	public Set<SelectOptionBean> getAvailableOptions() {
		return availableOptions;
	}

	public Set<SelectOptionBean> getSelectedOptions() {
		return selectedOptions;
	}

	public void setAvailableOptions(SortedSet<SelectOptionBean> availableOptions) {
		this.availableOptions = availableOptions;
	}

	public void setSelectedOptions(SortedSet<SelectOptionBean> selectedOptions) {
		this.selectedOptions = selectedOptions;
	}

	public void reset() {
		availableCollection = null;
		selectedCollection = null;
		if (availableOptions != null) {
			availableOptions.clear();
		}
		if (selectedOptions != null) {
			selectedOptions.clear();
		}
		styleClassAlt = null;
		title = null;
	}

	public String getStyleClassAlt() {
		return styleClassAlt;
	}

	public void setStyleClassAlt(String styleClassAlt) {
		this.styleClassAlt = styleClassAlt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
}