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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;

@Entity
@Table(name = "USER_ACCESS", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "USER_ACCESS", 
	indexes = { 
		@Index(name = "useraccess_id_ix", columnNames = { "ID" }),
		@Index(name = "useraccess_user_pkg", columnNames = { "USER_ID", "PACKAGE_ID" })
	}
)
public class UserAccess implements IEntity, Serializable {
	private static final long serialVersionUID = 1248986311409990919L;

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_ACCESS_SEQ")
	@SequenceGenerator(name = "USER_ACCESS_SEQ", sequenceName = "GLOBAL.USER_ACCESS_SEQ")
	@Column(name = "ID", nullable = false)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	private long id;
	
	/*@Temporal(TemporalType.TIMESTAMP)
 	@Column(name="CREATED_DATE", updatable=false)
    public Date createdDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATED_DATE")
    public Date updatedDate = new Date();*/
	
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
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	/*public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}*/
	public boolean isEmpty() {
		return false;
	}
	
	public IEntity clone() {
		return null;
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
}