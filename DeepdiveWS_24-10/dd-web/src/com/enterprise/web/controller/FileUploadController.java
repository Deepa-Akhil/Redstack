package com.enterprise.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.enterprise.common.pojo.FileUploadSessionBean;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  26 Feb 2014 12:08:26 PM
 * @author rafourie
 */
@Controller
@SessionAttributes({"fileUploadSessionBean"})
public class FileUploadController {
		
	@RequestMapping(value = "/upload/result", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadSuccessRequest(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean bean, Model model) {
		String fileName = bean.getFileName();
		model.addAttribute("files", fileName);
		return "upload/result";
	}
	
	@RequestMapping(value = "/upload/headings/select.onchange", method = {RequestMethod.POST, RequestMethod.GET})
    public String uploadHeadingsSelectOnchangeRequest(@ModelAttribute("fileUploadSessionBean") FileUploadSessionBean fileUploadSessionBean) {
		fileUploadSessionBean.reorg();
		return "upload/headings";
	}
}