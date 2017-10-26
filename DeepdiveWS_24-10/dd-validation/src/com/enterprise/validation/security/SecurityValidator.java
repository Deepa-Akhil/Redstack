package com.enterprise.validation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.enterprise.common.jaxb.InputElement;
import com.enterprise.common.jaxb.WebElementsJAXBHelper;
import com.enterprise.common.pojo.UserSecurityBean;

@Component
public class SecurityValidator {
	
	@Autowired
	private WebElementsJAXBHelper helper;
	
	public boolean validateInput(UserSecurityBean userSecurityBean) {
		boolean valid = true;
		InputElement username = helper.validate(userSecurityBean.getUsernameElement(), "errorcodetypes.username_is_required", "errorcodetypes.username_is_required");
		valid = username.isValid();
		if (valid) {
			InputElement password = helper.validate(userSecurityBean.getPasswordElement(), "errorcodetypes.password_is_required", "errorcodetypes.password_is_required");
			valid = password.isValid();
		}
		return valid;
	}
}