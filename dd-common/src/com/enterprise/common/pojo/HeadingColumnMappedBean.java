package com.enterprise.common.pojo;

public class HeadingColumnMappedBean {
	
	private String heading;
	
	private String column;
	
	public HeadingColumnMappedBean(String heading, String column) {
		this.heading = heading;
		this.column = column;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
}