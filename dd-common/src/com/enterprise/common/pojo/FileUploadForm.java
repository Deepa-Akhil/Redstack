package com.enterprise.common.pojo;

import org.springframework.web.multipart.MultipartFile;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  28 Feb 2014 7:57:09 AM
 * @author rafourie
 */
public class FileUploadForm {
	MultipartFile file;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
}