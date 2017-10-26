package com.enterprise.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.enums.ModeTypes;
import com.enterprise.common.util.DateUtils;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "CUSTOM", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"JOB_NUMBER", "PART_NUMBER", "LINE_NO", "PACKAGE_ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "CUSTOM", 
	indexes = { 
		@Index(name = "custom_id_ix", columnNames = { "ID" } )
	}
)
public class Custom implements IEntity, Serializable {

	private static final long serialVersionUID = 5777018454025259750L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUSTOM_SEQ")
	@SequenceGenerator(name = "CUSTOM_SEQ", sequenceName = "GLOBAL.CUSTOM_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "IMP_CODE")
	private String impCode;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MODE", length = 1)
	private ModeTypes mode;
	
	@Column(name = "PART_NUMBER")
	private String partNo;
	
	@Column(name = "PART_NAME", columnDefinition="Text")
	private String partName;
	
	@Column(name = "CLASS_OF_PART")
	private String classOfPart;
	
	@Column(name = "INCO")
	private String inco;
	
	@Column(name = "TOTAL_QUANTITY", nullable = true, precision = 10, scale = 0)
	private BigDecimal totalQuantity;
	
	@Column(name = "JOB_NUMBER")
	private String jobNumber;
	
	@Column(name = "SHIPMENT_NUMBER", length=21)
	private String shipmentNumber;
	
	@Column(name = "HB_ORIGIN")
	private String hbOrigin;
	
	@Column(name = "ITEM_ORIG")
	private String itemOrig;
	
	@Column(name = "MASTER_BILL_NUMBER")
	private String masterBillNumber;
	
	@Column(name = "VESSEL", length=100)
	private String vessel;
	
	@Column(name = "VOYAGE_FLIGHT")
	private String voyageFlight;
	
	@Column(name = "TARIFF", nullable = true, precision = 15, scale = 3)
	private BigDecimal tariff;
	
	@Column(name = "TARIFF_DESC")
	private String tariffDesc;
	
	@Column(name = "PART_AUS_VFD", nullable = true, precision = 10, scale = 3)
	private BigDecimal partAusVFD;
	
	@Column(name = "DUTY", nullable = true, precision = 10, scale = 3)
	private BigDecimal duty;
	
	@Column(name = "FRT_AMOUNT", nullable = true, precision = 10, scale = 3)
	private BigDecimal frtAmount;
	
	@Column(name = "CART_AMOUNT", nullable = true, precision = 10, scale = 3)
	private BigDecimal cartAmount;
	
	@Column(name = "OTHER_AMOUNT", nullable = true, precision = 10, scale = 3)
	private BigDecimal otherAmount;
	
	@Column(name = "GST", nullable = true, precision = 10, scale = 3)
	private BigDecimal gst;
	
	@Column(name = "TOTAL_AMOUNT", nullable = true, precision = 10, scale = 3)
	private BigDecimal totalAmount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FIRST_INVOICE_DATE")
	private Date firstInvoiceDate;
	
	@Column(name = "INVOICE_NUMBER")
	private String invoiceNumber;
	
	@Column(name = "LAST_INVOICE_NUMBER")
	private String lastInvoiceNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_INVOICE_DATE")
	private Date lastInvoiceDate;
	
	@Column(name = "USER_DEFINED1", nullable = true)
	private String userdefined1;
	
	@Column(name = "USER_DEFINED2", nullable = true)
	private String userdefined2;
	
	@Column(name = "USER_DEFINED3", nullable = true)
	private String userdefined3;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID", nullable = true)
	})
	private Package pkg;
	
	@Column(name = "LINE_NO", nullable = true)
	private String lineNo;

	public long getId() {
		return id;
	}

	public String getImpCode() {
		return impCode;
	}

	public void setImpCode(String impCode) {
		this.impCode = impCode;
	}


	public ModeTypes getMode() {
		return mode;
	}


	public void setMode(ModeTypes mode) {
		this.mode = mode;
	}
	
	public void setMode(String mode) {
		ModeTypes modeType = null;
		try {
			if(mode.equalsIgnoreCase("SEA") ||
					mode.equalsIgnoreCase("CNT")){
				mode = "O";
			} else if(mode.equalsIgnoreCase("ROAD") || 
					mode.equalsIgnoreCase("ROA")){
				mode = "T";
			}
			modeType = ModeTypes.valueOf(mode);
			this.mode = modeType;
		} catch (Throwable e) {
			String correctedMode = mode;
			if(!StringUtils.isEmpty(mode) && mode.length() > 1){
				correctedMode = mode.substring(0, 1).toUpperCase() + mode.substring(1).toLowerCase();
			}
			modeType = ModeTypes.valueOfByMode(correctedMode);
			if (modeType == null)
				throw new RuntimeException("Value [" + mode + "] as the Custom Mode is invalid. The system expects " + 
						"a 1 character value of either 'A','O' or 'M'.");
			this.mode = modeType;
		}
	}


	public String getPartNo() {
		return partNo;
	}


	public void setPartNo(String partNo) {
		this.partNo = partNo;
	}


	public String getPartName() {
		return partName;
	}


	public void setPartName(String partName) {
		this.partName = partName;
	}


	public String getClassOfPart() {
		return classOfPart;
	}


	public void setClassOfPart(String classOfPart) {
		this.classOfPart = classOfPart;
	}


	public String getInco() {
		return inco;
	}


	public void setInco(String inco) {
		this.inco = inco;
	}


	public BigDecimal getTotalQuantity() {
		return totalQuantity;
	}


	public void setTotalQuantity(BigDecimal totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	
	public void setTotalQuantity(String totalQuantity) {
		try {
			Double totalQuantityDouble = StringUtils.asDouble(totalQuantity);
			this.totalQuantity = (totalQuantityDouble == null) 
					? null : BigDecimal.valueOf(totalQuantityDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + totalQuantity + "] as the Total Quantity is invalid. " + 
					"The system expects a valid integer value. Example: 148");
		}
	}


	public String getJobNumber() {
		return jobNumber;
	}


	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}


	public String getShipmentNumber() {
		return shipmentNumber;
	}


	public void setShipmentNumber(String shipmentNumber) {
		if (!StringUtils.isEmpty(shipmentNumber)
				&& shipmentNumber.length() > 21)
			throw new RuntimeException("Value [" + shipmentNumber + "] as the shipment number is invalid. " + 
					"The system expects a valid value of no more than 20 characters.");
		else if(StringUtils.isEmpty(shipmentNumber)){
			throw new RuntimeException("Value [" + shipmentNumber + "] as the shipment number is empty. " + 
					"The system expects a valid value of no more than 20 characters.");
		}
		this.shipmentNumber = shipmentNumber;
	}


	public String getHbOrigin() {
		return hbOrigin;
	}


	public void setHbOrigin(String hbOrigin) {
		this.hbOrigin = hbOrigin;
	}


	public String getItemOrig() {
		return itemOrig;
	}


	public void setItemOrig(String itemOrig) {
		this.itemOrig = itemOrig;
	}


	public String getMasterBillNumber() {
		return masterBillNumber;
	}


	public void setMasterBillNumber(String masterBillNumber) {
		this.masterBillNumber = masterBillNumber;
	}


	public String getVessel() {
		return vessel;
	}


	public void setVessel(String vessel) {
		this.vessel = vessel;
	}


	public String getVoyageFlight() {
		return voyageFlight;
	}


	public void setVoyageFlight(String voyageFlight) {
		this.voyageFlight = voyageFlight;
	}


	public BigDecimal getTariff() {
		return tariff;
	}


	public void setTariff(BigDecimal tariff) {
		this.tariff = tariff;
	}
	
	public void setTariff(String tariff) {
		try {
			Double tariffDouble = StringUtils.asDouble(tariff);
			this.tariff = (tariffDouble == null) 
					? null : BigDecimal.valueOf(tariffDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + tariff + "] as the Tariff is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}


	public BigDecimal getPartAusVFD() {
		return partAusVFD;
	}


	public void setPartAusVFD(BigDecimal partAusVFD) {
		this.partAusVFD = partAusVFD;
	}
	
	public void setPartAusVFD(String partAusVFD) {
		try {
			Double partAusVFDDouble = StringUtils.asDouble(partAusVFD);
			this.partAusVFD = (partAusVFDDouble == null) 
					? null : BigDecimal.valueOf(partAusVFDDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + partAusVFD + "] as the Part Aus VFD is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}


	public BigDecimal getDuty() {
		return duty;
	}


	public void setDuty(BigDecimal duty) {
		this.duty = duty;
	}
	
	public void setDuty(String duty) {
		try {
			Double dutyDouble = StringUtils.asDouble(duty);
			this.duty = (dutyDouble == null) 
					? null : BigDecimal.valueOf(dutyDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + duty + "] as the Duty is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}


	public BigDecimal getFrtAmount() {
		return frtAmount;
	}


	public void setFrtAmount(BigDecimal frtAmount) {
		this.frtAmount = frtAmount;
	}
	
	public void setFrtAmount(String frtAmount) {
		try {
			Double frtAmountDouble = StringUtils.asDouble(frtAmount);
			this.frtAmount = (frtAmountDouble == null) 
					? null : BigDecimal.valueOf(frtAmountDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + frtAmount + "] as the FrtAmount is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}


	public BigDecimal getCartAmount() {
		return cartAmount;
	}


	public void setCartAmount(BigDecimal cartAmount) {
		this.cartAmount = cartAmount;
	}
	
	public void setCartAmount(String cartAmount) {
		try {
			Double frtAmountDouble = StringUtils.asDouble(cartAmount);
			this.cartAmount = (frtAmountDouble == null) 
					? null : BigDecimal.valueOf(frtAmountDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + cartAmount + "] as the Cart Amount is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}


	public BigDecimal getOtherAmount() {
		return otherAmount;
	}


	public void setOtherAmount(BigDecimal otherAmount) {
		this.otherAmount = otherAmount;
	}
	
	public void setOtherAmount(String otherAmount) {
		try {
			Double otherAmountDouble = StringUtils.asDouble(otherAmount);
			this.otherAmount = (otherAmountDouble == null) 
					? null : BigDecimal.valueOf(otherAmountDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + otherAmount + "] as the Other Amount is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}


	public BigDecimal getGst() {
		return gst;
	}


	public void setGst(BigDecimal gst) {
		this.gst = gst;
	}
	
	public void setGst(String gst) {
		try {
			Double gstDouble = StringUtils.asDouble(gst);
			this.gst = (gstDouble == null) 
					? null : BigDecimal.valueOf(gstDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + gst + "] as the GST is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}


	public BigDecimal getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public void setTotalAmount(String totalAmount) {
		try {
			Double totalAmountDouble = StringUtils.asDouble(totalAmount);
			this.totalAmount = (totalAmountDouble == null) 
					? null : BigDecimal.valueOf(totalAmountDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + totalAmount + "] as the Total Amount is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}


	public Date getFirstInvoiceDate() {
		return firstInvoiceDate;
	}


	public void setFirstInvoiceDate(Date firstInvoiceDate) {
		this.firstInvoiceDate = firstInvoiceDate;
	}
	
	public void setFirstInvoiceDate(String firstInvoiceDate) {
		try {
			this.firstInvoiceDate = DateUtils.toValidatedDate(StringUtils.upper(firstInvoiceDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + firstInvoiceDate + "] as the First Invoice date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}


	public String getInvoiceNumber() {
		return invoiceNumber;
	}


	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}


	public Date getLastInvoiceDate() {
		return lastInvoiceDate;
	}


	public void setLastInvoiceDate(Date lastInvoiceDate) {
		this.lastInvoiceDate = lastInvoiceDate;
	}
	
	public void setLastInvoiceDate(String lastInvoiceDate) {
		try {
			this.lastInvoiceDate = DateUtils.toValidatedDate(StringUtils.upper(lastInvoiceDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + lastInvoiceDate + "] as the First Invoice date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}


	public String getUserdefined1() {
		return userdefined1;
	}


	public void setUserdefined1(String userdefined1) {
		this.userdefined1 = userdefined1;
	}


	public String getUserdefined2() {
		return userdefined2;
	}


	public void setUserdefined2(String userdefined2) {
		this.userdefined2 = userdefined2;
	}


	public String getUserdefined3() {
		return userdefined3;
	}


	public void setUserdefined3(String userdefined3) {
		this.userdefined3 = userdefined3;
	}


	public Package getPkg() {
		return pkg;
	}


	public void setPkg(Package pkg) {
		this.pkg = pkg;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setLastInvoiceNumber(String lastInvoiceNumber) {
		this.lastInvoiceNumber = lastInvoiceNumber;
	}

	public String getLastInvoiceNumber() {
		return lastInvoiceNumber;
	}

	public void setTariffDesc(String tariffDesc) {
		this.tariffDesc = tariffDesc;
	}

	public String getTariffDesc() {
		return tariffDesc;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public String getLineNo() {
		return lineNo;
	}

	public boolean isEmpty() {
		if(pkg!=null && mode!=null && partNo!=null && shipmentNumber!=null) {
			return false;
		}
		return true;
	}
	
	public static Custom load(String jobNo, String partNo, String lineNo, Session session, long packageId) {
		Criteria criteria = session.createCriteria(Custom.class);
		if(null != jobNo)
			criteria.add(Restrictions.eq("jobNumber", jobNo));
		if(null != lineNo)
			criteria.add(Restrictions.eq("lineNo", lineNo));
		if(null != partNo)
			criteria.add(Restrictions.eq("partNo", partNo));
		if(packageId != 0L)
			criteria.add(Restrictions.eq("pkg.id", packageId));
		@SuppressWarnings("unchecked")
		List<Custom> customList = (List<Custom>)criteria.list();
		Custom custom = null;
		if(customList!=null && !customList.isEmpty()){
			custom = customList.get(0);
			if(customList.size() > 1){
				List<Long> invoiceIds = new ArrayList<Long>();
				for (int i = 1; i < customList.size(); i++) {
					invoiceIds.add(customList.get(i).getId());
				}
				String hql = "delete from Custom WHERE id in(:id)";
				Query query = session.createQuery(hql);
				query.setParameterList("id", invoiceIds);
				query.executeUpdate();
			}
		}
		return custom;
	}
}
