package com.enterprise.web.filter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.enterprise.common.Logger;
import com.enterprise.common.annotation.SessionObject;
import com.enterprise.common.pojo.JSessionBean;

@Component(value = "httpRequestFilter")
public class HttpRequestFilter extends OncePerRequestFilter {
	@Autowired
	private ApplicationContext applicationContext;
		
	public HttpRequestFilter() {/* no implementation */}

	public void destroy() {/* no implementation */}

	public void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain) 
		throws IOException, ServletException {
		HttpSession session = httpRequest.getSession(true);
		JSessionBean jSessionBean = (JSessionBean)session.getAttribute("jSessionBean");
		if (jSessionBean == null) {
			try {
				Map<String, Object> sessionObjects = applicationContext.getBeansWithAnnotation(SessionObject.class);
				Set<String> sessionObjectNames = sessionObjects.keySet();
				for (String sessionObjectName : sessionObjectNames) {
					Object sessionObject = sessionObjects.get(sessionObjectName);
					Class<? extends Object> sessionObjectClass = sessionObject.getClass();
					Annotation annotation = sessionObjectClass.getAnnotation(SessionObject.class);
					SessionObject sessionObjectAnnotation = (SessionObject)annotation;
					session.setAttribute(sessionObjectAnnotation.id(), sessionObject);
				}
				jSessionBean = (JSessionBean)session.getAttribute("jSessionBean");
				jSessionBean.setSessionId(session.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		String httpRequestURI = httpRequest.getRequestURI();
		Logger.debug("request uri [" + httpRequestURI + "] ", this.getClass());
		chain.doFilter(httpRequest, httpResponse);
	}
}