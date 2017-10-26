package com.enterprise.common.util;

public class ObjectUtils {
	public static boolean isValidId(long id) {
		return (id > 0) ? true : false;
	}
}