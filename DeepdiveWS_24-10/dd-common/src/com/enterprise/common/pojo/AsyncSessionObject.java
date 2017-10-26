package com.enterprise.common.pojo;

import com.enterprise.common.annotation.SessionObject;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  06 Mar 2014 3:22:01 PM
 * @author rafourie
 */
@SessionObject(id = "asyncSessionObject")
public class AsyncSessionObject {
	private String javascript;
	private String formName;

	public String getJavascript() {
		return javascript;
	}

	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}
}