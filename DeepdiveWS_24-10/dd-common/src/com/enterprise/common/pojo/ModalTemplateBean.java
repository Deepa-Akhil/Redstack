package com.enterprise.common.pojo;

import java.io.Serializable;

import com.enterprise.common.annotation.SessionObject;

@SessionObject(id = "modalTemplateBean")
public class ModalTemplateBean implements Serializable {
	private static final long serialVersionUID = -2369965534588657184L;
	private String dataIncludePage;
	private String heading;
	private String iframeSrc;
	private HeaderInfoBean headerInfoBean = new HeaderInfoBean();
	
	public String getDataIncludePage() {
		return dataIncludePage;
	}
	
	public void setDataIncludePage(String dataIncludePage) {
		this.dataIncludePage = dataIncludePage;
	}
	
	public HeaderInfoBean getHeaderInfoBean() {
		return headerInfoBean;
	}
	
	public void setHeaderInfoBean(HeaderInfoBean headerInfoBean) {
		this.headerInfoBean = headerInfoBean;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getIframeSrc() {
		return iframeSrc;
	}

	public void setIframeSrc(String iframeSrc) {
		this.iframeSrc = iframeSrc;
	}
}