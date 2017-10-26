package com.enterprise.common.pojo;

import com.enterprise.common.enums.InfoMessageTypes;
import com.enterprise.common.interfaces.IBean;

public class InfoMessageBean implements IBean {
	private static final long serialVersionUID = 7828354626463565565L;
	private static final String DEFAULT_MSG = "Fields marked with (<span class='font-red'>*</span>) are mandatory fields.";
	private InfoMessageTypes type = InfoMessageTypes.Info;
	private String message = new String();
	private String reference = new String();
	
	public InfoMessageBean() {
		this.type = InfoMessageTypes.Info;
		this.message = new String(DEFAULT_MSG);
	}
	
	public InfoMessageBean(InfoMessageTypes type, String message, String reference) {
		this.type = type;
		this.message = message;
		this.reference = reference;
	}
	
	public InfoMessageBean(InfoMessageTypes type, String message) {
		this(type, message, null);
	}

	public void reset() {
		type = InfoMessageTypes.Info;
		message = new String(DEFAULT_MSG);
	}

	public InfoMessageTypes getType() {
		return type;
	}

	public void setType(InfoMessageTypes type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setMessage(String message, InfoMessageTypes type) {
		this.message = message;
		this.type = type;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}
}