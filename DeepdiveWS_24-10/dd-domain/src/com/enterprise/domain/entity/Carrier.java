package com.enterprise.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.enums.ModeTypes;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "CARRIER", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"CARRIER_CD"})
)
@org.hibernate.annotations.Table(
	appliesTo = "CARRIER", 
	indexes = { 
		@Index(name = "carrier_id_ix", columnNames = { "ID" }),
		@Index(name = "carrier_cd_ix", columnNames = { "CARRIER_CD" }),
		@Index(name = "carrier_alias_ix", columnNames = { "CARRIER_ALIAS" })
	}
)
public class Carrier implements IEntity, Serializable {

	private static final long serialVersionUID = -6865357057935398632L;

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CARRIER_SEQ")
	@SequenceGenerator(name = "CARRIER_SEQ", sequenceName = "GLOBAL.CARRIER_SEQ")
	@Column(name = "ID", nullable = false)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	private long id;
	
	/** CARRIER_CD varchar2(4) NOT NULL, */
	@Column(name = "CARRIER_CD", nullable = false, length = 4)
	private String carrierCd;
	
	/** CARRIER_ALIAS varchar2(4) NOT NULL, */
	@Column(name = "CARRIER_ALIAS", nullable = true, length = 4)
	private String carrierAlias;
	
	/** NAME varchar2(128) NOT NULL, */
	@Column(name = "NAME", nullable = true, length = 128)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = true, length = 128)
	private String description;
	
	@Column(name = "WEB_URL", nullable = true, length = 128)
	private String webUrl;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MODE", nullable = false, length = 1)
	private ModeTypes mode = ModeTypes.A;
	
	@OneToMany(mappedBy = "carrier", fetch = FetchType.LAZY)
	private List<CarrierAlias> carrierAliases = new ArrayList<CarrierAlias>();
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCarrierCd() {
		return carrierCd;
	}

	public void setCarrierCd(String carrierCd) {
		if (!StringUtils.isEmpty(carrierCd)
				&& carrierCd.length() > 15)
			throw new RuntimeException("Value [" + carrierCd + "] as the carrier code is invalid. " + 
					"The system expects a valid code no longer than 4 characters.");
		this.carrierCd = carrierCd;
	}

	public String getCarrierAlias() {
		return carrierAlias;
	}

	public void setCarrierAlias(String carrierAlias) {
		if (!StringUtils.isEmpty(carrierAlias)
				&& carrierAlias.length() > 15)
			throw new RuntimeException("Value [" + carrierAlias + "] as the carrier alias is invalid. " + 
					"The system expects a valid alias no longer than 4 characters.");
		this.carrierAlias = carrierAlias;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (!StringUtils.isEmpty(name)
				&& name.length() > 128)
			throw new RuntimeException("Value [" + name + "] as the carrier name is invalid. " + 
					"The system expects a valid name no longer than 128 characters.");
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if (!StringUtils.isEmpty(description)
				&& description.length() > 128)
			throw new RuntimeException();
		this.description = description;
	}

	public String getWebUrl() {
		return webUrl;
	}

	public void setWebUrl(String webUrl) {
		if (!StringUtils.isEmpty(webUrl)
				&& webUrl.length() > 128)
			throw new RuntimeException("Value [" + webUrl + "] as the web url is invalid. " + 
					"The system expects a valid url no longer than 128 characters.");
		this.webUrl = webUrl;
	}

	public ModeTypes getMode() {
		throw new RuntimeException("getMode() of the Carrier entity must be overridden in subclass.");
	}

	public void setMode(ModeTypes mode) {
		this.mode = mode;
	}
	
	public void setMode(String mode) {
		ModeTypes modeType = null;
		try {
			modeType = ModeTypes.valueOf(mode);
			this.mode = modeType;
		} catch (Throwable e) {
			modeType = ModeTypes.valueOfByMode(mode);
			if (modeType == null)
				throw new RuntimeException("Value [" + mode + "] as the mode is invalid. The system expects " + 
						"a 1 character value of either 'A' or 'O'.");
			this.mode = modeType;
		}
	}

	public boolean isEmpty() {
		return false;
	}
	
	public static String getCarrierCd(Session session, long id) {
		Criteria criteria = session.createCriteria(Carrier.class)
			.add(Restrictions.eq("id", id));
		Carrier carrier = (Carrier)criteria.uniqueResult();
		if(carrier != null) {
			return carrier.getCarrierCd();
		}
		return null;
	}

	public void setCarrierAliases(List<CarrierAlias> carrierAliases) {
		this.carrierAliases = carrierAliases;
	}

	public List<CarrierAlias> getCarrierAliases() {
		return carrierAliases;
	}
}