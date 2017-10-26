package com.enterprise.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.enums.LoadType;
import com.enterprise.common.enums.ScheduleType;

@Entity
@javax.persistence.Table(name = "SUB_PACKAGE_DETAIL", schema = "GLOBAL", uniqueConstraints =
		@UniqueConstraint(columnNames = { "PACKAGE_ID", "LOAD_TYPE" }))
@org.hibernate.annotations.Table(
	appliesTo = "SUB_PACKAGE_DETAIL", 
	indexes = {
		@Index(name = "sub_package_ix", columnNames = { "ID" } )
	}
)
@org.hibernate.annotations.Entity(
		dynamicInsert = true
)
public class SubPackageDetail implements IEntity, Serializable{

	private static final long serialVersionUID = 7986128312251756812L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SUB_PACKAGE_DETAIL_SEQ")
	@SequenceGenerator(name = "SUB_PACKAGE_DETAIL_SEQ", sequenceName = "GLOBAL.SUB_PACKAGE_DETAIL_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "LOAD_TYPE", length = 2, nullable = false, updatable=false)
	private LoadType loadType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID", nullable = false)
	})
	private Package pkg;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", nullable = false, updatable=false)
	private Date createdDate = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_UPDATED", nullable = false)
	private Date lastUpdated = new Date();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SCHEDULE", length = 1, nullable = false, updatable=false)
	private ScheduleType schedule = ScheduleType.M;
	
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
	
	@Column(name = "IS_ALIAS")
	private boolean alias = false; 

	@Column(name = "ROWS_TO_REMOVE")
	private int rowsToRemove = 12;
	
	@Column(name = "COLUMNS_TO_REMOVE")
	private int columnsToRemove = 0;
	
	@Column(name = "REPLY_MAIL_ID", nullable = false, length = 45)
   	private String emailId = "alreynolds@live.com.au";
	
	@Column(name = "IS_LOCATION_ALIAS")
	private boolean locationAlias = false; 
	
	@Column(name = "MIN_FILLED_CELLS")
	private int minFillesCells = 0;
	
	@Column(name = "IS_SPECIALCHAR")
	private boolean isSpecialChar = false;
	
	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}

	public boolean isEmpty() {
		return false;
	}

	public LoadType getLoadType() {
		return loadType;
	}

	public void setLoadType(LoadType loadType) {
		this.loadType = loadType;
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
	
	public Package getPkg() {
		return pkg;
	}

	public void setPkg(Package pkg) {
		this.pkg = pkg;
	}
	
	public ScheduleType getSchedule() {
		return schedule;
	}
	
	public void setSchedule(ScheduleType schedule) {
		this.schedule = schedule;
	}
	
	public User getCreatedUser() {
		return createdUser;
	}
	
	public void setCreatedUser(User createdUser) {
		this.createdUser = createdUser;
	}
	
	public User getLastEditedUser() {
		return lastEditedUser;
	}
	
	public void setLastEditedUser(User lastEditedUser) {
		this.lastEditedUser = lastEditedUser;
	}
	public boolean isAlias() {
		return alias;
	}
	public void setAlias(boolean alias) {
		this.alias = alias;
	}
	public int getRowsToRemove() {
		return rowsToRemove;
	}
	public void setRowsToRemove(int rowsToRemove) {
		this.rowsToRemove = rowsToRemove;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setColumnsToRemove(int columnsToRemove) {
		this.columnsToRemove = columnsToRemove;
	}
	public int getColumnsToRemove() {
		return columnsToRemove;
	}
	public void setLocationAlias(boolean locationAlias) {
		this.locationAlias = locationAlias;
	}
	public boolean isLocationAlias() {
		return locationAlias;
	}
	public void setMinFillesCells(int minFillesCells) {
		this.minFillesCells = minFillesCells;
	}
	public int getMinFillesCells() {
		return minFillesCells;
	}
	public void setSpecialChar(boolean isSpecialChar) {
		this.isSpecialChar = isSpecialChar;
	}
	public boolean isSpecialChar() {
		return isSpecialChar;
	}
}
