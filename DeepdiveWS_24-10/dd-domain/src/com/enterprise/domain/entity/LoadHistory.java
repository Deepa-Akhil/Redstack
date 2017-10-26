package com.enterprise.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;

@Entity
@Table(name = "LOAD_HISTORY", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "LOAD_HISTORY", 
	indexes = { 
		@Index(name = "loadhistory_idx", columnNames = { "ID" } ),
		@Index(name = "loadhistory_unique_keys", columnNames = { "USER_ID", "PACKAGE_ID" })
	}
)
public class LoadHistory implements IEntity, Serializable  {

	private static final long serialVersionUID = 1694244315302898696L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LOAD_HISTORY_SEQ")
	@SequenceGenerator(name = "LOAD_HISTORY_SEQ", sequenceName = "GLOBAL.LOAD_HISTORY_SEQ")
	@Column(name = "ID", nullable = false)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOAD_DATE", nullable = true, updatable=false)
	private Date loadDate = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
	})
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID", nullable = false)
	})
	private Package pkg;
	
	@Column(name = "SUCCESS", nullable = true, precision = 13, scale = 0)
	private BigDecimal success;
	
	@Column(name = "FAILURE", nullable = true, precision = 13, scale = 0)
	private BigDecimal failure;
	
	//loadType = 1 for Shipment, 2 for Rate, 3 for Invoice, 4 Order
	@Column(name = "LOAD_TYPE", columnDefinition = "TINYINT", length=1)
	private int loadType = 1;
	
	//true for Webload false for emailload
	@Column(name = "IS_WEBLOAD")
	private boolean webload = true; 
	
	@Column(name = "FILE_NAME")
	private String fileName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEmpty() {
		return false;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Package getPkg() {
		return pkg;
	}

	public void setPkg(Package pkg) {
		this.pkg = pkg;
	}
	
	public BigDecimal getSuccess() {

		return success;
	}

	public void setSuccess(BigDecimal success) {
		this.success = success;
	}

	public BigDecimal getFailure() {
		return failure;
	}

	public void setFailure(BigDecimal failure) {
		this.failure = failure;
	}
	
	public int getLoadType() {
		return loadType;
	}

	public void setLoadType(int loadType) {
		this.loadType = loadType;
	}

	public boolean isWebload() {
		return webload;
	}

	public void setWebload(boolean webload) {
		this.webload = webload;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

}
