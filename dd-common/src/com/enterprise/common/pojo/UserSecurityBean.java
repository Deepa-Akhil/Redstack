package com.enterprise.common.pojo;

import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.xsd.webelementcomplextypes.PasswordInputElement;
import com.enterprise.common.xsd.webelementcomplextypes.UsernameInputElement;
import com.enterprise.common.xsd.webelementcomplextypes.impl.PasswordInputElementImpl;
import com.enterprise.common.xsd.webelementcomplextypes.impl.UsernameInputElementImpl;

public class UserSecurityBean implements IBean {
	private static final long serialVersionUID = -1283678917337616738L;
	private long id = -1L;
	private final UsernameInputElement usernameElement = new UsernameInputElementImpl();
	private final PasswordInputElement passwordElement = new PasswordInputElementImpl();
	private boolean authenticated = false;
	
	public UserSecurityBean() {
		usernameElement.setUsername("admin");
		passwordElement.setPassword("admin");
	}

	public PasswordInputElement getPasswordElement() {
		return passwordElement;
	}
	
	public String toString() {
		return "username=" + usernameElement.getUsername() + ",password=";
	}

	public UsernameInputElement getUsernameElement() {
		return usernameElement;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void reset() {
		id = -1L;
		usernameElement.setUsername("admin");
		passwordElement.setPassword("admin");
		authenticated = false;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
}