package com.enterprise.web.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.enterprise.businessservices.FileUploadBusinessService;
import com.enterprise.common.pojo.AsyncSessionObject;
import com.enterprise.common.pojo.FileUploadForm;
import com.enterprise.common.pojo.FileUploadSessionBean;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  10 Mar 2014 4:34:41 PM
 * @author rafourie
 */
@Controller
@SessionAttributes({"fileUploadSessionBean", "asyncSessionObject"})
public class CityFileUploadController implements IFileUploadController {
	
	@Autowired
	private FileUploadBusinessService fileUploadBusinessService;
	
	@RequestMapping(value = "/upload/city/save", method = RequestMethod.POST)
    public String uploadModuleSaveRequest(
    		@ModelAttribute("cityUploadForm") FileUploadForm fileUploadForm, 
    		@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean,
    		Model model) {
		fileUploadSessionBean.reset();
		MultipartFile multipartFile = fileUploadForm.getFile();
        String fileName = "";
        if (multipartFile != null) {
            fileName = multipartFile.getOriginalFilename();
            fileUploadSessionBean.setFileName(fileName);
            try {
            	byte[] fileContentAsByteArr = multipartFile.getBytes();
            	if (fileContentAsByteArr == null || fileContentAsByteArr.length == 0)
            		model.addAttribute("error", "Please select valid file.");
            	else {
            		String fileContentAsStr = new String(fileContentAsByteArr);
            		fileUploadSessionBean.setContent(fileContentAsStr);
            		fileUploadSessionBean.setRequestMapping("/upload/city/process");
            		fileUploadSessionBean.setJavascript("ajax('ajaxModalPageDiv','" + fileUploadSessionBean.getRequestMapping() + "');");
                    return "upload/redirect";
            	}
            } catch (IOException e) {
            	e.printStackTrace();
            	model.addAttribute("error", "There was an exception reading the file contents.");
            }
        } else
        	model.addAttribute("error", "Please select valid file.");
        return this.uploadModuleFormRequest(model);
    }
	
	@RequestMapping(value = "/upload/city/process", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadModuleProcessRequest(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean bean, Model model) {
		String fileName = bean.getFileName();
		model.addAttribute("files", fileName);
		String fileContent = bean.getContent();
		String textStr[] = fileContent.split("\\r?\\n");
		bean.setTotalLines(textStr.length - 1);
		bean.setBatchSize(512);
		try {
			bean = fileUploadBusinessService.processCityFileContent(textStr, bean);
			if (!bean.isFileProcessed())
				bean.setJavascript("parent.ajax('ajaxAsyncDiv','/upload/city/processing');");
			else
				bean.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload/processing";
	}
	
	@RequestMapping(value="/upload/city/form", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadModuleFormRequest(Model model) {
		return "upload/city/form";
	}
	
	@RequestMapping(value = "/upload/city/processing", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadCityProcessingRequest(
    		@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean bean, 
    		@ModelAttribute("asyncSessionObject") AsyncSessionObject asyncSessionObj,
    		Model model) {
		String fileContent = bean.getContent();
		String textStr[] = fileContent.split("\\r?\\n");
		try {
			bean = fileUploadBusinessService.processCityFileContent(textStr, bean);
			if (!bean.isFileProcessed())
				asyncSessionObj.setJavascript("parent.ajax('ajaxAsyncDiv','/upload/city/processing');");
			else
				asyncSessionObj.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
		} catch (Exception e) {
			asyncSessionObj.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
			bean.addErrorMsg("Processing failed at line " + (bean.getCurrentLine() + 1) + " of the file.");
		}
		return "async";
	}
}