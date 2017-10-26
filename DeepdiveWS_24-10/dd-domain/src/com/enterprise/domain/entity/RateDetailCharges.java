package com.enterprise.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;
import org.springframework.beans.factory.annotation.Configurable;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.DateUtils;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "RATE_DETAIL_CHARGES", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "RATE_DETAIL_CHARGES", 
	indexes = { 
		@Index(name = "rd_charges_id_ix", columnNames = { "ID" } )
	}
)
@Configurable
public class RateDetailCharges implements IEntity, Serializable{
	
	private static final long serialVersionUID = 3473207706636358010L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "RATE_DETAIL_CHARGES_SEQ")
	@SequenceGenerator(name = "RATE_DETAIL_CHARGES_SEQ", sequenceName = "GLOBAL.RATE_DETAIL_CHARGES_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "RD_CHARGE1", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge1;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY1", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency1;
	
	@Column(name = "RD_CHARGEDESC1", nullable = true)
	private String chargeDesc1;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE1", nullable = true)
	private Date startDate1;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE1", nullable = true)
	private Date endDate1;
	
	@Column(name = "RD_CHARGE2", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge2;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY2", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency2;
	
	@Column(name = "RD_CHARGEDESC2", nullable = true)
	private String chargeDesc2;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE2", nullable = true)
	private Date startDate2;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE2", nullable = true)
	private Date endDate2;
	
	@Column(name = "RD_CHARGE3", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge3;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY3", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency3;
	
	@Column(name = "RD_CHARGEDESC3", nullable = true)
	private String chargeDesc3;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE3", nullable = true)
	private Date startDate3;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE3", nullable = true)
	private Date endDate3;
	
	@Column(name = "RD_CHARGE4", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge4;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY4", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency4;
	
	@Column(name = "RD_CHARGEDESC4", nullable = true)
	private String chargeDesc4;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE4", nullable = true)
	private Date startDate4;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE4", nullable = true)
	private Date endDate4;
	
	@Column(name = "RD_CHARGE5", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge5;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY5", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency5;
	
	@Column(name = "RD_CHARGEDESC5", nullable = true)
	private String chargeDesc5;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE5", nullable = true)
	private Date startDate5;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE5", nullable = true)
	private Date endDate5;
	
	@Column(name = "RD_CHARGE6", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge6;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY6", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency6;
	
	@Column(name = "RD_CHARGEDESC6", nullable = true)
	private String chargeDesc6;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE6", nullable = true)
	private Date startDate6;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE6", nullable = true)
	private Date endDate6;
	
	@Column(name = "RD_CHARGE7", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge7;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY7", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency7;
	
	@Column(name = "RD_CHARGEDESC7", nullable = true)
	private String chargeDesc7;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE7", nullable = true)
	private Date startDate7;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE7", nullable = true)
	private Date endDate7;
	
	@Column(name = "RD_CHARGE8", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge8;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY8", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency8;
	
	@Column(name = "RD_CHARGEDESC8", nullable = true)
	private String chargeDesc8;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE8", nullable = true)
	private Date startDate8;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE8", nullable = true)
	private Date endDate8;
	
	@Column(name = "RD_CHARGE9", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge9;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY9", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency9;
	
	@Column(name = "RD_CHARGEDESC9", nullable = true)
	private String chargeDesc9;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE9", nullable = true)
	private Date startDate9;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE9", nullable = true)
	private Date endDate9;
	
	@Column(name = "RD_CHARGE10", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge10;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY10", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency10;
	
	@Column(name = "RD_CHARGEDESC10", nullable = true)
	private String chargeDesc10;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE10", nullable = true)
	private Date startDate10;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE10", nullable = true)
	private Date endDate10;
	
	@Column(name = "RD_CHARGE11", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge11;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY11", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency11;
	
	@Column(name = "RD_CHARGEDESC11", nullable = true)
	private String chargeDesc11;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE11", nullable = true)
	private Date startDate11;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE11", nullable = true)
	private Date endDate11;
	
	@Column(name = "RD_CHARGE12", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge12;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY12", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency12;
	
	@Column(name = "RD_CHARGEDESC12", nullable = true)
	private String chargeDesc12;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE12", nullable = true)
	private Date startDate12;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE12", nullable = true)
	private Date endDate12;
	
	@Column(name = "RD_CHARGE13", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge13;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY13", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency13;
	
	@Column(name = "RD_CHARGEDESC13", nullable = true)
	private String chargeDesc13;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE13", nullable = true)
	private Date startDate13;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE13", nullable = true)
	private Date endDate13;
	
	@Column(name = "RD_CHARGE14", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge14;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY14", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency14;
	
	@Column(name = "RD_CHARGEDESC14", nullable = true)
	private String chargeDesc14;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE14", nullable = true)
	private Date startDate14;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE14", nullable = true)
	private Date endDate14;
	
	@Column(name = "RD_CHARGE15", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge15;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY15", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency15;
	
	@Column(name = "RD_CHARGEDESC15", nullable = true)
	private String chargeDesc15;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE15", nullable = true)
	private Date startDate15;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE15", nullable = true)
	private Date endDate15;
	
	@Column(name = "RD_CHARGE16", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge16;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY16", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency16;
	
	@Column(name = "RD_CHARGEDESC16", nullable = true)
	private String chargeDesc16;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE16", nullable = true)
	private Date startDate16;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE16", nullable = true)
	private Date endDate16;
	
	@Column(name = "RD_CHARGE17", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge17;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY17", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency17;
	
	@Column(name = "RD_CHARGEDESC17", nullable = true)
	private String chargeDesc17;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE17", nullable = true)
	private Date startDate17;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE17", nullable = true)
	private Date endDate17;
	
	@Column(name = "RD_CHARGE18", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge18;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY18", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency18;
	
	@Column(name = "RD_CHARGEDESC18", nullable = true)
	private String chargeDesc18;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE18", nullable = true)
	private Date startDate18;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE18", nullable = true)
	private Date endDate18;
	
	@Column(name = "RD_CHARGE19", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge19;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY19", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency19;
	
	@Column(name = "RD_CHARGEDESC19", nullable = true)
	private String chargeDesc19;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE19", nullable = true)
	private Date startDate19;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE19", nullable = true)
	private Date endDate19;
	
	@Column(name = "RD_CHARGE20", nullable = true, precision = 13, scale = 3)
	private BigDecimal charge20;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RD_CURRENCY20", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency20;
	
	@Column(name = "RD_CHARGEDESC20", nullable = true)
	private String chargeDesc20;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_STARTDATE20", nullable = true)
	private Date startDate20;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RD_ENDDATE20", nullable = true)
	private Date endDate20;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "RATE_DETAIL_ID", referencedColumnName = "ID", nullable = false)
	})
	private RateDetail rateDetail;


	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public BigDecimal getCharge1() {
		return charge1;
	}

	public void setCharge1(BigDecimal charge1) {
		this.charge1 = charge1;
	}
	
	public void setCharge1(String charge1) {
		try {
			Double charge1Double = StringUtils.asDouble(charge1);
			this.charge1 = (charge1Double == null) 
					? null : BigDecimal.valueOf(charge1Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge1 + "] as the Charge 1 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency1() {
		return currency1;
	}

	public void setCurrency1(Currency currency1) {
		this.currency1 = currency1;
	}

	public String getChargeDesc1() {
		return chargeDesc1;
	}

	public void setChargeDesc1(String chargeDesc1) {
		this.chargeDesc1 = chargeDesc1;
	}

	public Date getStartDate1() {
		return startDate1;
	}

	public void setStartDate1(Date startDate1) {
		this.startDate1 = startDate1;
	}
	
	public void setStartDate1(String startDate1) {		
		try {
			this.startDate1 = DateUtils.toValidatedDate(StringUtils.upper(startDate1));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate1 + "] as the Start Date 1 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEndDate1() {
		return endDate1;
	}

	public void setEndDate1(Date endDate1) {
		this.endDate1 = endDate1;
	}

	public void setEndDate1(String endDate1) {		
		try {
			this.endDate1 = DateUtils.toValidatedDate(StringUtils.upper(endDate1));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate1 + "] as the End Date 1 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}
	
	public BigDecimal getCharge2() {
		return charge2;
	}

	public void setCharge2(BigDecimal charge2) {
		this.charge2 = charge2;
	}
	
	public void setCharge2(String charge2) {
		try {
			Double charge2Double = StringUtils.asDouble(charge2);
			this.charge2 = (charge2Double == null) 
					? null : BigDecimal.valueOf(charge2Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge2 + "] as the Charge 2 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency2() {
		return currency2;
	}

	public void setCurrency2(Currency currency2) {
		this.currency2 = currency2;
	}

	public String getChargeDesc2() {
		return chargeDesc2;
	}

	public void setChargeDesc2(String chargeDesc2) {
		this.chargeDesc2 = chargeDesc2;
	}

	public Date getStartDate2() {
		return startDate2;
	}

	public void setStartDate2(Date startDate2) {
		this.startDate2 = startDate2;
	}

	public void setStartDate2(String startDate2) {		
		try {
			this.startDate2 = DateUtils.toValidatedDate(StringUtils.upper(startDate2));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate2 + "] as the Start Date 2 is invalid. " + 
					"The system expects a valid date value. Example: 22-Feb-2024.");
		}
	}
	
	public Date getEndDate2() {
		return endDate2;
	}

	public void setEndDate2(Date endDate2) {
		this.endDate2 = endDate2;
	}
	
	public void setEndDate2(String endDate2) {		
		try {
			this.endDate2 = DateUtils.toValidatedDate(StringUtils.upper(endDate2));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate2 + "] as the End Date 2 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge3() {
		return charge3;
	}

	public void setCharge3(BigDecimal charge3) {
		this.charge3 = charge3;
	}
	
	public void setCharge3(String charge3) {
		try {
			Double charge3Double = StringUtils.asDouble(charge3);
			this.charge3 = (charge3Double == null) 
					? null : BigDecimal.valueOf(charge3Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge3 + "] as the Charge 3 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency3() {
		return currency3;
	}

	public void setCurrency3(Currency currency3) {
		this.currency3 = currency3;
	}

	public String getChargeDesc3() {
		return chargeDesc3;
	}

	public void setChargeDesc3(String chargeDesc3) {
		this.chargeDesc3 = chargeDesc3;
	}

	public Date getStartDate3() {
		return startDate3;
	}
	
	public void setStartDate3(String startDate3) {		
		try {
			this.startDate3 = DateUtils.toValidatedDate(StringUtils.upper(startDate3));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate3 + "] as the Start Date 3 is invalid. " + 
					"The system expects a valid date value. Example: 32-Feb-2034.");
		}
	}

	public void setStartDate3(Date startDate3) {
		this.startDate3 = startDate3;
	}

	public Date getEndDate3() {
		return endDate3;
	}

	public void setEndDate3(Date endDate3) {
		this.endDate3 = endDate3;
	}
	
	public void setEndDate3(String endDate3) {		
		try {
			this.endDate3 = DateUtils.toValidatedDate(StringUtils.upper(endDate3));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate3 + "] as the End Date 3 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge4() {
		return charge4;
	}

	public void setCharge4(BigDecimal charge4) {
		this.charge4 = charge4;
	}
	
	public void setCharge4(String charge4) {
		try {
			Double charge4Double = StringUtils.asDouble(charge4);
			this.charge4 = (charge4Double == null) 
					? null : BigDecimal.valueOf(charge4Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge4 + "] as the Charge 4 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency4() {
		return currency4;
	}

	public void setCurrency4(Currency currency4) {
		this.currency4 = currency4;
	}

	public String getChargeDesc4() {
		return chargeDesc4;
	}

	public void setChargeDesc4(String chargeDesc4) {
		this.chargeDesc4 = chargeDesc4;
	}

	public Date getStartDate4() {
		return startDate4;
	}

	public void setStartDate4(Date startDate4) {
		this.startDate4 = startDate4;
	}
	
	public void setStartDate4(String startDate4) {		
		try {
			this.startDate4 = DateUtils.toValidatedDate(StringUtils.upper(startDate4));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate4 + "] as the Start Date 4 is invalid. " + 
					"The system expects a valid date value. Example: 42-Feb-2014.");
		}
	}

	public Date getEndDate4() {
		return endDate4;
	}

	public void setEndDate4(Date endDate4) {
		this.endDate4 = endDate4;
	}
	
	public void setEndDate4(String endDate4) {		
		try {
			this.endDate4 = DateUtils.toValidatedDate(StringUtils.upper(endDate4));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate4 + "] as the End Date 4 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge5() {
		return charge5;
	}

	public void setCharge5(BigDecimal charge5) {
		this.charge5 = charge5;
	}
	
	public void setCharge5(String charge5) {
		try {
			Double charge5Double = StringUtils.asDouble(charge5);
			this.charge5 = (charge5Double == null) 
					? null : BigDecimal.valueOf(charge5Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge5 + "] as the Charge 5 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.455");
		}
	}

	public Currency getCurrency5() {
		return currency5;
	}

	public void setCurrency5(Currency currency5) {
		this.currency5 = currency5;
	}

	public String getChargeDesc5() {
		return chargeDesc5;
	}

	public void setChargeDesc5(String chargeDesc5) {
		this.chargeDesc5 = chargeDesc5;
	}

	public Date getStartDate5() {
		return startDate5;
	}

	public void setStartDate5(Date startDate5) {
		this.startDate5 = startDate5;
	}
	
	public void setStartDate5(String startDate5) {		
		try {
			this.startDate5 = DateUtils.toValidatedDate(StringUtils.upper(startDate5));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate5 + "] as the Start Date 5 is invalid. " + 
					"The system expects a valid date value. Example: 52-Feb-2015.");
		}
	}

	public Date getEndDate5() {
		return endDate5;
	}

	public void setEndDate5(Date endDate5) {
		this.endDate5 = endDate5;
	}
	
	public void setEndDate5(String endDate5) {		
		try {
			this.endDate5 = DateUtils.toValidatedDate(StringUtils.upper(endDate5));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate5 + "] as the End Date 5 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge6() {
		return charge6;
	}

	public void setCharge6(BigDecimal charge6) {
		this.charge6 = charge6;
	}
	
	public void setCharge6(String charge6) {
		try {
			Double charge6Double = StringUtils.asDouble(charge6);
			this.charge6 = (charge6Double == null) 
					? null : BigDecimal.valueOf(charge6Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge6 + "] as the Charge 6 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency6() {
		return currency6;
	}

	public void setCurrency6(Currency currency6) {
		this.currency6 = currency6;
	}

	public String getChargeDesc6() {
		return chargeDesc6;
	}

	public void setChargeDesc6(String chargeDesc6) {
		this.chargeDesc6 = chargeDesc6;
	}

	public Date getStartDate6() {
		return startDate6;
	}

	public void setStartDate6(Date startDate6) {
		this.startDate6 = startDate6;
	}
	
	public void setStartDate6(String startDate6) {		
		try {
			this.startDate6 = DateUtils.toValidatedDate(StringUtils.upper(startDate6));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate6 + "] as the Start Date 6 is invalid. " + 
					"The system expects a valid date value. Example: 62-Feb-2016.");
		}
	}

	public Date getEndDate6() {
		return endDate6;
	}

	public void setEndDate6(Date endDate6) {
		this.endDate6 = endDate6;
	}
	
	public void setEndDate6(String endDate6) {		
		try {
			this.endDate6 = DateUtils.toValidatedDate(StringUtils.upper(endDate6));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate6 + "] as the End Date 6 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge7() {
		return charge7;
	}

	public void setCharge7(BigDecimal charge7) {
		this.charge7 = charge7;
	}
	
	public void setCharge7(String charge7) {
		try {
			Double charge7Double = StringUtils.asDouble(charge7);
			this.charge7 = (charge7Double == null) 
					? null : BigDecimal.valueOf(charge7Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge7 + "] as the Charge 7 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency7() {
		return currency7;
	}

	public void setCurrency7(Currency currency7) {
		this.currency7 = currency7;
	}

	public String getChargeDesc7() {
		return chargeDesc7;
	}

	public void setChargeDesc7(String chargeDesc7) {
		this.chargeDesc7 = chargeDesc7;
	}

	public Date getStartDate7() {
		return startDate7;
	}

	public void setStartDate7(Date startDate7) {
		this.startDate7 = startDate7;
	}
	
	public void setStartDate7(String startDate7) {		
		try {
			this.startDate7 = DateUtils.toValidatedDate(StringUtils.upper(startDate7));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate7 + "] as the Start Date 7 is invalid. " + 
					"The system expects a valid date value. Example: 72-Feb-2017.");
		}
	}

	public Date getEndDate7() {
		return endDate7;
	}

	public void setEndDate7(Date endDate7) {
		this.endDate7 = endDate7;
	}
	
	public void setEndDate7(String endDate7) {		
		try {
			this.endDate7 = DateUtils.toValidatedDate(StringUtils.upper(endDate7));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate7 + "] as the End Date 7 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge8() {
		return charge8;
	}
	
	public void setCharge8(String charge8) {
		try {
			Double charge8Double = StringUtils.asDouble(charge8);
			this.charge8 = (charge8Double == null) 
					? null : BigDecimal.valueOf(charge8Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge8 + "] as the Charge 8 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public void setCharge8(BigDecimal charge8) {
		this.charge8 = charge8;
	}

	public Currency getCurrency8() {
		return currency8;
	}

	public void setCurrency8(Currency currency8) {
		this.currency8 = currency8;
	}

	public String getChargeDesc8() {
		return chargeDesc8;
	}

	public void setChargeDesc8(String chargeDesc8) {
		this.chargeDesc8 = chargeDesc8;
	}

	public Date getStartDate8() {
		return startDate8;
	}

	public void setStartDate8(Date startDate8) {
		this.startDate8 = startDate8;
	}
	
	public void setStartDate8(String startDate8) {		
		try {
			this.startDate8 = DateUtils.toValidatedDate(StringUtils.upper(startDate8));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate8 + "] as the Start Date 8 is invalid. " + 
					"The system expects a valid date value. Example: 82-Feb-2018.");
		}
	}

	public Date getEndDate8() {
		return endDate8;
	}

	public void setEndDate8(Date endDate8) {
		this.endDate8 = endDate8;
	}
	
	public void setEndDate8(String endDate8) {		
		try {
			this.endDate8 = DateUtils.toValidatedDate(StringUtils.upper(endDate8));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate8 + "] as the End Date 8 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge9() {
		return charge9;
	}

	public void setCharge9(BigDecimal charge9) {
		this.charge9 = charge9;
	}
	
	public void setCharge9(String charge9) {
		try {
			Double charge9Double = StringUtils.asDouble(charge9);
			this.charge9 = (charge9Double == null) 
					? null : BigDecimal.valueOf(charge9Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge9 + "] as the Charge 9 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency9() {
		return currency9;
	}

	public void setCurrency9(Currency currency9) {
		this.currency9 = currency9;
	}

	public String getChargeDesc9() {
		return chargeDesc9;
	}

	public void setChargeDesc9(String chargeDesc9) {
		this.chargeDesc9 = chargeDesc9;
	}

	public Date getStartDate9() {
		return startDate9;
	}

	public void setStartDate9(Date startDate9) {
		this.startDate9 = startDate9;
	}
	
	public void setStartDate9(String startDate9) {		
		try {
			this.startDate9 = DateUtils.toValidatedDate(StringUtils.upper(startDate9));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate9 + "] as the Start Date 9 is invalid. " + 
					"The system expects a valid date value. Example: 92-Feb-2019.");
		}
	}

	public Date getEndDate9() {
		return endDate9;
	}

	public void setEndDate9(Date endDate9) {
		this.endDate9 = endDate9;
	}

	public void setEndDate9(String endDate9) {		
		try {
			this.endDate9 = DateUtils.toValidatedDate(StringUtils.upper(endDate9));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate9 + "] as the End Date 9 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}
	
	public BigDecimal getCharge10() {
		return charge10;
	}

	public void setCharge10(BigDecimal charge10) {
		this.charge10 = charge10;
	}
	
	public void setCharge10(String charge10) {
		try {
			Double charge10Double = StringUtils.asDouble(charge10);
			this.charge10 = (charge10Double == null) 
					? null : BigDecimal.valueOf(charge10Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge10 + "] as the Charge 10 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency10() {
		return currency10;
	}

	public void setCurrency10(Currency currency10) {
		this.currency10 = currency10;
	}

	public String getChargeDesc10() {
		return chargeDesc10;
	}

	public void setChargeDesc10(String chargeDesc10) {
		this.chargeDesc10 = chargeDesc10;
	}

	public Date getStartDate10() {
		return startDate10;
	}

	public void setStartDate10(Date startDate10) {
		this.startDate10 = startDate10;
	}
	
	public void setStartDate10(String startDate10) {		
		try {
			this.startDate10 = DateUtils.toValidatedDate(StringUtils.upper(startDate10));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate10 + "] as the Start Date 10 is invalid. " + 
					"The system expects a valid date value. Example: 102-Feb-20110.");
		}
	}

	public Date getEndDate10() {
		return endDate10;
	}

	public void setEndDate10(Date endDate10) {
		this.endDate10 = endDate10;
	}
	
	public void setEndDate10(String endDate10) {		
		try {
			this.endDate10 = DateUtils.toValidatedDate(StringUtils.upper(endDate10));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate10 + "] as the End Date 10 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge11() {
		return charge11;
	}

	public void setCharge11(BigDecimal charge11) {
		this.charge11 = charge11;
	}
	
	public void setCharge11(String charge11) {
		try {
			Double charge11Double = StringUtils.asDouble(charge11);
			this.charge11 = (charge11Double == null) 
					? null : BigDecimal.valueOf(charge11Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge11 + "] as the Charge 11 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency11() {
		return currency11;
	}

	public void setCurrency11(Currency currency11) {
		this.currency11 = currency11;
	}

	public String getChargeDesc11() {
		return chargeDesc11;
	}

	public void setChargeDesc11(String chargeDesc11) {
		this.chargeDesc11 = chargeDesc11;
	}

	public Date getStartDate11() {
		return startDate11;
	}

	public void setStartDate11(Date startDate11) {
		this.startDate11 = startDate11;
	}
	
	public void setStartDate11(String startDate11) {		
		try {
			this.startDate11 = DateUtils.toValidatedDate(StringUtils.upper(startDate11));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate11 + "] as the Start Date 11 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEndDate11() {
		return endDate11;
	}

	public void setEndDate11(Date endDate11) {
		this.endDate11 = endDate11;
	}
	
	public void setEndDate11(String endDate11) {		
		try {
			this.endDate11 = DateUtils.toValidatedDate(StringUtils.upper(endDate11));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate11 + "] as the End Date 11 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge12() {
		return charge12;
	}

	public void setCharge12(BigDecimal charge12) {
		this.charge12 = charge12;
	}
	
	public void setCharge12(String charge12) {
		try {
			Double charge12Double = StringUtils.asDouble(charge12);
			this.charge12 = (charge12Double == null) 
					? null : BigDecimal.valueOf(charge12Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge12 + "] as the Charge 12 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency12() {
		return currency12;
	}

	public void setCurrency12(Currency currency12) {
		this.currency12 = currency12;
	}

	public String getChargeDesc12() {
		return chargeDesc12;
	}

	public void setChargeDesc12(String chargeDesc12) {
		this.chargeDesc12 = chargeDesc12;
	}

	public Date getStartDate12() {
		return startDate12;
	}

	public void setStartDate12(Date startDate12) {
		this.startDate12 = startDate12;
	}
	
	public void setStartDate12(String startDate12) {		
		try {
			this.startDate12 = DateUtils.toValidatedDate(StringUtils.upper(startDate12));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate12 + "] as the Start Date 12 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEndDate12() {
		return endDate12;
	}

	public void setEndDate12(Date endDate12) {
		this.endDate12 = endDate12;
	}
	
	public void setEndDate12(String endDate12) {		
		try {
			this.endDate12 = DateUtils.toValidatedDate(StringUtils.upper(endDate12));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate12 + "] as the End Date 12 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge13() {
		return charge13;
	}

	public void setCharge13(BigDecimal charge13) {
		this.charge13 = charge13;
	}
	
	public void setCharge13(String charge13) {
		try {
			Double charge13Double = StringUtils.asDouble(charge13);
			this.charge13 = (charge13Double == null) 
					? null : BigDecimal.valueOf(charge13Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge13 + "] as the Charge 13 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency13() {
		return currency13;
	}

	public void setCurrency13(Currency currency13) {
		this.currency13 = currency13;
	}

	public String getChargeDesc13() {
		return chargeDesc13;
	}

	public void setChargeDesc13(String chargeDesc13) {
		this.chargeDesc13 = chargeDesc13;
	}

	public Date getStartDate13() {
		return startDate13;
	}

	public void setStartDate13(Date startDate13) {
		this.startDate13 = startDate13;
	}
	
	public void setStartDate13(String startDate13) {		
		try {
			this.startDate13 = DateUtils.toValidatedDate(StringUtils.upper(startDate13));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate13 + "] as the Start Date 13 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEndDate13() {
		return endDate13;
	}

	public void setEndDate13(Date endDate13) {
		this.endDate13 = endDate13;
	}
	
	public void setEndDate13(String endDate13) {		
		try {
			this.endDate13 = DateUtils.toValidatedDate(StringUtils.upper(endDate13));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate13 + "] as the End Date 13 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge14() {
		return charge14;
	}

	public void setCharge14(BigDecimal charge14) {
		this.charge14 = charge14;
	}
	
	public void setCharge14(String charge14) {
		try {
			Double charge14Double = StringUtils.asDouble(charge14);
			this.charge14 = (charge14Double == null) 
					? null : BigDecimal.valueOf(charge14Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge14 + "] as the Charge 14 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency14() {
		return currency14;
	}

	public void setCurrency14(Currency currency14) {
		this.currency14 = currency14;
	}

	public String getChargeDesc14() {
		return chargeDesc14;
	}

	public void setChargeDesc14(String chargeDesc14) {
		this.chargeDesc14 = chargeDesc14;
	}

	public Date getStartDate14() {
		return startDate14;
	}

	public void setStartDate14(Date startDate14) {
		this.startDate14 = startDate14;
	}
	
	public void setStartDate14(String startDate14) {		
		try {
			this.startDate14 = DateUtils.toValidatedDate(StringUtils.upper(startDate14));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate14 + "] as the Start Date 14 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEndDate14() {
		return endDate14;
	}

	public void setEndDate14(Date endDate14) {
		this.endDate14 = endDate14;
	}
	
	public void setEndDate14(String endDate14) {		
		try {
			this.endDate14 = DateUtils.toValidatedDate(StringUtils.upper(endDate14));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate14 + "] as the End Date 14 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge15() {
		return charge15;
	}

	public void setCharge15(BigDecimal charge15) {
		this.charge15 = charge15;
	}
	
	public void setCharge15(String charge15) {
		try {
			Double charge15Double = StringUtils.asDouble(charge15);
			this.charge15 = (charge15Double == null) 
					? null : BigDecimal.valueOf(charge15Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge15 + "] as the Charge 15 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency15() {
		return currency15;
	}

	public void setCurrency15(Currency currency15) {
		this.currency15 = currency15;
	}

	public String getChargeDesc15() {
		return chargeDesc15;
	}

	public void setChargeDesc15(String chargeDesc15) {
		this.chargeDesc15 = chargeDesc15;
	}

	public Date getStartDate15() {
		return startDate15;
	}

	public void setStartDate15(Date startDate15) {
		this.startDate15 = startDate15;
	}
	
	public void setStartDate15(String startDate15) {		
		try {
			this.startDate15 = DateUtils.toValidatedDate(StringUtils.upper(startDate15));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate15 + "] as the Start Date 15 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEndDate15() {
		return endDate15;
	}

	public void setEndDate15(Date endDate15) {
		this.endDate15 = endDate15;
	}
	
	public void setEndDate15(String endDate15) {		
		try {
			this.endDate15 = DateUtils.toValidatedDate(StringUtils.upper(endDate15));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate15 + "] as the End Date 15 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge16() {
		return charge16;
	}

	public void setCharge16(BigDecimal charge16) {
		this.charge16 = charge16;
	}
	
	public void setCharge16(String charge16) {
		try {
			Double charge16Double = StringUtils.asDouble(charge16);
			this.charge16 = (charge16Double == null) 
					? null : BigDecimal.valueOf(charge16Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge16 + "] as the Charge 16 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency16() {
		return currency16;
	}

	public void setCurrency16(Currency currency16) {
		this.currency16 = currency16;
	}

	public String getChargeDesc16() {
		return chargeDesc16;
	}

	public void setChargeDesc16(String chargeDesc16) {
		this.chargeDesc16 = chargeDesc16;
	}

	public Date getStartDate16() {
		return startDate16;
	}

	public void setStartDate16(Date startDate16) {
		this.startDate16 = startDate16;
	}

	public void setStartDate16(String startDate16) {		
		try {
			this.startDate16 = DateUtils.toValidatedDate(StringUtils.upper(startDate16));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate16 + "] as the Start Date 16 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}
	
	public Date getEndDate16() {
		return endDate16;
	}

	public void setEndDate16(Date endDate16) {
		this.endDate16 = endDate16;
	}
	
	public void setEndDate16(String endDate16) {		
		try {
			this.endDate16 = DateUtils.toValidatedDate(StringUtils.upper(endDate16));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate16 + "] as the End Date 16 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge17() {
		return charge17;
	}

	public void setCharge17(BigDecimal charge17) {
		this.charge17 = charge17;
	}
	
	public void setCharge17(String charge17) {
		try {
			Double charge17Double = StringUtils.asDouble(charge17);
			this.charge17 = (charge17Double == null) 
					? null : BigDecimal.valueOf(charge17Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge17 + "] as the Charge 17 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency17() {
		return currency17;
	}

	public void setCurrency17(Currency currency17) {
		this.currency17 = currency17;
	}

	public String getChargeDesc17() {
		return chargeDesc17;
	}

	public void setChargeDesc17(String chargeDesc17) {
		this.chargeDesc17 = chargeDesc17;
	}

	public Date getStartDate17() {
		return startDate17;
	}

	public void setStartDate17(Date startDate17) {
		this.startDate17 = startDate17;
	}
	
	public void setStartDate17(String startDate17) {		
		try {
			this.startDate17 = DateUtils.toValidatedDate(StringUtils.upper(startDate17));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate17 + "] as the Start Date 17 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEndDate17() {
		return endDate17;
	}

	public void setEndDate17(Date endDate17) {
		this.endDate17 = endDate17;
	}
	
	public void setEndDate17(String endDate17) {		
		try {
			this.endDate17 = DateUtils.toValidatedDate(StringUtils.upper(endDate17));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate17 + "] as the End Date 17 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge18() {
		return charge18;
	}

	public void setCharge18(BigDecimal charge18) {
		this.charge18 = charge18;
	}
	
	public void setCharge18(String charge18) {
		try {
			Double charge18Double = StringUtils.asDouble(charge18);
			this.charge18 = (charge18Double == null) 
					? null : BigDecimal.valueOf(charge18Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge18 + "] as the Charge 18 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency18() {
		return currency18;
	}

	public void setCurrency18(Currency currency18) {
		this.currency18 = currency18;
	}

	public String getChargeDesc18() {
		return chargeDesc18;
	}

	public void setChargeDesc18(String chargeDesc18) {
		this.chargeDesc18 = chargeDesc18;
	}

	public Date getStartDate18() {
		return startDate18;
	}

	public void setStartDate18(Date startDate18) {
		this.startDate18 = startDate18;
	}
	
	public void setStartDate18(String startDate18) {		
		try {
			this.startDate18 = DateUtils.toValidatedDate(StringUtils.upper(startDate18));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate18 + "] as the Start Date 18 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEndDate18() {
		return endDate18;
	}

	public void setEndDate18(Date endDate18) {
		this.endDate18 = endDate18;
	}
	
	public void setEndDate18(String endDate18) {		
		try {
			this.endDate18 = DateUtils.toValidatedDate(StringUtils.upper(endDate18));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate18 + "] as the End Date 18 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge19() {
		return charge19;
	}

	public void setCharge19(BigDecimal charge19) {
		this.charge19 = charge19;
	}
	
	public void setCharge19(String charge19) {
		try {
			Double charge19Double = StringUtils.asDouble(charge19);
			this.charge19 = (charge19Double == null) 
					? null : BigDecimal.valueOf(charge19Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge19 + "] as the Charge 19 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency19() {
		return currency19;
	}

	public void setCurrency19(Currency currency19) {
		this.currency19 = currency19;
	}

	public String getChargeDesc19() {
		return chargeDesc19;
	}

	public void setChargeDesc19(String chargeDesc19) {
		this.chargeDesc19 = chargeDesc19;
	}

	public Date getStartDate19() {
		return startDate19;
	}

	public void setStartDate19(Date startDate19) {
		this.startDate19 = startDate19;
	}
	
	public void setStartDate19(String startDate19) {		
		try {
			this.startDate19 = DateUtils.toValidatedDate(StringUtils.upper(startDate19));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate19 + "] as the Start Date 19 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEndDate19() {
		return endDate19;
	}

	public void setEndDate19(Date endDate19) {
		this.endDate19 = endDate19;
	}
	
	public void setEndDate19(String endDate19) {		
		try {
			this.endDate19 = DateUtils.toValidatedDate(StringUtils.upper(endDate19));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate19 + "] as the End Date 19 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public BigDecimal getCharge20() {
		return charge20;
	}

	public void setCharge20(BigDecimal charge20) {
		this.charge20 = charge20;
	}
	
	public void setCharge20(String charge20) {
		try {
			Double charge20Double = StringUtils.asDouble(charge20);
			this.charge20 = (charge20Double == null) 
					? null : BigDecimal.valueOf(charge20Double).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + charge20 + "] as the Charge 20 is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public Currency getCurrency20() {
		return currency20;
	}

	public void setCurrency20(Currency currency20) {
		this.currency20 = currency20;
	}

	public String getChargeDesc20() {
		return chargeDesc20;
	}

	public void setChargeDesc20(String chargeDesc20) {
		this.chargeDesc20 = chargeDesc20;
	}

	public Date getStartDate20() {
		return startDate20;
	}

	public void setStartDate20(Date startDate20) {
		this.startDate20 = startDate20;
	}
	
	public void setStartDate20(String startDate20) {		
		try {
			this.startDate20 = DateUtils.toValidatedDate(StringUtils.upper(startDate20));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + startDate20 + "] as the Start Date 20 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEndDate20() {
		return endDate20;
	}

	public void setEndDate20(Date endDate20) {
		this.endDate20 = endDate20;
	}
	
	public void setEndDate20(String endDate20) {		
		try {
			this.endDate20 = DateUtils.toValidatedDate(StringUtils.upper(endDate20));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + endDate20 + "] as the End Date 20 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public void setRateDetail(RateDetail rateDetail) {
		this.rateDetail = rateDetail;
	}

	public RateDetail getRateDetail() {
		return rateDetail;
	}

	public boolean isEmpty() {
		return false;
	}
}
