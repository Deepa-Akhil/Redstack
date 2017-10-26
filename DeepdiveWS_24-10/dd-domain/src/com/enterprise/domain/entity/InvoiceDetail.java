package com.enterprise.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "INVOICE_DETAIL", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "INVOICE_DETAIL", 
	indexes = { 
		@Index(name = "ratedetail_id_ix", columnNames = { "ID" } )
	}
)
public class InvoiceDetail implements IEntity, Serializable {

	private static final long serialVersionUID = -3181167491493876267L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INVOICE_DETAIL_SEQ")
	@SequenceGenerator(name = "INVOICE_DETAIL_SEQ", sequenceName = "GLOBAL.INVOICE_DETAIL_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "CHARGE_CATEGORY")
	private String chargeCategory;
	
	@Column(name = "CHARGE_CODE", nullable = false, length = 10)
	private String chargeCode;
	
	@Column(name = "CHARGE_DESCRIPTION", length = 1000)
	private String chargeDescription;
	
	@Column(name = "CHARGE_LEVEL")
	private String chargeLevel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "LOCAL_CURRENCY_ID", referencedColumnName = "ID", nullable = true)
	})
	private Currency localCurId;
	
	@Column(name = "LOCAL_AMOUNT", nullable = true, precision = 13, scale = 3)
	private BigDecimal localAmount;
	
	@Column(name = "USD_AMOUNT", nullable = true, precision = 13, scale = 3)
	private BigDecimal usdAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "INVOICE_ID", referencedColumnName = "ID", nullable = true)
	})
	private Invoice invoice;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getChargeCategory() {
		return chargeCategory;
	}

	public void setChargeCategory(String chargeCategory) {
		this.chargeCategory = chargeCategory;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public String getChargeDescription() {
		return chargeDescription;
	}

	public void setChargeDescription(String chargeDescription) {
		this.chargeDescription = chargeDescription;
	}

	public String getChargeLevel() {
		return chargeLevel;
	}

	public void setChargeLevel(String chargeLevel) {
		this.chargeLevel = chargeLevel;
	}

	public Currency getLocalCurId() {
		return localCurId;
	}

	public void setLocalCurId(Currency localCurId) {
		this.localCurId = localCurId;
	}

	public BigDecimal getLocalAmount() {
		return localAmount;
	}

	public void setLocalAmount(BigDecimal localAmount) {
		this.localAmount = localAmount;
	}
	
	public void setLocalAmount(String localAmount) {
		try {
			Double localAmountDouble = StringUtils.asDouble(localAmount);
			this.localAmount = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + localAmount + "] as the LocalAmount is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}
	
	public BigDecimal getUsdAmount() {
		return usdAmount;
	}

	public void setUsdAmount(BigDecimal usdAmount) {
		this.usdAmount = usdAmount;
	}
	
	public void setUsdAmount(String usdAmount) {
		try {
			Double usdAmountDouble = StringUtils.asDouble(usdAmount);
			this.usdAmount = (usdAmountDouble == null) 
					? null : BigDecimal.valueOf(usdAmountDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + usdAmount + "] as the USD Amount weight is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public boolean isEmpty() {
		return false;
	}

}
