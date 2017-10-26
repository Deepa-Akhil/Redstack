package com.enterprise.businessservices;

import com.enterprise.common.pojo.UserSecurityBean;
import com.enterprise.domain.entity.User;

public interface SecurityBusinessService {
	public abstract UserSecurityBean authenticate(UserSecurityBean input, String sessionId);
	public abstract UserSecurityBean authorise(UserSecurityBean input);
	public User searchDatabase(String username)  throws Exception;
}