package com.enterprise.common.util;

import java.util.Collection;

public final class CollectionUtils {
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection collection) {
		if (collection == null) {
			return true;
		} else {
			return collection.isEmpty();
		}
	}
}