package com.enterprise.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;

import com.enterprise.common.util.StringUtils;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  17 Mar 2014 7:41:59 AM
 * @author akhil
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "DTYPE",
    discriminatorType = DiscriminatorType.STRING,
    length = 64
)
@Table(name = "LOCATION", 
		schema = "GLOBAL", 
		uniqueConstraints = @UniqueConstraint(columnNames = {"DTYPE", "UNIQUE_CD"})
)
@org.hibernate.annotations.Table(
	appliesTo = "LOCATION", 
	indexes = { 
		@Index(name = "loc_id_ix", columnNames = { "ID" } ),
		@Index(name = "loc_unique_cd_ix", columnNames = { "UNIQUE_CD" } )
	}
)
public class Location implements ILocation, Serializable {
	private static final long serialVersionUID = 2383730072129856205L;

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "LOCATION_SEQ")
	@SequenceGenerator(name = "LOCATION_SEQ", sequenceName = "GLOBAL.LOCATION_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	/** LOC_CD varchar2(4) NOT NULL, */
	@Column(name = "LOC_CD", nullable = false, length = 4)
	private String locCd;
	
	/** LOC_NAME varchar2(128) NOT NULL, */
	@Column(name = "LOC_NAME", nullable = false, length = 128)
	private String locName;
	
	/** UNIQUE_CD varchar2(6) NOT NULL, */
	@Column(name = "UNIQUE_CD", nullable = false, length = 6)
	private String uniqueCd;
	
	/** COUNTRY_ID decimal(19) NOT NULL, */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "COUNTRY_ID", referencedColumnName = "ID", nullable = false)
	})
	private Country country;
	
	@OneToMany(mappedBy = "location", fetch = FetchType.LAZY)
	private List<LocationAlias> locationAliases = new ArrayList<LocationAlias>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUniqueCd() {
		return uniqueCd;
	}

	public void setUniqueCd(String uniqueCd) {
		if (!StringUtils.isEmpty(uniqueCd)){
			uniqueCd=uniqueCd.replaceAll("[^a-zA-Z0-9]", "");
			if(uniqueCd.length() > 6)
				throw new RuntimeException("Value [" + uniqueCd + "] for the location unique code is invalid. " + 
					"The system expects a valid unique code no longer than 6 characters.");
		}
		this.uniqueCd = uniqueCd;
	}

	public String getLocCd() {
		return locCd;
	}

	public void setLocCd(String locCd) {
		if (!StringUtils.isEmpty(locCd)
				&& locCd.length() > 6)
			throw new RuntimeException("Value [" + locCd + "] for the location code is invalid. " + 
					"The system expects a valid code no longer than 4 characters.");
		this.locCd = locCd;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		if (!StringUtils.isEmpty(locName)
				&& locName.length() > 6)
			throw new RuntimeException("Value [" + locName + "] for the location name is invalid. " + 
					"The system expects a valid name no longer than 128 characters.");
		this.locName = locName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public boolean isEmpty() {
		return false;
	}

	public void setLocationAliases(List<LocationAlias> locationAliases) {
		this.locationAliases = locationAliases;
	}

	public List<LocationAlias> getLocationAliases() {
		return locationAliases;
	}
}