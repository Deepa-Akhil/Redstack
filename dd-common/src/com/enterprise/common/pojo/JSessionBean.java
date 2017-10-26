package com.enterprise.common.pojo;

import java.io.Serializable;

import com.enterprise.common.annotation.SessionObject;

@SessionObject(id = "jSessionBean")
public class JSessionBean implements Serializable {
	private static final long serialVersionUID = 1172829246521533650L;
	private String sessionId;
	private String javascript;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getJavascript() {
		return javascript;
	}

	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}
}