package com.enterprise.web.service.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component(value="userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	//private static final Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);
	
	//@Autowired
	//private SecurityFacadeServiceClient securityFacadeServiceClient;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		UserDetailsImpl userDetails = null;
		return (UserDetails)userDetails;
	}
}