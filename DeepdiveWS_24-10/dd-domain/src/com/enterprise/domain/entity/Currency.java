package com.enterprise.domain.entity;

import java.io.Serializable;
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

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;

@Entity
@Table(name = "CURRENCY", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "CURRENCY", 
	indexes = { 
		@Index(name = "currency_id_ix", columnNames = { "ID" } )
	}
)
public class Currency implements IEntity, Serializable {

	private static final long serialVersionUID = -4152134122054972007L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CURRENCY_SEQ")
	@SequenceGenerator(name = "CURRENCY_SEQ", sequenceName = "GLOBAL.CURRENCY_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "CURRENCY_CODE", nullable = false)
	private String currencyCode;
	
	@Column(name = "CURRENCY_DESC", nullable = false, length=100)
	private String currencyDesc;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "COUNTRY_ID", referencedColumnName = "ID", nullable = false)
	})
	private Country country;*/
	
	@OneToMany(mappedBy = "localCurId", fetch = FetchType.LAZY)
	private List<RateDetail> rateDetails ;
	
	@Column(name = "CURRENCY_COUNTRY", columnDefinition="TEXT")
	private String countries;
	
	@OneToMany(mappedBy = "localCurId", fetch = FetchType.LAZY)
	private List<InvoiceDetail> invoiceDetails ;
	
	@OneToMany(mappedBy = "currency", fetch = FetchType.LAZY)
	private List<Order> order ;

	
	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public List<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyDesc() {
		return currencyDesc;
	}

	public void setCurrencyDesc(String currencyDesc) {
		this.currencyDesc = currencyDesc;
	}
	
	/*public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}*/
	
	public List<RateDetail> getRateDetails() {
		return rateDetails;
	}

	public void setRateDetails(List<RateDetail> rateDetails) {
		this.rateDetails = rateDetails;
	}
	
	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}
	
	public boolean isEmpty() {
		return false;
	}

}
