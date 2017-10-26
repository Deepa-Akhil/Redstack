package com.enterprise.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.enterprise.common.pojo.AsyncSessionObject;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  18 Mar 2014 8:22:30 AM
 * @author rafourie
 */
@Controller
@SessionAttributes({"asyncSessionObject"})
public class AjaxAsyncPageController {
	@RequestMapping(value = "/ajax/async", method = {RequestMethod.POST, RequestMethod.GET})
    public String ajaxAsyncRequest(@ModelAttribute("asyncSessionObject") AsyncSessionObject obj) {
		return "async";
	}
}