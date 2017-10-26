package com.enterprise.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "CMF", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"CLIENT_CD"})
)
@org.hibernate.annotations.Table(
	appliesTo = "CMF", 
	indexes = { 
		@Index(name = "cmf_id_ix", columnNames = { "ID" }),
		@Index(name = "cmf_client_cd_ix", columnNames = { "CLIENT_CD" }),
		@Index(name = "cmf_search_alias_ix", columnNames = { "SEARCH_ALIAS" })
	}
)
public class CMF implements IEntity, Serializable {

	private static final long serialVersionUID = 5892507812556054871L;

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CMF_SEQ")
	@SequenceGenerator(name = "CMF_SEQ", sequenceName = "GLOBAL.CMF_SEQ")
	@Column(name = "ID", nullable = false)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	private long id;
	
	/** CLIENT_CD varchar2(16) NOT NULL, */
	@Column(name = "CLIENT_CD", nullable = false, length = 16)
	private String clientCd;
	
	/** ALIAS varchar2(128) NOT NULL, */
	@Column(name = "ALIAS", nullable = true, length = 128)
	private String alias;
	
	/** SEARCH_ALIAS varchar2(128) NOT NULL, */
	@Column(name = "SEARCH_ALIAS", nullable = true, length = 128)
	private String searchAlias;
	
	@OneToMany(mappedBy = "consignee", fetch = FetchType.LAZY)
	private List<Shipment> shipmentsAsConsignee = new ArrayList<Shipment>();
	
	@OneToMany(mappedBy = "shipper", fetch = FetchType.LAZY)
	private List<Shipment> shipmentsAsShipper = new ArrayList<Shipment>();
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientCd() {
		return clientCd;
	}

	public void setClientCd(String clientCd) {
		if (!StringUtils.isEmpty(clientCd)
				&& clientCd.length() > 16)
			throw new RuntimeException("Value [" + clientCd + "] as the client code is invalid. " + 
					"The system expects a valid code no longer than 16 characters.");
		this.clientCd = clientCd;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		if (!StringUtils.isEmpty(alias)
				&& alias.length() > 128)
			throw new RuntimeException("Value [" + alias + "] as the client alias is invalid. " + 
					"The system expects a valid alias no longer than 128 characters.");
		this.alias = alias;
		this.setSearchAlias(StringUtils.spacelessLowerCase(alias));
	}

	public List<Shipment> getShipmentsAsConsignee() {
		return shipmentsAsConsignee;
	}

	public void setShipmentsAsConsignee(List<Shipment> shipmentsAsConsignee) {
		this.shipmentsAsConsignee = shipmentsAsConsignee;
	}

	public List<Shipment> getShipmentsAsShipper() {
		return shipmentsAsShipper;
	}

	public void setShipmentsAsShipper(List<Shipment> shipmentsAsShipper) {
		this.shipmentsAsShipper = shipmentsAsShipper;
	}

	public String getSearchAlias() {
		return searchAlias;
	}

	private void setSearchAlias(String searchAlias) {
		this.searchAlias = searchAlias;
	}

	public boolean isEmpty() {
		return false;
	}
}