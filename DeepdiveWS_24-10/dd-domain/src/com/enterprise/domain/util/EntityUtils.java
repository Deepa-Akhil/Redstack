package com.enterprise.domain.util;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.CollectionUtils;
import com.enterprise.common.util.ObjectUtils;

public class EntityUtils {
	public static boolean isValidEntity(long entityNo) {
		return ObjectUtils.isValidId(entityNo);
	}
		
	@SuppressWarnings("rawtypes")
	public static boolean isCollectionEmpty(List list) {
		return CollectionUtils.isEmpty(list);
	}
	
	public static boolean isEmpty(IEntity entity) {
		if (entity == null) {
			return true;
		} else {
			try {
				if (entity.getId() == 0L) {
					return true;
				} else {
					return false;
				}
			} catch (org.hibernate.ObjectNotFoundException e) {
				return true;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T initializeAndUnproxy(T entity) {
		if (entity == null) {
			return null;
		}

		if (entity instanceof HibernateProxy) {
			Hibernate.initialize(entity);
			entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
		}
		return entity;
	}
}