package com.enterprise.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class DeepDiveUser extends User{
	private static final long serialVersionUID = -2126374796787380832L;
	private long id;
	private String userName;
	private int role;
	private String compName;
	
	public DeepDiveUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}

	/*public DeepDiveUser(String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompName() {
		return compName;
	}
	
}
