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
@Table(name = "LOCATION_ALIAS", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "LOCATION_ALIAS", 
	indexes = { 
		@Index(name = "locationalias_id_ix", columnNames = { "ID" } )
	}
)
public class LocationAlias implements IEntity, Serializable{

	private static final long serialVersionUID = 4126189716908370195L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LOCATION_ALIAS_SEQ")
	@SequenceGenerator(name = "LOCATION_ALIAS_SEQ", sequenceName = "GLOBAL.LOCATION_ALIAS_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "ALIAS_NAME")
	private String aliasName;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "LOCATION_ID", referencedColumnName = "ID", nullable = true)
	})
	private Location location;
	
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

	public Location getLocation() {
		return EntityUtils.initializeAndUnproxy(location);
	}

	public void setLocation(Location location) {
		this.location = location;
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
