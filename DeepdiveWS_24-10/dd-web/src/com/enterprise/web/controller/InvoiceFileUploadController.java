package com.enterprise.web.controller;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.enterprise.businessservices.FileUploadBusinessService;
import com.enterprise.common.exception.DataUploadException;
import com.enterprise.common.exception.DataUploadWarning;
import com.enterprise.common.pojo.AsyncSessionObject;
import com.enterprise.common.pojo.FileUploadForm;
import com.enterprise.common.pojo.FileUploadSessionBean;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  10 Mar 2014 4:49:11 PM
 * @author rafourie
 */
@Controller
@SessionAttributes({"fileUploadSessionBean", "asyncSessionObject"})
public class InvoiceFileUploadController implements IFileUploadController {
	@Autowired
	private FileUploadBusinessService fileUploadBusinessService;
	
	@RequestMapping(value = "/upload/invoice/save", method = RequestMethod.POST)
    public String uploadModuleSaveRequest(
    		@ModelAttribute("invoiceUploadForm") FileUploadForm fileUploadForm, 
    		@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean,
    		Model model) {
		fileUploadSessionBean.reset();
		fileUploadSessionBean.addInfoMsg("To upload invoice records successfully, each record must have at least a " + 
				"Shipment Number (5100946238).");
		fileUploadSessionBean.addInfoMsg("Should the system not be able to upload records, warnings will be visible as " + 
				"the result.");
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
            		fileUploadSessionBean.setRequestMapping("/upload/invoice/process");
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
	
	@RequestMapping(value = "/upload/invoice/process", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadModuleProcessRequest(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean bean, Model model) {
		String fileName = bean.getFileName();
		model.addAttribute("files", fileName);
		String fileContent = bean.getContent();
		String textStr[] = fileContent.split("\\r?\\n");
		bean.setTotalLines(textStr.length - 1);
		bean.setBatchSize(64);
		bean.setUuid(UUID.randomUUID());
		bean.setFileProcessed(false);
		try {
			bean = fileUploadBusinessService.processInvoiceFileContent(textStr, bean);
			if (!bean.isFileProcessed())
				bean.setJavascript("parent.ajax('ajaxAsyncDiv','/upload/invoice/processing');");
			else bean.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
		} catch (DataUploadWarning e) {
			bean.addWarningMsg(e.getMessage());
			bean.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/invoice/processing');");
		} catch (DataUploadException e) {
			bean.addErrorMsg(e.getLocalizedMessage());
			bean.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "upload/processing";
	}
	
	@RequestMapping(value="/upload/invoice/form", method = {RequestMethod.POST, RequestMethod.GET})
	public String uploadModuleFormRequest(Model model) {
		return "upload/invoice/form";
	}
	
	@RequestMapping(value = "/upload/invoice/processing", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadInvoiceProcessingRequest(
    		@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean bean, 
    		@ModelAttribute("asyncSessionObject") AsyncSessionObject asyncSessionObj,
    		Model model) {
		String fileContent = bean.getContent();
		String textStr[] = fileContent.split("\\r?\\n");
		try {
			bean = fileUploadBusinessService.processInvoiceFileContent(textStr, bean);
			if (!bean.isFileProcessed())
				asyncSessionObj.setJavascript("parent.ajax('ajaxAsyncDiv','/upload/invoice/processing');");
			else
				asyncSessionObj.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
		} catch (DataUploadWarning e) {
			bean.addWarningMsg(e.getMessage());
			asyncSessionObj.setJavascript("parent.ajax('ajaxAsyncDiv','/upload/invoice/processing');");
		} catch (Exception e) {
			asyncSessionObj.setJavascript("parent.ajax('ajaxModalPageDiv','/upload/result');");
			bean.addErrorMsg("Processing failed at line " + (bean.getCurrentLine() + 1) + " of the file.");
		}
		return "async";
	}
}