package com.enterprise.common.exception;

public class DataUploadWarning extends RuntimeException {
	
	private static final long serialVersionUID = 4477787814957719217L;

	public DataUploadWarning(String message, Throwable cause) {
		super(message, cause);
	}
}