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
@DiscriminatorValue("Seaport")
public class Seaport extends Port {

	private static final long serialVersionUID = 1755035404792887122L;

	@Override
	public ModeTypes getModeType() {
		return ModeTypes.O;
	}
	
	@Override
	public IEntity clone() {
		return null;
	}
	
	public static Seaport load(String seaportCd, String countryCd, Session session) {
		Criteria criteria = session.createCriteria(Seaport.class)
			.add(Restrictions.eq("locCd", seaportCd))
			.createAlias("country", "c1")
			.add(Restrictions.eq("c1.countryCd", countryCd));
		Seaport seaport = (Seaport)criteria.uniqueResult();
		return seaport;
	}
	
	@SuppressWarnings("unchecked")
	public static Seaport loadPortNotAgainstCountry(String seaportCd, Session session) {
		Criteria criteria = session.createCriteria(Seaport.class)
			.add(Restrictions.eq("locCd", seaportCd))
			.createAlias("country", "c1");
		List<Seaport> seaports = criteria.list();
		Seaport seaport = null;
		if(!CollectionUtils.isEmpty(seaports)){
			seaport = (Seaport)seaports.get(0);
		}
		return seaport;
	}
	
	public static Seaport load(String uniqueCd, Session session) {
		Criteria criteria = session.createCriteria(Seaport.class)
			.add(Restrictions.eq("uniqueCd", uniqueCd));
		Seaport seaport = (Seaport)criteria.uniqueResult();
		return seaport;
	}
	
	@Override
	public void setUniqueCd(String uniqueCd) {
		if (!StringUtils.isEmpty(uniqueCd)
				&& uniqueCd.length() > 6)
			throw new RuntimeException("Value [" + uniqueCd + "] for the seaport unique code is invalid. " + 
					"The system expects a valid code of no longer than 6 characters.");
		super.setUniqueCd(uniqueCd);
	}
	
	@Override
	public void setLocCd(String locCd) {
		if (!StringUtils.isEmpty(locCd)
				&& locCd.length() > 6)
			throw new RuntimeException("Value [" + locCd + "] for the seaport code is invalid. " + 
					"The system expects a valid code no longer than 4 characters.");
		super.setLocCd(locCd);
	}
	
	@Override
	public void setLocName(String locName) {
		if (!StringUtils.isEmpty(locName)
				&& locName.length() > 6)
			throw new RuntimeException("Value [" + locName + "] for the seaport name is invalid. " + 
					"The system expects a valid name no longer than 128 characters.");
		super.setLocName(locName);
	}
}