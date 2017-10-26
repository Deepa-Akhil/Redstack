package com.enterprise.domain.entity.web.transform;

import com.enterprise.common.pojo.UserSecurityBean;
import com.enterprise.domain.entity.User;
import com.enterprise.domain.util.EntityUtils;

public class UserTransformer {
	private static final UserTransformer instance = new UserTransformer();

	public static UserTransformer getInstance() {
		return instance;
	}
	
	private UserTransformer() {/* no implementation */}
	
	public UserSecurityBean fromWebUser(User user, UserSecurityBean bean) {
		if (!EntityUtils.isEmpty(user)) {
			bean.setId(user.getId());
			bean.getUsernameElement().setUsername(user.getUsername());
		}
		return bean;
	}
}