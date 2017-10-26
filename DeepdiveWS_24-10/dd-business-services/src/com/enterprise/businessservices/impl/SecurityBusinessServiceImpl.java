package com.enterprise.businessservices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.businessservices.SecurityBusinessService;
import com.enterprise.common.pojo.UserSecurityBean;
import com.enterprise.dataservices.SecurityDataService;
import com.enterprise.domain.entity.User;

@Service
public class SecurityBusinessServiceImpl implements SecurityBusinessService {
	@Autowired
	private SecurityDataService securityDataService;

	@Transactional
	public UserSecurityBean authenticate(UserSecurityBean input, String sessionId) {
		return securityDataService.authenticate(input, sessionId);
	}
	
	@Transactional
	public UserSecurityBean authorise(UserSecurityBean input) {
		return securityDataService.authorise(input);
	}

	public User searchDatabase(String username) throws Exception {
		return securityDataService.searchDatabase(username);
	}
}