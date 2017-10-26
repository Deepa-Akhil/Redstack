package com.enterprise.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.enums.ModeTypes;

@Entity
@DiscriminatorValue("A")
public class AirCarrier extends Carrier implements ICarrier {
	
	private static final ModeTypes MODE = ModeTypes.A;
	
	private static final long serialVersionUID = 6912646514662850365L;

	
	public AirCarrier() {
		super.setMode(MODE);
	}
	
	@Override
	public ModeTypes getMode() {
		return MODE;
	}
	
	public static AirCarrier load(String code, Session session) {
		Criteria criteria = session.createCriteria(AirCarrier.class)
			.add(Restrictions.eq("mode", MODE))
			.add(Restrictions.or(
					Restrictions.eq("carrierCd", code), 
					Restrictions.eq("carrierAlias", code)
				)
		);
		AirCarrier airCarrier = (AirCarrier)criteria.uniqueResult();
		return airCarrier;
	}
	
	@SuppressWarnings("deprecation")
	public static AirCarrier loadAlias(String code, Session session, long packageId) {
		Criteria criteria = session.createCriteria(AirCarrier.class)
				.add(Restrictions.eq("mode", MODE))
				.add(Restrictions.or(
						Restrictions.eq("carrierAlias", code), 
						Restrictions.eq("carrierCd", code))
						);
		AirCarrier airCarrier = (AirCarrier)criteria.uniqueResult();
		if(airCarrier == null) {
			Criteria aliasCriteria = session.createCriteria(CarrierAlias.class)
					.createAlias("carrier", "c")
					.createAlias("pkg", "p")
					.setFetchMode("carrier", FetchMode.EAGER)
					.setFetchMode("pkg", FetchMode.EAGER)
					.add(Restrictions.eq("aliasName", code))
					.add(Restrictions.eq("c.mode", MODE))
					.add(Restrictions.eq("p.id", packageId));
			CarrierAlias aliasAirCarrier = (CarrierAlias)aliasCriteria.uniqueResult();
			if(null!=aliasAirCarrier)
				airCarrier = (AirCarrier) aliasAirCarrier.getCarrier();
		} 
		return airCarrier;
	}
}