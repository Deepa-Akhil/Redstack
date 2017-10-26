package com.enterprise.common.exception;

public class DataUploadException extends RuntimeException {
	
	private static final long serialVersionUID = -9073776467834491841L;

	public DataUploadException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataUploadException(String message) {
		super(message);
	}
}