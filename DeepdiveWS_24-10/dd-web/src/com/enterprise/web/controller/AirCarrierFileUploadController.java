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
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.AsyncSessionObject;
import com.enterprise.common.pojo.FileUploadForm;
import com.enterprise.common.pojo.FileUploadSessionBean;

@Controller
@SessionAttributes({"fileUploadSessionBean", "asyncSessionObject"})
public class AirCarrierFileUploadController implements IFileUploadController {
	
	@Autowired
	private FileUploadBusinessService fileUploadBusinessService;

	@RequestMapping(value = "/upload/aircarrier/save", method = RequestMethod.POST)
	public String uploadModuleSaveRequest(
			@ModelAttribute("airportUploadForm") FileUploadForm fileUploadForm, 
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
            		fileUploadSessionBean.setRequestMapping("/upload/aircarrier/process");
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

	@RequestMapping(value = "/upload/aircarrier/process", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadModuleProcessRequest(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean bean, Model model) {
		String fileName = bean.getFileName();
		model.addAttribute("files", fileName);
		String fileContent = bean.getContent();
		String textStr[] = fileContent.split("\\r?\\n");
		bean.setTotalLines(textStr.length - 1);
		bean.setBatchSize(128);
		try {
			bean = fileUploadBusinessService.processAirCarrierFileContent(textStr, bean);
			if (!bean.isFileProcessed())
				bean.setJavascript("parent.ajax('ajaxAsyncDiv','/upload/aircarrier/processing');");
			else
				bean.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
		} catch (DataUploadWarning e) {
			bean.addWarningMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload/processing";
	}
	
	@RequestMapping(value="/upload/aircarrier/form", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadModuleFormRequest(Model model) {
		return "upload/aircarrier/form";
	}
	
	@RequestMapping(value = "/upload/aircarrier/processing", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadAirportProcessingRequest(
    		@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean bean, 
    		@ModelAttribute("asyncSessionObject") AsyncSessionObject asyncSessionObj,
    		Model model) {
		String fileContent = bean.getContent();
		String textStr[] = fileContent.split("\\r?\\n");
		try {
			bean = fileUploadBusinessService.processAirCarrierFileContent(textStr, bean);
			if (!bean.isFileProcessed())
				asyncSessionObj.setJavascript("parent.ajax('ajaxAsyncDiv','/upload/aircarrier/processing');");
			else
				asyncSessionObj.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
		} catch (DataUploadWarning e) {
			bean.addWarningMsg(e.getMessage());
		} catch (Exception e) {
			asyncSessionObj.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
			bean.addErrorMsg("Processing failed at line " + (bean.getCurrentLine() + 1) + " of the file.");
		}
		return "async";
	}
}