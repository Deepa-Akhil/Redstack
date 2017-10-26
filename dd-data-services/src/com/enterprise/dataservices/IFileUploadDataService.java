package com.enterprise.dataservices;

import java.lang.reflect.InvocationTargetException;

import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.FileUploadSessionBean;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  04 Mar 2014 7:23:54 AM
 * @author rafourie
 */
public interface IFileUploadDataService {
	public abstract FileUploadSessionBean processFileContent(String[] textStr, FileUploadSessionBean bean) 
		throws ClassNotFoundException, SecurityException, NoSuchMethodException, 
			IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			DataUploadWarning, DataUploadException;
}