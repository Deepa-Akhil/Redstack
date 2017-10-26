package com.enterprise.common.pojo;

import java.util.ArrayList;
import java.util.List;

import com.enterprise.common.interfaces.IMultiPageBean;
import com.enterprise.common.interfaces.IRecord;
import com.enterprise.common.interfaces.impl.AbstractRecordBean;
import com.enterprise.common.util.CollectionUtils;

public class MultiPageDataContainerBean implements IMultiPageBean {
	private static final long serialVersionUID = 8412322167518722076L;
	private final int pageLength;
	private final int maxResults;
	private List<IRecord> allRecords = new ArrayList<IRecord>();
	private int allRecordSize = 0;
	private int pageIndex = 1;
	private int totalPages;
	private final List<IRecord> pageRecords = new ArrayList<IRecord>();
	private final List<NavigationPageBean> pages = new ArrayList<NavigationPageBean>();
	private final Class<? extends AbstractRecordBean> recordClass;
	private String javascript;

	public MultiPageDataContainerBean(final int pageLength, final int maxResults, Class<? extends AbstractRecordBean> recordClass) {
		this.pageLength = pageLength;
		this.maxResults = maxResults;
		this.recordClass = recordClass;
	}

	public int getPageLength() {
		return pageLength;
	}

	public int getMaxResults() {
		return maxResults;
	}

	public List<IRecord> getAllRecords() {
		return allRecords;
	}
	
	public void setAllRecords(List<IRecord> allRecords) throws InstantiationException, IllegalAccessException {
		this.allRecordSize = (CollectionUtils.isEmpty(allRecords)) ? 0 : allRecords.size();
		this.allRecords.clear();
		this.allRecords.addAll(allRecords);
		int allRecordSize = pageLength;
		totalPages = 1;
		pages.clear();
		if (!CollectionUtils.isEmpty(allRecords)) {
			allRecordSize = allRecords.size();
			totalPages = allRecordSize / pageLength;
			int remainder = allRecordSize % pageLength;
			totalPages = (remainder > 0) ? ++totalPages : totalPages;
			for (int i=0; i<totalPages; i++) {
				NavigationPageBean pageBean = new NavigationPageBean();
				pageBean.setPage(i+1);
				int counter = i;
				pageBean.setCounter(counter);
				pages.add(pageBean);
			}
		} else {
			NavigationPageBean pageBean = new NavigationPageBean();
			pageBean.setPage(1);
			pageBean.setCounter(0);
			pages.add(pageBean);
		}
		buildPageRecords();
	}
	
	private void buildPageRecords() throws InstantiationException, IllegalAccessException {
		pageRecords.clear();
		int iEnd = (pageIndex * pageLength) -1;
		int allRecordsSize = allRecords.size();
		if (iEnd > (allRecordsSize -1)) {
			for (int i = iEnd; i >= allRecordsSize; i--) {
				allRecords.add(recordClass.newInstance());
			}
			iEnd = (allRecords.size() -1);
		}
		int iStart = (pageIndex * pageLength) - pageLength;
		for (int i=iStart; i<=iEnd; i++) {
			pageRecords.add(allRecords.get(i));
		}
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) throws InstantiationException, IllegalAccessException {
		this.pageIndex = pageIndex;
		this.buildPageRecords();
	}

	public void reset() {
		if (allRecords != null) {
			allRecords.clear();
		}
		allRecordSize = 0;
		javascript = null;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public List<IRecord> getPageRecords() {
		return pageRecords;
	}

	public List<NavigationPageBean> getPages() {
		return pages;
	}
	
	public int getFirst() {
		return 1;
	}
	
	public int getPrev() {
		int prev = (pageIndex == 1) ? 1 : pageIndex - 1 ;
		return prev;
	}
	
	public int getNext() {
		int next = (pageIndex == totalPages) ? totalPages : pageIndex + 1;
		return next;
	}
	
	public int getLast() {
		return totalPages;
	}

	public Class<? extends AbstractRecordBean> getRecordClass() {
		return recordClass;
	}

	public boolean isEmptyList() {
		boolean empty = (this.allRecordSize > 0) ? false : true;
		return empty;
	}

	public String getJavascript() {
		return javascript;
	}

	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}

	public int getAllRecordSize() {
		return allRecordSize;
	}
}