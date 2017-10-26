package com.enterprise.common.pojo;

import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.xsd.webelementcomplextypes.MandatoryValueElement;
import com.enterprise.common.xsd.webelementcomplextypes.impl.MandatoryValueElementImpl;

public class SearchDataBean implements IBean {
	private static final long serialVersionUID = 7695921930139220578L;
	private MandatoryValueElement searchInputElement = new MandatoryValueElementImpl();
	private MultiPageDataContainerBean searchResultsDataBean = initSearchResultsDataBean();
	
	public SearchDataBean() {
		searchInputElement = new MandatoryValueElementImpl();
		searchResultsDataBean = initSearchResultsDataBean();
	}
	
	private MultiPageDataContainerBean initSearchResultsDataBean() {
		return new MultiPageDataContainerBean(5,45,com.enterprise.common.pojo.SearchResultRecordBean.class);
	}
	
	public MandatoryValueElement getSearchInputElement() {
		return searchInputElement;
	}
	
	public void setSearchInputElement(MandatoryValueElement searchInputElement) {
		this.searchInputElement = searchInputElement;
	}
	
	public MultiPageDataContainerBean getSearchResultsDataBean() {
		return searchResultsDataBean;
	}
	
	public void setSearchResultsDataBean(MultiPageDataContainerBean searchResultsDataBean) {
		this.searchResultsDataBean = searchResultsDataBean;
	}

	public void reset() {
		searchInputElement = new MandatoryValueElementImpl();
		searchResultsDataBean = initSearchResultsDataBean();
	}
}