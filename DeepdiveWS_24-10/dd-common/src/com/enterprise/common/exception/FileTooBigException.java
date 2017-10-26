package com.enterprise.common.exception;

import java.io.File;

import com.enterprise.common.util.Constants;

public class FileTooBigException extends RuntimeException {
	private static final long serialVersionUID = 1820461652718287936L;

	public FileTooBigException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FileTooBigException(File file) {
		super("Contents of file [" + file.getName() + "] exceeds the maximum readable size of [" + Constants.MAX_FILE_SIZE + "]");
	}
}