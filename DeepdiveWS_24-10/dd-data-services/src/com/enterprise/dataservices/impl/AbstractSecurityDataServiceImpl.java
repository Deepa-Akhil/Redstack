package com.enterprise.dataservices.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.enterprise.common.pojo.UserSecurityBean;

public abstract class AbstractSecurityDataServiceImpl {
	@Autowired
	protected SessionFactory sessionFactory;
	
	public UserSecurityBean authorise(UserSecurityBean input) {
		//Session session = sessionFactory.getCurrentSession();
		//UsernameInputElement usernameElement = input.getUsernameElement();
		//String username = usernameElement.getUsername();
		return input;
	}
}