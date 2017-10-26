package com.enterprise.web.util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFile {

	public void uploadImage(String fileName, MultipartFile multipartFile){
		try {
			File file = new File(fileName);
			FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
		} catch (Throwable e) {
			e.printStackTrace();
		} 
	}
}
