package com.enterprise.common.collections;

import java.util.Comparator;

import com.enterprise.common.pojo.SelectOptionBean;

public class SelectOptionBeanAlphabeticalComparator implements Comparator<SelectOptionBean> {

	public int compare(SelectOptionBean o1, SelectOptionBean o2) {
		int result = 0;
		if (o1 != null && o2 != null) {
			String o1Description = o1.getDescription();
			String o2Description = o2.getDescription();
			result = o1Description.compareTo(o2Description);
		}
		return result;
	}
}