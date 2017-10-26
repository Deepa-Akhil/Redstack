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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.StringUtils;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  17 Mar 2014 7:54:02 AM
 * @author rafourie
 */
@Entity
@Table(
		name = "COUNTRY", 
		schema = "GLOBAL", 
		uniqueConstraints = @UniqueConstraint(columnNames = {"COUNTRY_CD"})
)
@org.hibernate.annotations.Table(
	appliesTo = "COUNTRY", 
	indexes = { 
		@Index(name = "country_id_ix", columnNames = { "ID" } )
	}
)
public class Country implements IEntity, Serializable {
	private static final long serialVersionUID = -4297566526664041413L;

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "COUNTRY_SEQ")
	@SequenceGenerator(name = "COUNTRY_SEQ", sequenceName = "GLOBAL.COUNTRY_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	/** COUNTRY_CD varchar2(3) NOT NULL, */
	@Column(name = "COUNTRY_CD", nullable = false, length = 2)
	private String countryCd;
	
	/** COUNTRY_NAME varchar2(128) NOT NULL, */
	@Column(name = "COUNTRY_NAME", nullable = true, length = 128)
	private String countryName;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<Location> locations = new ArrayList<Location>();
	
	@OneToMany(mappedBy = "destCountry", fetch = FetchType.LAZY)
	private List<Rate> destRates = new ArrayList<Rate>();
	
	@OneToMany(mappedBy = "originCountry", fetch = FetchType.LAZY)
	private List<Rate> originRates = new ArrayList<Rate>();
	
	@OneToMany(mappedBy = "destCityCountry", fetch = FetchType.LAZY)
	private List<Rate> destCityRates = new ArrayList<Rate>();
	
	@OneToMany(mappedBy = "originCityCountry", fetch = FetchType.LAZY)
	private List<Rate> originCityRates = new ArrayList<Rate>();
	
	@OneToMany(mappedBy = "billingCountry", fetch = FetchType.LAZY)
	private List<Invoice> billingInvoices = new ArrayList<Invoice>();
	/*@OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
	private List<Currency> currencies = new ArrayList<Currency>();*/
	
	
	public static Country load(String countryCd, Session session) {
		Criteria criteria = session.createCriteria(Country.class)
			.add(Restrictions.eq("countryCd", countryCd));
		Country country = (Country)criteria.uniqueResult();
		return country;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		if (!StringUtils.isEmpty(countryCd)
				&& countryCd.length() > 2)
			throw new RuntimeException("Value [" + countryCd + "] as the country code is invalid. " + 
					"The system expects a valid code no longer than 2 characters.");
		this.countryCd = countryCd;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		if (!StringUtils.isEmpty(countryName)
				&& countryName.length() > 128)
			throw new RuntimeException("Value [" + countryName + "] as the country name is invalid. " + 
					"The system expects a valid name no longer than 128 characters.");
		this.countryName = countryName;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public void setLocations(List<Location> locations) {
		this.locations = locations;
	}
	
	/*public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}*/


	public boolean isEmpty() {
		return false;
	}

	public List<Rate> getDestRates() {
		return destRates;
	}

	public void setDestRates(List<Rate> destRates) {
		this.destRates = destRates;
	}

	public List<Rate> getOriginRates() {
		return originRates;
	}

	public void setOriginRates(List<Rate> originRates) {
		this.originRates = originRates;
	}

	public List<Invoice> getBillingInvoices() {
		return billingInvoices;
	}

	public void setBillingInvoices(List<Invoice> billingInvoices) {
		this.billingInvoices = billingInvoices;
	}

	public void setDestCityRates(List<Rate> destCityRates) {
		this.destCityRates = destCityRates;
	}

	public List<Rate> getDestCityRates() {
		return destCityRates;
	}

	public void setOriginCityRates(List<Rate> originCityRates) {
		this.originCityRates = originCityRates;
	}

	public List<Rate> getOriginCityRates() {
		return originCityRates;
	}
}