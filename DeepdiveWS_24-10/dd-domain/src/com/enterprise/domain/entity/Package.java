package com.enterprise.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;
import com.enterprise.domain.entity.meta.Meta;

@Entity
@Table(name = "PACKAGE", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "PACKAGE", 
	indexes = { 
		@Index(name = "package_id_ix", columnNames = { "ID" } )
	}
)
public class Package implements IEntity, Serializable {
	private static final long serialVersionUID = 1248986311409990919L;

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PACKAGE_SEQ")
	@SequenceGenerator(name = "PACKAGE_SEQ", sequenceName = "GLOBAL.PACKAGE_SEQ")
	@Column(name = "ID", nullable = false)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	private long id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@Column(name = "DESCRIPTION", nullable = true, columnDefinition="TEXT")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false, updatable=false)
	private Date createdDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATED", nullable = false)
	private Date lastUpdated = new Date();
	
	@Column(name="DELETE_FLAG")
	private boolean deleted = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CREATED_USER_ID", referencedColumnName = "ID", nullable = true, updatable=false)
	})
	private User createdUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "LAST_UPDATED_USER_ID", referencedColumnName = "ID", nullable = true)
	})
	private User lastEditedUser;
	
	@OneToMany(mappedBy = "pkg", fetch = FetchType.LAZY)
   	private List<UserAccess> userAccesses = new ArrayList<UserAccess>();
	
	@OneToMany(mappedBy = "pkg", fetch = FetchType.LAZY)
   	private List<Shipment> shipments = new ArrayList<Shipment>();
	
	@OneToMany(mappedBy = "pkg", fetch = FetchType.LAZY)
   	private List<Meta> metas = new ArrayList<Meta>();
	
	@OneToMany(mappedBy = "pkg", fetch = FetchType.LAZY)
	private List<LoadHistory> loadHistories = new ArrayList<LoadHistory>();
	
	@OneToMany(mappedBy = "pkg", fetch = FetchType.LAZY)
	private List<Rate> rates = new ArrayList<Rate>();
	
	@OneToMany(mappedBy = "pkg", fetch = FetchType.LAZY)
	private List<CarrierAlias> carrierAlias = new ArrayList<CarrierAlias>();
	
	@OneToMany(mappedBy = "pkg", fetch = FetchType.LAZY)
	private List<Invoice> invoices = new ArrayList<Invoice>();
	
	@OneToMany(mappedBy = "pkg", fetch = FetchType.LAZY)
	private List<SubPackageDetail> subPackageDetails = new ArrayList<SubPackageDetail>();
	
	@OneToMany(mappedBy = "pkg", fetch = FetchType.LAZY)
	private List<Custom> customs = new ArrayList<Custom>();
	

	public List<SubPackageDetail> getSubPackageDetails() {
		return subPackageDetails;
	}

	public void setSubPackageDetails(List<SubPackageDetail> subPackageDetails) {
		this.subPackageDetails = subPackageDetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEmpty() {
		return false;
	}
	
	public IEntity clone() {
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public User getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(User createdUser) {
		this.createdUser = createdUser;
	}
	
	public List<UserAccess> getUserAccesses() {
		return userAccesses;
	}

	public void setUserAccesses(List<UserAccess> userAccesses) {
		this.userAccesses = userAccesses;
	}
	
	public List<Shipment> getShipments() {
		return shipments;
	}

	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}
	
	public List<Meta> getMetas() {
		return metas;
	}

	public void setMetas(List<Meta> metas) {
		this.metas = metas;
	}
	
	public User getLastEditedUser() {
		return lastEditedUser;
	}

	public void setLastEditedUser(User lastEditedUser) {
		this.lastEditedUser = lastEditedUser;
	}
	
	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public List<LoadHistory> getLoadHistories() {
		return loadHistories;
	}

	public void setLoadHistories(List<LoadHistory> loadHistories) {
		this.loadHistories = loadHistories;
	}
	
	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}
	
	public List<CarrierAlias> getCarrierAlias() {
		return carrierAlias;
	}

	public void setCarrierAlias(List<CarrierAlias> carrierAlias) {
		this.carrierAlias = carrierAlias;
	}

	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public void setCustoms(List<Custom> customs) {
		this.customs = customs;
	}

	public List<Custom> getCustoms() {
		return customs;
	}
}