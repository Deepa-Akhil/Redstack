package com.enterprise.common.pojo;

import java.util.ArrayList;
import java.util.List;

import com.enterprise.common.interfaces.IBean;

public class BreadCrumbQueue implements IBean {
	private static final long serialVersionUID = 3884252571358692897L;
	private List<LinkItemBean> items = new ArrayList<LinkItemBean>();
	
	public void reset() {
		items.clear();
	}

	public List<LinkItemBean> getItems() {
		return items;
	}
	
	public void addItem(LinkItemBean item) {
		items.add(item);
	}
	
	public void setItem(LinkItemBean item) {
		items = new ArrayList<LinkItemBean>();
		items.add(item);
	}
	
	public void removeAfter(LinkItemBean bean) {
		List<LinkItemBean> filteredItems = new ArrayList<LinkItemBean>();
		for (int i = 0; i < items.size(); i++) {
			LinkItemBean itemsLinkItemBean = items.get(i);
			if (itemsLinkItemBean.equals(bean)) {
				break;
			}
			filteredItems.add(itemsLinkItemBean);
		}
		filteredItems.add(bean);
		items = filteredItems;
	}
}