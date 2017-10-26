package com.enterprise.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.StringUtils;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  11 Mar 2014 10:29:29 AM
 * @author rafourie
 */
@Entity
@DiscriminatorValue("City")
public class City extends Location {
	private static final long serialVersionUID = -5388242122731386688L;
		
	public IEntity clone() {
		return null;
	}
	
	@Override
	public void setUniqueCd(String uniqueCd) {
		if (!StringUtils.isEmpty(uniqueCd)
				&& uniqueCd.length() > 6)
			throw new RuntimeException("Value [" + uniqueCd + "] as the city unique code is invalid. " + 
					"The system expects a valid code of no longer than 6 characters.");
		super.setUniqueCd(uniqueCd);
	}
	
	@Override
	public void setLocCd(String locCd) {
		if (!StringUtils.isEmpty(locCd)
				&& locCd.length() > 6)
			throw new RuntimeException("Value [" + locCd + "] as the city code is invalid. " + 
					"The system expects a valid code no longer than 4 characters.");
		super.setLocCd(locCd);
	}
	
	@Override
	public void setLocName(String locName) {
		if (!StringUtils.isEmpty(locName)
				&& locName.length() > 6)
			throw new RuntimeException("Value [" + locName + "] as the city name is invalid. " + 
					"The system expects a valid name no longer than 128 characters.");
		super.setLocName(locName);
	}
}