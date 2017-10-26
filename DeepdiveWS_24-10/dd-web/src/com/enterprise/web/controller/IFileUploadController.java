package com.enterprise.web.controller;

import org.springframework.ui.Model;

import com.enterprise.common.pojo.FileUploadForm;
import com.enterprise.common.pojo.FileUploadSessionBean;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  10 Mar 2014 4:44:09 PM
 * @author rafourie
 */
public interface IFileUploadController {
	
	/**
	 * @RequestMapping(value = "/upload/city/save", method = RequestMethod.POST)
	 * @param fileUploadForm
	 * @param fileUploadSessionBean
	 * @return requestMapping
	 */
	public abstract String uploadModuleSaveRequest(
			FileUploadForm fileUploadForm, FileUploadSessionBean fileUploadSessionBean, Model model);
	
	/**
	 * @RequestMapping(value = "/upload/city/process", method = {RequestMethod.POST, RequestMethod.GET})
	 * @param bean
	 * @param model
	 * @return requestMapping
	 */
	public abstract String uploadModuleProcessRequest(FileUploadSessionBean bean, Model model);
	
	/**
	 * @RequestMapping(value="/upload/city/form", method = {RequestMethod.POST, RequestMethod.GET})
	 * @param model
	 * @return requestMapping
	 */
	public abstract String uploadModuleFormRequest(Model model);
}