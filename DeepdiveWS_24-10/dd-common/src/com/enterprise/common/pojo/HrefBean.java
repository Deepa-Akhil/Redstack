package com.enterprise.common.pojo;

public class HrefBean {
	private final String label;
	private final String url;
	
	public HrefBean(final String label, final String url) {
		this.label = label;
		this.url = url;
	}

	public String getLabel() {
		return label;
	}

	public String getUrl() {
		return url;
	}
}