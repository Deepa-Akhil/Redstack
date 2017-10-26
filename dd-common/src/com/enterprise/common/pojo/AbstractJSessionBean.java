package com.enterprise.common.pojo;

import org.springframework.stereotype.Component;

import com.enterprise.common.interfaces.IBean;

@Component
public abstract class AbstractJSessionBean implements IBean {
	private static final long serialVersionUID = -5112545681104511905L;

	public abstract void reset();
}