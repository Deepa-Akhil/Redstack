package com.enterprise.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.DateUtils;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "INVOICE", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"SHIPMENT_NUMBER", "INVOICE_NUMBER", "PACKAGE_ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "INVOICE", 
	indexes = { 
		@Index(name = "invoice_id_ix", columnNames = { "ID" } )
	}
)
public class Invoice implements IEntity, Serializable {

	private static final long serialVersionUID = 1648489837564128267L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "INVOICE_SEQ")
	@SequenceGenerator(name = "INVOICE_SEQ", sequenceName = "GLOBAL.INVOICE_SEQ")
	@Column(name = "ID", nullable = false)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	private long id;
	
	@Column(name = "SHIPMENT_NUMBER", nullable = false, length = 30)
	private String shipmentNumber;
	
	@Column(name = "INVOICE_NUMBER", nullable = false, length = 16)
	private String invoiceNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INVOICE_DATE", nullable = true)
	private Date invoiceDate = new Date();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID", nullable = true)
	})
	private Package pkg;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SHIPMENT_ID", referencedColumnName = "ID", nullable = true)
	})
	private Shipment shipment;
	
	@OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
	private List<InvoiceDetail> invoiceDetails = new ArrayList<InvoiceDetail>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "BILL_COUNTRY_ID", referencedColumnName = "ID", nullable = true)
	})
	private Country billingCountry;
	
	@Column(name = "BILLING_MODE")
	private String billingMode;
	
	@Column(name = "BILL_TO_PART")
	private String billingModePart;
	
	@Column(name = "USER_DEFINED1", columnDefinition="Text")
	private String userDefined1;
	
	@Column(name = "USER_DEFINED2", columnDefinition="Text")
	private String userDefined2;
	
	@Column(name = "USER_DEFINED3", columnDefinition="Text")
	private String userDefined3;
	
	@Transient
	private boolean detailNotNeeded = false;
	
	@Column(name = "FREIGHT_CHARGES", nullable = true, precision = 13, scale = 3)
	private BigDecimal freightCharges;
	
	@Column(name = "FUEL_SURCHAGE", nullable = true, precision = 13, scale = 3)
	private BigDecimal fuelSurchage;
	
	@Column(name = "SECURITY_SURCHARGE", nullable = true, precision = 13, scale = 3)
	private BigDecimal securitySurcharge;
	
	@Column(name = "WAR_RISK_SURCHARGE", nullable = true, precision = 13, scale = 3)
	private BigDecimal warRiskSurcharge;
	
	@Column(name = "PICKUP_CHARGES", nullable = true, precision = 13, scale = 3)
	private BigDecimal pickupCharges;
	
	@Column(name = "DOCUMENTATION", nullable = true, precision = 13, scale = 3)
	private BigDecimal documentation;
	
	@Column(name = "DG_FEES", nullable = true, precision = 13, scale = 3)
	private BigDecimal dgFees;
	
	@Column(name = "TERMINAL_HANDLING", nullable = true, precision = 13, scale = 3)
	private BigDecimal terminalHandling;
	
	@Column(name = "CNTR_CHARGES", nullable = true, precision = 13, scale = 3)
	private BigDecimal cntrCharges;
	
	@Column(name = "BROKERAGE_CLEARANCE", nullable = true, precision = 13, scale = 3)
	private BigDecimal brokerageClearance;
	
	@Column(name = "DUTIES_TAXES", nullable = true, precision = 13, scale = 3)
	private BigDecimal dutiesTaxes;
	
	@Column(name = "CUSTOMS_OGA_CHARGES", nullable = true, precision = 13, scale = 3)
	private BigDecimal customsOGACharges;
	
	@Column(name = "INLAND_CHARGES", nullable = true, precision = 13, scale = 3)
	private BigDecimal inlandCharges;
	
	@Column(name = "DELIVERY_CHARGES", nullable = true, precision = 13, scale = 3)
	private BigDecimal deliveryCharges;
	
	@Column(name = "OTHER_ACCESSORIALS", nullable = true, precision = 13, scale = 3)
	private BigDecimal otherAccessorials;
	
	@Column(name = "STORAGE_WAREHOUSING", nullable = true, precision = 13, scale = 3)
	private BigDecimal storageWarehousing;
	
	@Column(name = "TOTAL_CHARGES_IN_DUTY", nullable = true, precision = 13, scale = 3)
	private BigDecimal totalChargesInDuty;
	
	@Column(name = "TOTAL_CHARGES_EX_DUTY", nullable = true, precision = 13, scale = 3)
	private BigDecimal totalChargesExDuty;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON")
	private Date updatedOn = new Date();
	
	/** ORIG_PORT_ID decimal(19), */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORIG_PORT_ID", referencedColumnName = "ID", nullable = true)
	})
	private Location origPort;
	
	/** DEST_PORT_ID decimal(19), */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DEST_PORT_ID", referencedColumnName = "ID", nullable = true)
	})
	private Location destPort;
	
	public List<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}
	
	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public void setInvoiceDate(String invoiceDate) {		
		try {
			this.invoiceDate = DateUtils.toValidatedDate(StringUtils.upper(invoiceDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + invoiceDate + "] as the invoice date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Package getPkg() {
		return pkg;
	}

	public void setPkg(Package pkg) {
		this.pkg = pkg;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Country getBillingCountry() {
		return billingCountry;
	}

	public void setBillingCountry(Country billingCountry) {
		this.billingCountry = billingCountry;
	}

	public String getBillingMode() {
		return billingMode;
	}

	public void setBillingMode(String billingMode) {
		this.billingMode = billingMode;
	}

	public String getBillingModePart() {
		return billingModePart;
	}

	public void setBillingModePart(String billingModePart) {
		this.billingModePart = billingModePart;
	}

	public String getUserDefined1() {
		return userDefined1;
	}

	public void setUserDefined1(String userDefined1) {
		this.userDefined1 = userDefined1;
	}

	public String getUserDefined2() {
		return userDefined2;
	}

	public void setUserDefined2(String userDefined2) {
		this.userDefined2 = userDefined2;
	}

	public String getUserDefined3() {
		return userDefined3;
	}

	public void setUserDefined3(String userDefined3) {
		this.userDefined3 = userDefined3;
	}

	public boolean isEmpty() {
		return false;
	}
	
	public BigDecimal getFreightCharges() {
		return freightCharges;
	}

	public void setFreightCharges(BigDecimal freightCharges) {
		this.freightCharges = freightCharges;
	}
	
	public void setFreightCharges(String freightCharges) {
		try {
			Double freightChargesDouble = StringUtils.asDouble(freightCharges);
			this.freightCharges = (freightChargesDouble == null) 
					? null : BigDecimal.valueOf(freightChargesDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + freightCharges + "] as the Freight charges is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getFuelSurchage() {
		return fuelSurchage;
	}

	public void setFuelSurchage(BigDecimal fuelSurchage) {
		this.fuelSurchage = fuelSurchage;
	}
	
	public void setFuelSurchage(String fuelSurchage) {
		try {
			Double fuelSurchageDouble = StringUtils.asDouble(fuelSurchage);
			this.fuelSurchage = (fuelSurchageDouble == null) 
					? null : BigDecimal.valueOf(fuelSurchageDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + fuelSurchage + "] as the Fuel surchage is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getSecuritySurcharge() {
		return securitySurcharge;
	}

	public void setSecuritySurcharge(BigDecimal securitySurcharge) {
		this.securitySurcharge = securitySurcharge;
	}
	
	public void setSecuritySurcharge(String securitySurcharge) {
		try {
			Double securitySurchargeDouble = StringUtils.asDouble(securitySurcharge);
			this.securitySurcharge = (securitySurchargeDouble == null) 
					? null : BigDecimal.valueOf(securitySurchargeDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + securitySurcharge + "] as the Security surcharge is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getWarRiskSurcharge() {
		return warRiskSurcharge;
	}

	public void setWarRiskSurcharge(BigDecimal warRiskSurcharge) {
		this.warRiskSurcharge = warRiskSurcharge;
	}
	
	public void setWarRiskSurcharge(String warRiskSurcharge) {
		try {
			Double warRiskSurchargeDouble = StringUtils.asDouble(warRiskSurcharge);
			this.warRiskSurcharge = (warRiskSurchargeDouble == null) 
					? null : BigDecimal.valueOf(warRiskSurchargeDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + warRiskSurcharge + "] as the War risk surcharge is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getPickupCharges() {
		return pickupCharges;
	}

	public void setPickupCharges(BigDecimal pickupCharges) {
		this.pickupCharges = pickupCharges;
	}
	
	public void setPickupCharges(String pickupCharges) {
		try {
			Double pickupChargesDouble = StringUtils.asDouble(pickupCharges);
			this.pickupCharges = (pickupChargesDouble == null) 
					? null : BigDecimal.valueOf(pickupChargesDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + pickupCharges + "] as the Pickup charges is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getDocumentation() {
		return documentation;
	}

	public void setDocumentation(BigDecimal documentation) {
		this.documentation = documentation;
	}
	
	public void setDocumentation(String documentation) {
		try {
			Double documentationDouble = StringUtils.asDouble(documentation);
			this.documentation = (documentationDouble == null) 
					? null : BigDecimal.valueOf(documentationDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + documentation + "] as the Documentation is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getDgFees() {
		return dgFees;
	}

	public void setDgFees(BigDecimal dgFees) {
		this.dgFees = dgFees;
	}
	
	public void setDgFees(String dgFees) {
		try {
			Double dgFeesDouble = StringUtils.asDouble(dgFees);
			this.dgFees = (dgFeesDouble == null) 
					? null : BigDecimal.valueOf(dgFeesDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + dgFees + "] as the DG fees is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getTerminalHandling() {
		return terminalHandling;
	}

	public void setTerminalHandling(BigDecimal terminalHandling) {
		this.terminalHandling = terminalHandling;
	}
	
	public void setTerminalHandling(String terminalHandling) {
		try {
			Double terminalHandlingDouble = StringUtils.asDouble(terminalHandling);
			this.terminalHandling = (terminalHandlingDouble == null) 
					? null : BigDecimal.valueOf(terminalHandlingDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + terminalHandling + "] as the Terminal handling is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getCntrCharges() {
		return cntrCharges;
	}

	public void setCntrCharges(BigDecimal cntrCharges) {
		this.cntrCharges = cntrCharges;
	}
	
	public void setCntrCharges(String cntrCharges) {
		try {
			Double cntrChargesDouble = StringUtils.asDouble(cntrCharges);
			this.cntrCharges = (cntrChargesDouble == null) 
					? null : BigDecimal.valueOf(cntrChargesDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + cntrCharges + "] as the Cntr charges is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getBrokerageClearance() {
		return brokerageClearance;
	}

	public void setBrokerageClearance(BigDecimal brokerageClearance) {
		this.brokerageClearance = brokerageClearance;
	}
	
	public void setBrokerageClearance(String brokerageClearance) {
		try {
			Double brokerageClearanceDouble = StringUtils.asDouble(brokerageClearance);
			this.brokerageClearance = (brokerageClearanceDouble == null) 
					? null : BigDecimal.valueOf(brokerageClearanceDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + brokerageClearance + "] as the Brokerage/Clearance is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getDutiesTaxes() {
		return dutiesTaxes;
	}

	public void setDutiesTaxes(BigDecimal dutiesTaxes) {
		this.dutiesTaxes = dutiesTaxes;
	}
	
	public void setDutiesTaxes(String dutiesTaxes) {
		try {
			Double dutiesTaxesDouble = StringUtils.asDouble(dutiesTaxes);
			this.dutiesTaxes = (dutiesTaxesDouble == null) 
					? null : BigDecimal.valueOf(dutiesTaxesDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + dutiesTaxes + "] as the Duties & Taxes is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getCustomsOGACharges() {
		return customsOGACharges;
	}

	public void setCustomsOGACharges(BigDecimal customsOGACharges) {
		this.customsOGACharges = customsOGACharges;
	}
	
	public void setCustomsOGACharges(String customsOGACharges) {
		try {
			Double customsOGAChargesDouble = StringUtils.asDouble(customsOGACharges);
			this.customsOGACharges = (customsOGAChargesDouble == null) 
					? null : BigDecimal.valueOf(customsOGAChargesDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + customsOGACharges + "] as the Customs & OGA charges is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getInlandCharges() {
		return inlandCharges;
	}

	public void setInlandCharges(BigDecimal inlandCharges) {
		this.inlandCharges = inlandCharges;
	}
	
	public void setInlandCharges(String inlandCharges) {
		try {
			Double inlandChargesDouble = StringUtils.asDouble(inlandCharges);
			this.inlandCharges = (inlandChargesDouble == null) 
					? null : BigDecimal.valueOf(inlandChargesDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + inlandCharges + "] as the Inland charges is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(BigDecimal deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}
	
	public void setDeliveryCharges(String deliveryCharges) {
		try {
			Double deliveryChargesDouble = StringUtils.asDouble(deliveryCharges);
			this.deliveryCharges = (deliveryChargesDouble == null) 
					? null : BigDecimal.valueOf(deliveryChargesDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + deliveryCharges + "] as the Delivery charges is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getOtherAccessorials() {
		return otherAccessorials;
	}

	public void setOtherAccessorials(BigDecimal otherAccessorials) {
		this.otherAccessorials = otherAccessorials;
	}
	
	public void setOtherAccessorials(String otherAccessorials) {
		try {
			Double otherAccessorialsDouble = StringUtils.asDouble(otherAccessorials);
			this.otherAccessorials = (otherAccessorialsDouble == null) 
					? null : BigDecimal.valueOf(otherAccessorialsDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + otherAccessorials + "] as the Other accessorials is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getStorageWarehousing() {
		return storageWarehousing;
	}

	public void setStorageWarehousing(BigDecimal storageWarehousing) {
		this.storageWarehousing = storageWarehousing;
	}
	
	public void setStorageWarehousing(String storageWarehousing) {
		try {
			Double storageWarehousingDouble = StringUtils.asDouble(storageWarehousing);
			this.storageWarehousing = (storageWarehousingDouble == null) 
					? null : BigDecimal.valueOf(storageWarehousingDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + storageWarehousing + "] as the Storage & Warehousing is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getTotalChargesInDuty() {
		return totalChargesInDuty;
	}

	public void setTotalChargesInDuty(BigDecimal totalChargesInDuty) {
		this.totalChargesInDuty = totalChargesInDuty;
	}
	
	public void setTotalChargesInDuty(String totalChargesInDuty) {
		try {
			Double totalChargesInDutyDouble = StringUtils.asDouble(totalChargesInDuty);
			this.totalChargesInDuty = (totalChargesInDutyDouble == null) 
					? null : BigDecimal.valueOf(totalChargesInDutyDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + totalChargesInDuty + "] as the Total Charges (Incl Duties) is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getTotalChargesExDuty() {
		return totalChargesExDuty;
	}

	public void setTotalChargesExDuty(BigDecimal totalChargesExDuty) {
		this.totalChargesExDuty = totalChargesExDuty;
	}
	
	public void setTotalChargesExDuty(String totalChargesExDuty) {
		try {
			Double totalChargesExDutyDouble = StringUtils.asDouble(totalChargesExDuty);
			this.totalChargesExDuty = (totalChargesExDutyDouble == null) 
					? null : BigDecimal.valueOf(totalChargesExDutyDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
			detailNotNeeded = true;
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + totalChargesExDuty + "] as the Total Charges (Excl Duties) is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}
	
	
	public void setDetailNotNeeded(boolean detailNotNeeded) {
		this.detailNotNeeded = detailNotNeeded;
	}

	public boolean isDetailNotNeeded() {
		return detailNotNeeded;
	}

	public static Invoice load(String shipmentNo, String invoiceNo, Session session, long packageId) {
		Criteria criteria = session.createCriteria(Invoice.class)
			.add(Restrictions.eq("invoiceNumber", invoiceNo));
		if(null != shipmentNo)
			criteria.add(Restrictions.eq("shipmentNumber", shipmentNo));
		if(packageId != 0L)
			criteria.add(Restrictions.eq("pkg.id", packageId));
		Invoice invoice = (Invoice)criteria.uniqueResult();
		return invoice;
	}

	public static void setInvoiceDetailClearOld(Set<Long> ids, Set<Long> invoiceIds, Session session) {
		if(ids!=null && invoiceIds!=null && !ids.isEmpty() && !invoiceIds.isEmpty()) {
			String hql = "delete from InvoiceDetail "
					+ "WHERE id not in(:id) and invoice.id in(:invoiceIds)";
			Query query = session.createQuery(hql);
			query.setParameterList("id", ids);
			query.setParameterList("invoiceIds", invoiceIds);
			query.executeUpdate();
		}
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	public Location getOrigPort() {
		return origPort;
	}

	public void setOrigPort(Location origPort) {
		this.origPort = origPort;
	}

	public void setOrigPort(IPort origPort) {
		this.origPort = (Location)origPort;
	}

	public Location getDestPort() {
		return destPort;
	}

	public void setDestPort(Location destPort) {
		this.destPort = destPort;
	}
	
	public void setDestPort(IPort destPort) {
		this.destPort = (Location)destPort;
	}
}
