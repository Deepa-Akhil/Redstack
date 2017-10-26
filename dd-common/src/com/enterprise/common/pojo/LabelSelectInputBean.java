package com.enterprise.common.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.enterprise.common.util.StringUtils;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  06 Mar 2014 11:19:51 AM
 * @author rafourie
 */
public class LabelSelectInputBean implements Serializable {
	private static final long serialVersionUID = 6973375801790892933L;
	
	private String label;
	private String data;
	private SelectInputBean selectInputBean;
	
	public LabelSelectInputBean(String label, String data, List<HeadingColumnMappedBean> options) {
		if (!StringUtils.isEmpty(label)) {
			this.label = label;
			this.data = data;
			if (!CollectionUtils.isEmpty(options)) {
				selectInputBean = this.newSelectInputBean(options);
				for (HeadingColumnMappedBean bean : options)
					if (bean.getHeading().equals(label))
						selectInputBean.getValueElement().setValue(bean.getColumn());
			}
			else
				throw new RuntimeException("Options cannot be empty.");
		} else
			throw new RuntimeException("Label cannot be null.");
	}
	
	public SelectInputBean newSelectInputBean(List<HeadingColumnMappedBean> options) {
		selectInputBean = new SelectInputBean(true);
		List<SelectOptionBean> selectOptionList = new ArrayList<SelectOptionBean>();
		for (HeadingColumnMappedBean bean : options)
			selectOptionList.add(new SelectOptionBean(bean.getColumn(), bean.getColumn()));
		selectInputBean.setOptions(selectOptionList);
		return selectInputBean;
	}
	
	public void removeUnselectedOption(String value) {
		if (!StringUtils.isEmpty(value)) {
			if (selectInputBean != null) {
				String selectedOptionDescription = selectInputBean.getSelectedOptionDescription();
				if (!value.equals(selectedOptionDescription)) {
					List<SelectOptionBean> selectOptionList = selectInputBean.getOptions();
					if (!CollectionUtils.isEmpty(selectOptionList)) {
						int removeAtIndex = -1;
						for (int i = 0; i < selectOptionList.size(); i++) {
							SelectOptionBean option = selectOptionList.get(i);
							if (value.equals(option.getDescription())) {
								removeAtIndex = i;
								break;
							}
						}
						if (removeAtIndex > -1)
							selectOptionList.remove(removeAtIndex);
					}
				}
			} else
				throw new RuntimeException("Cannot remove option with description [" + value + "] from un-initialised bean.");
		} else
			throw new RuntimeException("Cannot remove null option from list of options.");
	}
	
	public String getFormattedLabel() {
		return label + " [" + StringUtils.abbreviateString(data, 30) + "]";
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public SelectInputBean getSelectInputBean() {
		return selectInputBean;
	}
	
	public void setSelectInputBean(SelectInputBean selectInputBean) {
		this.selectInputBean = selectInputBean;
	}
}