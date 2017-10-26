package com.enterprise.domain.entity;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.enums.ModeTypes;
import com.enterprise.common.util.CollectionUtils;
import com.enterprise.common.util.StringUtils;

@Entity
@DiscriminatorValue("Airport")
public class Airport extends Port {

	private static final long serialVersionUID = -3924758740422374526L;

	@Override
	public ModeTypes getModeType() {
		return ModeTypes.A;
	}

	@Override
	public IEntity clone() {
		return null;
	}
	
	public static Airport load(String airportCd, String countryCd, Session session) {
		Criteria criteria = session.createCriteria(Airport.class)
			.add(Restrictions.eq("locCd", airportCd))
			.createAlias("country", "c1")
			.add(Restrictions.eq("c1.countryCd", countryCd));
		Airport airport = (Airport)criteria.uniqueResult();
		return airport;
	}
	
	@SuppressWarnings("unchecked")
	public static Airport loadPortNotAgainstCountry(String airportCd, Session session) {
		Criteria criteria = session.createCriteria(Airport.class)
			.add(Restrictions.eq("locCd", airportCd))
			.createAlias("country", "c1");
		List<Airport> airports = criteria.list();
		Airport airport = null;
		if(!CollectionUtils.isEmpty(airports)){
			airport = (Airport)airports.get(0);
		}
		return airport;
	}
	
	public static Airport load(String uniqueCd, Session session) {
		Criteria criteria = session.createCriteria(Airport.class)
			.add(Restrictions.eq("uniqueCd", uniqueCd));
		Airport airport = (Airport)criteria.uniqueResult();
		return airport;
	}
	
	@Override
	public void setUniqueCd(String uniqueCd) {
		if (!StringUtils.isEmpty(uniqueCd)
				&& uniqueCd.length() > 6)
			throw new RuntimeException("Value [" + uniqueCd + "] as the airport unique code is invalid. " + 
					"The system expects a valid code of no longer than 6 characters.");
		super.setUniqueCd(uniqueCd);
	}
	
	@Override
	public void setLocCd(String locCd) {
		if (!StringUtils.isEmpty(locCd)
				&& locCd.length() > 6)
			throw new RuntimeException("Value [" + locCd + "] as the airport code is invalid. " + 
					"The system expects a valid code no longer than 4 characters.");
		super.setLocCd(locCd);
	}
	
	@Override
	public void setLocName(String locName) {
		if (!StringUtils.isEmpty(locName)
				&& locName.length() > 6)
			throw new RuntimeException("Value [" + locName + "] as the airport name is invalid. " + 
					"The system expects a valid name no longer than 128 characters.");
		super.setLocName(locName);
	}
}