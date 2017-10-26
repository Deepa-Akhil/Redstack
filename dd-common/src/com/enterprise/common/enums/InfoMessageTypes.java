package com.enterprise.common.enums;

public enum InfoMessageTypes {
	Info,
	Error,
	Success;
	
	InfoMessageTypes() {/* no implementation */}
	
	public String getName() {
		return this.name();
	}
}