package com.enterprise.dataservices.meta;

import com.enterprise.common.enums.SubTypes;
import com.enterprise.domain.entity.SubPackageDetail;
import com.enterprise.domain.entity.meta.Meta;

public abstract class AbstractMetaDataService {
	
	protected  Meta setMetaProperties(Meta meta, String columnName, Class<?> entity,  String getter, String setter, SubTypes subType) {
		meta.setColumnName(columnName);
		meta.setEntityGetter(getter);
		meta.setEntitySetter(setter);
		meta.setSubType(subType);
		meta.setEntityClass(entity.getName());
		com.enterprise.domain.entity.Package pkg = new com.enterprise.domain.entity.Package();
		pkg.setId(1L);
		SubPackageDetail subPkg = new SubPackageDetail();
		subPkg.setId(1001L);
		meta.setPkg(pkg);
		meta.setSubPkg(subPkg);
		return meta;
	}
}