package com.enterprise.common.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetProperty {

	@Autowired 
	Properties properties;

	public String getProperty(String key){
		return properties.getProperty(key);
	}
}
