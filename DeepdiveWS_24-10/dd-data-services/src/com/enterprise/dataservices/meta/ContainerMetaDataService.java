package com.enterprise.dataservices.meta;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.enterprise.common.Logger;
import com.enterprise.common.enums.SubTypes;
import com.enterprise.domain.entity.meta.ContainerStatusMeta;

@Repository(value="containerMetaDataService")
public class ContainerMetaDataService extends AbstractMetaDataService {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public void load(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		Session session = sessionFactory.getCurrentSession();
		ContainerStatusMeta containerStatusMeta = null;
		try {
			containerStatusMeta = this.newContainerMeta(columnName, entity, getter, setter, subType);
			session.save(containerStatusMeta);
		} catch (Throwable e) {
			Logger.warning(e.getMessage(), this.getClass());
			session.clear();
		}
	}
	
	private ContainerStatusMeta newContainerMeta(String columnName, Class<?> entity, String getter, String setter, SubTypes subType) {
		return (ContainerStatusMeta)this.setMetaProperties(new ContainerStatusMeta(), columnName, entity, getter, setter, subType);
	}
}