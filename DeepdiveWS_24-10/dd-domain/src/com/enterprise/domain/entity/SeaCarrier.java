package com.enterprise.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.enums.ModeTypes;

@Entity
@DiscriminatorValue("O")
public class SeaCarrier extends Carrier implements ICarrier {
	
	private static final ModeTypes MODE = ModeTypes.O;
	
	private static final long serialVersionUID = -6892019619346354444L;

	public SeaCarrier() {
		super.setMode(MODE);
	}
	
	@Override
	public ModeTypes getMode() {
		return MODE;
	}
	
	public static SeaCarrier load(String code, Session session) {
		Criteria criteria = session.createCriteria(SeaCarrier.class)
			.add(Restrictions.eq("mode", MODE))
			.add(Restrictions.or(
					Restrictions.eq("carrierCd", code), 
					Restrictions.eq("carrierAlias", code)
				)
		);
		SeaCarrier seaCarrier = (SeaCarrier)criteria.uniqueResult();
		return seaCarrier;
	}
	
	@SuppressWarnings("deprecation")
	public static SeaCarrier loadAlias(String code, Session session, long packageId) {
		Criteria criteria = session.createCriteria(SeaCarrier.class)
			.add(Restrictions.eq("mode", MODE))
			.add(Restrictions.or(
					Restrictions.eq("carrierAlias", code), 
					Restrictions.eq("carrierCd", code))
				);
		SeaCarrier seaCarrier = (SeaCarrier)criteria.uniqueResult();
		if(seaCarrier == null) {
			Criteria aliasCriteria = session.createCriteria(CarrierAlias.class)
					.createAlias("carrier", "c")
					.createAlias("pkg", "p")
					.setFetchMode("carrier", FetchMode.EAGER)
					.setFetchMode("pkg", FetchMode.EAGER)
					.add(Restrictions.eq("aliasName", code))
					.add(Restrictions.eq("c.mode", MODE))
					.add(Restrictions.eq("p.id", packageId));
				CarrierAlias aliasSeaCarrier = (CarrierAlias) aliasCriteria.uniqueResult();
				if(null!=aliasSeaCarrier)
					seaCarrier = (SeaCarrier) aliasSeaCarrier.getCarrier();
		}
		return seaCarrier;
	}
}