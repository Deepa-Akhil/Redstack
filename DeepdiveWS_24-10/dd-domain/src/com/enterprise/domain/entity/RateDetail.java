package com.enterprise.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.StringUtils;


@Entity
@Table(name = "RATE_DETAIL", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "RATE_DETAIL", 
	indexes = { 
		@Index(name = "ratedetail_id_ix", columnNames = { "ID" } )
	}
)
public class RateDetail implements IEntity, Serializable {

	private static final long serialVersionUID = 1643265395999321668L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RATE_DETAIL_SEQ")
	@SequenceGenerator(name = "RATE_DETAIL_SEQ", sequenceName = "GLOBAL.RATE_DETAIL_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CARRIER_ID", referencedColumnName = "ID", nullable = true)
	})
	private Carrier carrier;
	
	@Column(name="CONTAINER_SIZE", length = 5)
	private String containerSize;
	
	@Column(name = "CHARGE_CATEGORY", nullable = true)
	private String chargeCategory;
	
	@Column(name = "CHARGE_ITEM", nullable = true, length = 1000)
	private String chargeItem;
	
	@Column(name = "CHARGE_LEVEL", nullable = true)
	private String chargeLevel;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "BASE_RATE_CURRENCY", referencedColumnName = "ID", nullable = true)
	})
	private Currency localCurId;
	
	@Column(name = "BASE_RATE_LOCAL_AMOUNT", nullable = true, precision = 13, scale = 3)
	private BigDecimal localAmount;
	
	@Column(name = "BASE_RATE_USD_AMOUNT", nullable = true, precision = 13, scale = 3)
	private BigDecimal usdAmount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RATE_ID", referencedColumnName = "ID", nullable = true)
	})
	private Rate rate;
	
	@Column(name = "IS_ACTIVE")
	private boolean isActive = true;
	
	@Column(name = "IS_MANDATORY")
	private boolean isMandatory = true;
	
	@Column(name = "NOTE", nullable = true)
	private String notes;
	
	@Column(name = "TRANSIT_TIME", nullable = true, precision = 13, scale = 3)
	private BigDecimal transitTime;
	
	@Column(name = "ALLOCATION_AMOUNT", nullable = true, precision = 13, scale = 3)
	private BigDecimal allocationAmount;
	
	@Column(name = "SERVICE_TYPE", nullable = true)
	private String serviceType;
	
	@Column(name = "USER_DEFINED_1", nullable = true)
	private String userdefined1;
	
	@Column(name = "USER_DEFINED_2", nullable = true)
	private String userdefined2;
	
	@Column(name = "USER_DEFINED_3", nullable = true)
	private String userdefined3;
	
	/** ORIG_CITY_ID decimal(19) NOT NULL, */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( {
        @JoinColumn(name="ORIG_CITY_ID", referencedColumnName = "ID", nullable = true)
	} )
	@Cascade({CascadeType.SAVE_UPDATE})
	private City origCity;
	
	/** DEST_CITY_ID decimal(19) NOT NULL, */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( {
        @JoinColumn(name="DEST_CITY_ID", referencedColumnName = "ID", nullable = true)
	} )
	@Cascade({CascadeType.SAVE_UPDATE})
	private City destCity;
	
	@Column(name = "SERVICE_STRING", nullable = true)
	private String serviceString;
	
	@Column(name = "SERVICE_LEVEL", nullable = true)
	private String serviceLevel;
	
	@Column(name = "SAILING_FREQUENCY", nullable = true)
	private String sailingFreeq;
	
	@Column(name = "ORIG_FREE_DAYS", nullable = true, precision = 13, scale = 3)
	private BigDecimal origFreeDays;
	
	@Column(name = "ORIG_PORT_FREE_DAYS", nullable = true, precision = 13, scale = 3)
	private BigDecimal origPortFreeDays;
	
	@Column(name = "DEST_FREE_DAYS", nullable = true, precision = 13, scale = 3)
	private BigDecimal destFreeDays;
	
	@Column(name = "DEST_PORT_FREE_DAYS", nullable = true, precision = 13, scale = 3)
	private BigDecimal destPortFreeDays;
	
	@Column(name = "ORIG_FREE_DAYS_HAZ", nullable = true, precision = 13, scale = 3)
	private BigDecimal origFreeDaysHaz;
	
	@Column(name = "ORIG_PORT_FREE_DAYS_HAZ", nullable = true, precision = 13, scale = 3)
	private BigDecimal origPortFreeDaysHaz;
	
	@Column(name = "DEST_FREE_DAYS_HAZ", nullable = true, precision = 13, scale = 3)
	private BigDecimal destFreeDaysHaz;
	
	@Column(name = "DEST_PORT_FREE_DAYS_HAZ", nullable = true, precision = 13, scale = 3)
	private BigDecimal destPortFreeDaysHaz;
	
	@Column(name = "CY_CUTTOFF_DAY", nullable = true, precision = 13, scale = 3)
	private BigDecimal cyCutOffDays;
	
	@Column(name = "SAILING_DAY", nullable = true, precision = 13, scale = 3)
	private BigDecimal sailingDay;
	
	@Column(name = "ARRIVAL_DAY", nullable = true, precision = 13, scale = 3)
	private BigDecimal arrivalday;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORIG_PENALTY_CURRENCY", referencedColumnName = "ID", nullable = true)
	})
	private Currency origPenCur;
	
	@Column(name = "ORIG_PENALTY_CHARGE", nullable = true, precision = 13, scale = 3)
	private BigDecimal origPenCharge;
	
	@Column(name = "ORIG_FREE_DAYS_TYPE", nullable = true)
	private String origFreeDayType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORIG_PORT_PENALTY_CURRENCY", referencedColumnName = "ID", nullable = true)
	})
	private Currency origPortPenCur;
	
	@Column(name = "ORIG_PORT_PENALTY_CHARGE", nullable = true, precision = 13, scale = 3)
	private BigDecimal origPortPenCharge;
	
	@Column(name = "ORIG_PORT_FREE_DAYS_TYPE", nullable = true)
	private String origPortFreeDayType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DEST_PENALTY_CURRENCY", referencedColumnName = "ID", nullable = true)
	})
	private Currency destPenCur;
	
	@Column(name = "DEST_PENALTY_CHARGE", nullable = true, precision = 13, scale = 3)
	private BigDecimal destPenCharge;
	
	@Column(name = "DEST_FREE_DAYS_TYPE", nullable = true)
	private String destFreeDayType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DEST_PORT_PENALTY_CURRENCY", referencedColumnName = "ID", nullable = true)
	})
	private Currency destPortPenCur;
	
	@Column(name = "DEST_PORT_PENALTY_CHARGE", nullable = true, precision = 13, scale = 3)
	private BigDecimal destPortPenCharge;
	
	@Column(name = "DEST_PORT_FREE_DAYS_TYPE", nullable = true)
	private String destPortFreeDayType;
	
	@OneToMany(mappedBy = "rateDetail", fetch = FetchType.LAZY)
	private List<RateDetailCharges> rateDetailCharges = new ArrayList<RateDetailCharges>();
	
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
		if(StringUtils.isEmpty(chargeCategory)){
			throw new RuntimeException("Value [" + chargeCategory + "] as the Charge Category is invalid. " + 
					"The system expects a not null value.");
		}
		this.chargeCategory = chargeCategory;
	}

	public String getChargeItem() {
		return chargeItem;
	}

	public void setChargeItem(String chargeItem) {
		this.chargeItem = chargeItem;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}
	
	public void setCarrier(ICarrier carrier) {
		this.carrier = (Carrier)carrier;
	}

	public String getChargeLevel() {
		return chargeLevel;
	}

	public void setChargeLevel(String chargeLevel) {
		if(StringUtils.isEmpty(chargeLevel)){
			throw new RuntimeException("Value [" + chargeLevel + "] as the Charge Level is invalid. " + 
					"The system expects a not null value.");
		}
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

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}
	
	public String getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}

	public boolean isEmpty() {
		return false;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	
	public void setMandatory(String isMandatory) {
		try {
			this.isMandatory = Boolean.parseBoolean(isMandatory.toLowerCase());
		} catch (Exception e) {
			throw new RuntimeException("Value [" + isMandatory + "] as the mandatory is invalid. " + 
					"The system expects a valid boolean value. Example: TRUE.");
		}
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public BigDecimal getTransitTime() {
		return transitTime;
	}

	public void setTransitTime(BigDecimal transitTime) {
		this.transitTime = transitTime;
	}
	
	public void setTransitTime(String transitTime) {
		try {
			Double transitTimeDouble = StringUtils.asDouble(transitTime);
			this.transitTime = (transitTimeDouble == null) 
					? null : BigDecimal.valueOf(transitTimeDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + usdAmount + "] as the USD Amount weight is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}
	
	public BigDecimal getAllocationAmount() {
		return allocationAmount;
	}

	public void setAllocationAmount(BigDecimal allocationAmount) {
		this.allocationAmount = allocationAmount;
	}
	
	public void setAllocationAmount(String allocationAmount) {
		try {
			Double allocationAmountDouble = StringUtils.asDouble(allocationAmount);
			this.allocationAmount = (allocationAmountDouble == null) 
					? null : BigDecimal.valueOf(allocationAmountDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + allocationAmount + "] as the Allocation Amount is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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

	public City getOrigCity() {
		return origCity;
	}

	public void setOrigCity(City origCity) {
		this.origCity = origCity;
	}

	public City getDestCity() {
		return destCity;
	}

	public void setDestCity(City destCity) {
		this.destCity = destCity;
	}

	public String getServiceString() {
		return serviceString;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public void setServiceString(String serviceString) {
		this.serviceString = serviceString;
	}

	public String getSailingFreeq() {
		return sailingFreeq;
	}

	public void setSailingFreeq(String sailingFreeq) {
		this.sailingFreeq = sailingFreeq;
	}

	public BigDecimal getOrigFreeDays() {
		return origFreeDays;
	}

	public void setOrigFreeDays(BigDecimal origFreeDays) {
		this.origFreeDays = origFreeDays;
	}
	
	public void setOrigFreeDays(String origFreeDays) {
		try {
			Double origFreeDaysDouble = StringUtils.asDouble(origFreeDays);
			this.origFreeDays = (origFreeDaysDouble == null) 
					? null : BigDecimal.valueOf(origFreeDaysDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + origFreeDays + "] as the Orig Free Days is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getOrigPortFreeDays() {
		return origPortFreeDays;
	}

	public void setOrigPortFreeDays(BigDecimal origPortFreeDays) {
		this.origPortFreeDays = origPortFreeDays;
	}
	
	public void setOrigPortFreeDays(String origPortFreeDays) {
		try {
			Double origPortFreeDaysDouble = StringUtils.asDouble(origPortFreeDays);
			this.origPortFreeDays = (origPortFreeDaysDouble == null) 
					? null : BigDecimal.valueOf(origPortFreeDaysDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + origPortFreeDays + "] as the Orig Free Port Days is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getDestFreeDays() {
		return destFreeDays;
	}

	public void setDestFreeDays(BigDecimal destFreeDays) {
		this.destFreeDays = destFreeDays;
	}
	
	public void setDestFreeDays(String destFreeDays) {
		try {
			Double destFreeDaysDouble = StringUtils.asDouble(destFreeDays);
			this.destFreeDays = (destFreeDaysDouble == null) 
					? null : BigDecimal.valueOf(destFreeDaysDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + destFreeDays + "] as the Destination Free Days is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getDestPortFreeDays() {
		return destPortFreeDays;
	}

	public void setDestPortFreeDays(BigDecimal destPortFreeDays) {
		this.destPortFreeDays = destPortFreeDays;
	}
	
	public void setDestPortFreeDays(String destPortFreeDays) {
		try {
			Double destPortFreeDaysDouble = StringUtils.asDouble(destPortFreeDays);
			this.destPortFreeDays = (destPortFreeDaysDouble == null) 
					? null : BigDecimal.valueOf(destPortFreeDaysDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + destPortFreeDays + "] as the Destination Port Free Days is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getCyCutOffDays() {
		return cyCutOffDays;
	}

	public void setCyCutOffDays(BigDecimal cyCutOffDays) {
		this.cyCutOffDays = cyCutOffDays;
	}
	
	public void setCyCutOffDays(String cyCutOffDays) {
		try {
			Double cyCutOffDaysDouble = StringUtils.asDouble(cyCutOffDays);
			this.origPortFreeDays = (cyCutOffDaysDouble == null) 
					? null : BigDecimal.valueOf(cyCutOffDaysDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + cyCutOffDays + "] as the CY CutOff Days is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getSailingDay() {
		return sailingDay;
	}

	public void setSailingDay(BigDecimal sailingDay) {
		this.sailingDay = sailingDay;
	}
	
	public void setSailingDay(String sailingDay) {
		try {
			Double sailingDayDouble = StringUtils.asDouble(sailingDay);
			this.origPortFreeDays = (sailingDayDouble == null) 
					? null : BigDecimal.valueOf(sailingDayDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + sailingDay + "] as the Sailing Day is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getArrivalday() {
		return arrivalday;
	}

	public void setArrivalday(BigDecimal arrivalday) {
		this.arrivalday = arrivalday;
	}
	
	public void setArrivalday(String arrivalday) {
		try {
			Double arrivaldayDouble = StringUtils.asDouble(arrivalday);
			this.origPortFreeDays = (arrivaldayDouble == null) 
					? null : BigDecimal.valueOf(arrivaldayDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + arrivalday + "] as the Arrival day is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getOrigPenCur() {
		return origPenCur;
	}

	public void setOrigPenCur(Currency origPenCur) {
		this.origPenCur = origPenCur;
	}

	public BigDecimal getOrigPenCharge() {
		return origPenCharge;
	}

	public void setOrigPenCharge(BigDecimal origPenCharge) {
		this.origPenCharge = origPenCharge;
	}
	
	public void setOrigPenCharge(String origPenCharge) {
		try {
			Double origPenChargeDouble = StringUtils.asDouble(origPenCharge);
			this.origPenCharge = (origPenChargeDouble == null) 
					? null : BigDecimal.valueOf(origPenChargeDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + origPenCharge + "] as the Orig Penalty Charge is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public String getOrigFreeDayType() {
		return origFreeDayType;
	}

	public void setOrigFreeDayType(String origFreeDayType) {
		this.origFreeDayType = origFreeDayType;
	}

	public Currency getOrigPortPenCur() {
		return origPortPenCur;
	}
	
	public void setOrigPortPenCur(Currency origPortPenCur) {
		this.origPortPenCur = origPortPenCur;
	}

	public BigDecimal getOrigPortPenCharge() {
		return origPortPenCharge;
	}

	public void setOrigPortPenCharge(BigDecimal origPortPenCharge) {
		this.origPortPenCharge = origPortPenCharge;
	}
	
	public void setOrigPortPenCharge(String origPortPenCharge) {
		try {
			Double origPortPenChargeDouble = StringUtils.asDouble(origPortPenCharge);
			this.origPortPenCharge = (origPortPenChargeDouble == null) 
					? null : BigDecimal.valueOf(origPortPenChargeDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + origPortPenCharge + "] as the Orig Port Penalty Charge is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public String getOrigPortFreeDayType() {
		return origPortFreeDayType;
	}

	public void setOrigPortFreeDayType(String origPortFreeDayType) {
		this.origPortFreeDayType = origPortFreeDayType;
	}

	public Currency getDestPenCur() {
		return destPenCur;
	}

	public void setDestPenCur(Currency destPenCur) {
		this.destPenCur = destPenCur;
	}

	public BigDecimal getDestPenCharge() {
		return destPenCharge;
	}

	public void setDestPenCharge(BigDecimal destPenCharge) {
		this.destPenCharge = destPenCharge;
	}
	
	public void setDestPenCharge(String destPenCharge) {
		try {
			Double destPenChargeDouble = StringUtils.asDouble(destPenCharge);
			this.destPenCharge = (destPenChargeDouble == null) 
					? null : BigDecimal.valueOf(destPenChargeDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + destPenCharge + "] as the Dest Penalty Charge is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public String getDestFreeDayType() {
		return destFreeDayType;
	}

	public void setDestFreeDayType(String destFreeDayType) {
		this.destFreeDayType = destFreeDayType;
	}

	public Currency getDestPortPenCur() {
		return destPortPenCur;
	}

	public void setDestPortPenCur(Currency destPortPenCur) {
		this.destPortPenCur = destPortPenCur;
	}

	public BigDecimal getDestPortPenCharge() {
		return destPortPenCharge;
	}

	public void setDestPortPenCharge(BigDecimal destPortPenCharge) {
		this.destPortPenCharge = destPortPenCharge;
	}
	
	public void setDestPortPenCharge(String destPortPenCharge) {
		try {
			Double destPortPenChargeDouble = StringUtils.asDouble(destPortPenCharge);
			this.destPenCharge = (destPortPenChargeDouble == null) 
					? null : BigDecimal.valueOf(destPortPenChargeDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + destPortPenCharge + "] as the Dest Port Penalty Charge is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public String getDestPortFreeDayType() {
		return destPortFreeDayType;
	}

	public void setDestPortFreeDayType(String destPortFreeDayType) {
		this.destPortFreeDayType = destPortFreeDayType;
	}

	public void setRateDetailCharges(List<RateDetailCharges> rateDetailCharges) {
		this.rateDetailCharges = rateDetailCharges;
	}

	public List<RateDetailCharges> getRateDetailCharges() {
		return rateDetailCharges;
	}

	public BigDecimal getOrigFreeDaysHaz() {
		return origFreeDaysHaz;
	}

	public void setOrigFreeDaysHaz(BigDecimal origFreeDaysHaz) {
		this.origFreeDaysHaz = origFreeDaysHaz;
	}
	
	public void setOrigFreeDaysHaz(String origFreeDaysHaz) {
		try {
			Double origFreeDaysHazDouble = StringUtils.asDouble(origFreeDaysHaz);
			this.origFreeDaysHaz = (origFreeDaysHazDouble == null) 
					? null : BigDecimal.valueOf(origFreeDaysHazDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + origFreeDaysHaz + "] as the Hazardous Origin Free Days is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getOrigPortFreeDaysHaz() {
		return origPortFreeDaysHaz;
	}

	public void setOrigPortFreeDaysHaz(BigDecimal origPortFreeDaysHaz) {
		this.origPortFreeDaysHaz = origPortFreeDaysHaz;
	}
	
	public void setOrigPortFreeDaysHaz(String origPortFreeDaysHaz) {
		try {
			Double origPortFreeDaysHazDouble = StringUtils.asDouble(origPortFreeDaysHaz);
			this.origPortFreeDaysHaz = (origPortFreeDaysHazDouble == null) 
					? null : BigDecimal.valueOf(origPortFreeDaysHazDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + origPortFreeDaysHaz + "] as the Hazardous Origin Port Free Days is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getDestFreeDaysHaz() {
		return destFreeDaysHaz;
	}

	public void setDestFreeDaysHaz(BigDecimal destFreeDaysHaz) {
		this.destFreeDaysHaz = destFreeDaysHaz;
	}
	
	public void setDestFreeDaysHaz(String destFreeDaysHaz) {
		try {
			Double destFreeDaysHazDouble = StringUtils.asDouble(destFreeDaysHaz);
			this.destFreeDaysHaz = (destFreeDaysHazDouble == null) 
					? null : BigDecimal.valueOf(destFreeDaysHazDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + destFreeDaysHaz + "] as the Hazardous Destination Free Days is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getDestPortFreeDaysHaz() {
		return destPortFreeDaysHaz;
	}

	public void setDestPortFreeDaysHaz(BigDecimal destPortFreeDaysHaz) {
		this.destPortFreeDaysHaz = destPortFreeDaysHaz;
	}
	
	public void setDestPortFreeDaysHaz(String destPortFreeDaysHaz) {
		try {
			Double destPortFreeDaysHazDouble = StringUtils.asDouble(destPortFreeDaysHaz);
			this.destPortFreeDaysHaz = (destPortFreeDaysHazDouble == null) 
					? null : BigDecimal.valueOf(destPortFreeDaysHazDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + destPortFreeDaysHaz + "] as the Hazardous Destination Port Free Days is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}
}