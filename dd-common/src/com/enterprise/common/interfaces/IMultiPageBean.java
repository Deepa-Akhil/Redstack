package com.enterprise.common.interfaces;

import java.util.List;

import com.enterprise.common.pojo.NavigationPageBean;

public interface IMultiPageBean extends IBean {
	public abstract int getPageLength();
	public abstract int getMaxResults();
	public abstract int getPageIndex();
	public abstract void setPageIndex(int pageIndex) throws InstantiationException, IllegalAccessException;
	public abstract int getTotalPages();
	public abstract List<IRecord> getPageRecords();
	public abstract List<NavigationPageBean> getPages();
	public abstract int getFirst();
	public abstract int getPrev();
	public abstract int getNext();
	public abstract int getLast();
	public abstract boolean isEmptyList();
}