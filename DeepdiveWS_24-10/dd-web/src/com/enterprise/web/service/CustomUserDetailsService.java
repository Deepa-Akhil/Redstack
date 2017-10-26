package com.enterprise.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.enterprise.businessservices.SecurityBusinessService;
import com.enterprise.user.DeepDiveUser;


/**
 * A custom service for retrieving users from a custom datasource, such as a database.
 * <p>
 * This custom service must implement Spring's {@link UserDetailsService}
 */
@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private SecurityBusinessService securityBusinessService;
	/**
	 * Retrieves a user record containing the user's credentials and access. 
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		
		DeepDiveUser aUser = null;
		try {
			com.enterprise.domain.entity.User dbUser = securityBusinessService.searchDatabase(username);
			if(dbUser!=null){
				aUser =  new DeepDiveUser(
						dbUser.getUsername(), 
						dbUser.getPassword(),
						true,
						true,
						true,
						true,
						getAuthorities(dbUser.getUserRole()) ); 
				aUser.setId(dbUser.getId());
				aUser.setRole(dbUser.getUserRole());
				aUser.setUserName(dbUser.getFirstName() + " " + dbUser.getLastName());
				aUser.setCompName(dbUser.getCompanyName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		return aUser;
	}

	/**
	 * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
	 * Basically, this interprets the access value whether it's for a regular user or admin.
	 * 
	 * @param access an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	public Collection<GrantedAuthority> getAuthorities(int access) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
		if(access==1){
			authList.add(new GrantedAuthorityImpl("INTERNAL_USER"));
		}if(access==2){
			authList.add(new GrantedAuthorityImpl("CLIENT_USER"));
		}	
		return authList;
	}
}
