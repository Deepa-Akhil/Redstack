package com.enterprise.web;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.enterprise.common.Logger;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  11 Mar 2014 3:59:16 PM
 * @author rafourie
 */
public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet {

	private static final long serialVersionUID = -557108353238556537L;

	private static final Class<DispatcherServlet> CLASS = DispatcherServlet.class;
	
	private WebApplicationContext applicationContext = null;
	
	@Override
	public void init(ServletConfig config) {
		try {
			super.init(config);
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
			ApplicationContext.init(applicationContext);
			/*(new Thread() {
				public void run() {
					FileUtils fileUtils = (FileUtils) applicationContext.getBean("fileUtils");
					fileUtils.watchDropBoxFiles();
				}
			}).start();*/
		} catch (ServletException e) {
			Logger.error(CLASS, e);
		}
	}
}