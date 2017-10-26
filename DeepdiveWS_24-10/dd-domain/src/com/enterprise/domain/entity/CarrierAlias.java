package com.enterprise.domain.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;
import com.enterprise.domain.util.EntityUtils;

@Entity
@Table(name = "CARRIER_ALIAS", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "CARRIER_ALIAS", 
	indexes = { 
		@Index(name = "carrieralias_id_ix", columnNames = { "ID" } )
	}
)
public class CarrierAlias implements IEntity, Serializable{

	private static final long serialVersionUID = 4126189716908370195L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CARRIER_ALIAS_SEQ")
	@SequenceGenerator(name = "CARRIER_ALIAS_SEQ", sequenceName = "GLOBAL.CARRIER_ALIAS_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "ALIAS_NAME")
	private String aliasName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CARRIER_ID", referencedColumnName = "ID", nullable = true)
	})
	private Carrier carrier;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID", nullable = true)
	})
	private Package pkg;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public Carrier getCarrier() {
		return EntityUtils.initializeAndUnproxy(carrier);
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Package getPkg() {
		return pkg;
	}

	public void setPkg(Package pkg) {
		this.pkg = pkg;
	}

	public boolean isEmpty() {
		return false;
	}
}
