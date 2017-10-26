package com.enterprise.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.enums.ModeTypes;
import com.enterprise.common.util.CollectionUtils;
import com.enterprise.common.util.DateUtils;
import com.enterprise.common.util.StringUtils;

/**
 * Every class has Object as a superclass. All objects, including arrays, implement 
 * the methods of this superclass.
 * 
 * @since  03 Mar 2014 7:53:50 AM
 * @author rafourie
 */
@Entity
@Table(name = "SHIPMENT", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"SHIPMENT_NUMBER", "MODE", "PACKAGE_ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "SHIPMENT", 
	indexes = { 
		@Index(name = "shipment_id_ix", columnNames = { "ID" } )
	}
)
public class Shipment implements IEntity, Serializable {
	private static final long serialVersionUID = 5698097625454858468L;

	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SHIPMENT_SEQ")
	@SequenceGenerator(name = "SHIPMENT_SEQ", sequenceName = "GLOBAL.SHIPMENT_SEQ")
	@Column(name = "ID", nullable = false)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	private long id;
	
	/** SHIPMENT_NUMBER NOT NULL, 
	 *  select s.*
  	 *    from shipment s
 	 *   where s.SHIPMENT_NUMBER = cast("100628662" AS DECIMAL);
	 */
	@Column(name = "SHIPMENT_NUMBER", nullable = false, length = 30)
	private String shipmentNumber;
	
	/** YEAR varchar2(4) NOT NULL, */
	@Column(name = "YEAR", nullable = true, length = 4)
	private String year;
	
	@Enumerated(EnumType.STRING)
	@Column(name="MODE", nullable = false, length = 1)
	private ModeTypes mode;
	
	/** LSP_ID decimal(19), */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "LSP_ID", referencedColumnName = "ID", nullable = true)
	})
	private LogisticsServiceProvider serviceProvider;
	
	/** ORIG_CITY_ID decimal(19), */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORIG_CITY_ID", referencedColumnName = "ID", nullable = true)
	})
	private City origCity;
	
	/** DEST_CITY_ID decimal(19), */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DEST_CITY_ID", referencedColumnName = "ID", nullable = true)
	})
	private City destCity;
	
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
	
	/** ORIG_BRANCH_ID decimal(19), */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORIG_BRANCH_ID", referencedColumnName = "ID", nullable = true)
	})
	private Branch origBranch;
	
	/** DEST_BRANCH_ID decimal(19), */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "DEST_BRANCH_ID", referencedColumnName = "ID", nullable = true)
	})
	private Branch destBranch;
	
	/** SHIPPER_CMF_ID decimal(19) NOT NULL, */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SHIPPER_CMF_ID", referencedColumnName = "ID", nullable = true)
	})
	private CMF shipper;
	
	/** CONSIGNEE_CMF_ID decimal(19) NOT NULL, */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CONSIGNEE_CMF_ID", referencedColumnName = "ID", nullable = true)
	})
	private CMF consignee;
	
	/** CARRIER_ID decimal(19) NOT NULL, */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CARRIER_ID", referencedColumnName = "ID", nullable = true)
	})
	private Carrier carrier;
	
	@OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY)
	private List<ShipmentContainer> shipmentContainers = new ArrayList<ShipmentContainer>();
	
	@Column(name = "ETD", nullable = true)
	private Date etd;

	@Column(name = "ETA", nullable = true)
	private Date eta;
	
	@Column(name = "ATD", nullable = true)
	private Date atd;

	@Column(name = "ATA", nullable = true)
	private Date ata;
	
	@Column(name = "CREATE_DATE", nullable = true)
	private Date createDate;
	
	@Column(name = "BOOKING_DATE", nullable = true)
	private Date bookingDate;
	
	@Column(name = "RECEIVED_DATE", nullable = true)
	private Date receivedDate;
	
	@Column(name = "DELIVERY_DATE", nullable = true)
	private Date deliveryDate;
	
	@Column(name = "REQUIRED_DELIVERY_DATE", nullable = true)
	private Date requiredDeliveryDate;
	
	@Column(name = "NO_PIECES", nullable = true, precision = 10, scale = 0)
	private BigDecimal numberOfPieces;
	
	@Column(name = "WEIGHT", nullable = true, precision = 13, scale = 3)
	private BigDecimal weight;
	
	@Column(name = "CHARGEABLE_WEIGHT", nullable = true, precision = 13, scale = 3)
	private BigDecimal chargeableWeight;
	
	@Column(name = "VOLUME", nullable = true, precision = 13, scale = 3)
	private BigDecimal volume;
	
	@Column(name = "SHIPMENT_TYPE", nullable = true, length = 3)
	private String shipmentType;
	
	@Column(name = "SHIPMENT_TERMS", nullable = true, length = 3)
	private String shipmentTerms;
	
	@Column(name = "SERVICE_LEVEL", nullable = true)
	private String serviceLevel;
	
	@Column(name = "MASTERBILL_NUMBER", nullable = true)
	private String mastBillNo;
	
	@Column(name = "CARRIER_NAME", nullable = true)
	private String carrierName;
	
	@Column(name = "VOYAGE_NAME", nullable = true)
	private String voyageName;
	
	@Column(name = "VOYAGE_NUMBER", nullable = true)
	private String voyageNumber;
	
	@Column(name = "FLIGHT_NUMBER", nullable = true)
	private String flightNumber;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID", nullable = true)
	})
	private Package pkg;
	
	@OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY)
	private List<Invoice> invoices = new ArrayList<Invoice>();
	
	@Column(name = "LANE_ID", nullable = true)
	private String laneId;
	
	@OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY)
	private List<OrderExpedLine> orderExpedLines = new ArrayList<OrderExpedLine>();
	
	
	@Column(name = "SHIPPER_ALIAS", nullable = true, length = 128)
	private String shipperAlias;
	
	@Column(name = "CONSINGEE_ALIAS", nullable = true, length = 128)
	private String consingeeAlias;
	
	@Column(name = "USER_DEFINED1", columnDefinition="Text")
	private String userDefined1;
	
	@Column(name = "USER_DEFINED2", columnDefinition="Text")
	private String userDefined2;
	
	@Column(name = "USER_DEFINED3", columnDefinition="Text")
	private String userDefined3;
	
	@Column(name = "ORIG_COUNTRY_CD", length = 2)
	private String origCountryCd;
	
	@Column(name = "DEST_COUNTRY_CD", length = 2)
	private String destCountryCd;
	
	@Column(name = "ORIG_LOC_CD", length = 4)
	private String origLocCd;
	
	@Column(name = "DEST_LOC_CD", length = 4)
	private String destLocCd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "IN_CUSTOMS_DATE")
	private Date inCustomerDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OUT_CUSTOMS_DATE")
	private Date outCustomerDate;
	
	@Column(name = "DRY_BULK_CNTRS_20FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal dryBulkCntrs20Ft;
	
	@Column(name = "DRY_CNTRS_20FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal dryCntrs20Ft;
	
	@Column(name = "REEFER_CNTRS_20FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal reeferCntrs20Ft;
	
	@Column(name = "TANK_CNTRS_20_FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal tankCntrs20Ft;
	
	@Column(name = "FLAT_CNTRS_20FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal flatCntrs20Ft;
	
	@Column(name = "HIGH_CUBE_DRY_CNTRS_20FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal  highCubeDryCntrs20Ft;
	
	@Column(name = "INSULATED_CNTRS_20FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal insulatedCntrs20Ft;
	
	@Column(name = "OPEN_TOP_CNTRS_20FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal  openTopCntrs20Ft;
	
	@Column(name = "DRY_CNTRS_40FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal dryCntrs40Ft;
	
	@Column(name = "HIGH_CUBE_DRY_CNTRS_40FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal highCubeDryCntrs40Ft;
	
	@Column(name = "HIGH_CUBE_REEFER_CNTRS_40FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal highCubeReeferCntrs40Ft;
	
	@Column(name = "REEFER_CNTRS_40FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal reeferCntrs40Ft;
	
	@Column(name = "HIGH_CUBE_DRY_CNTRS_45FT", nullable = true, precision = 13, scale = 3)
	private BigDecimal 	highCubeDryCntrs45Ft;
	
	@Column(name = "OTHER_CNTRS", nullable = true, precision = 13, scale = 3)
	private BigDecimal otherCntrs;
	
	@Column(name = "HB_NUMBER", nullable = true)
	private String hbNumber;
	
	@Column(name = "CUSTOMS_NUMBER", nullable = true)
	private String customsNumber;
	
	@Column(name = "USER_DEFINED4", columnDefinition="Text")
	private String userDefined4;
	
	@Column(name = "USER_DEFINED5", columnDefinition="Text")
	private String userDefined5;
	
	@Column(name = "USER_DEFINED6", columnDefinition="Text")
	private String userDefined6;
	
	@Column(name = "USER_DEFINED7", columnDefinition="Text")
	private String userDefined7;
	
	@Column(name = "COMMENTS", columnDefinition="Text")
	private String comments;
	
	@Column(name = "GOOD_DESCRIPTION", columnDefinition="Text")
	private String goodDescription;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON")
	private Date updatedOn = new Date();
	
	@OneToMany(mappedBy = "shipment", fetch = FetchType.LAZY)
	private List<RefType> RefTypes = new ArrayList<RefType>();
	
	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	public List<OrderExpedLine> getOrderExpedLines() {
		return orderExpedLines;
	}

	public void setOrderExpedLines(List<OrderExpedLine> orderExpedLines) {
		this.orderExpedLines = orderExpedLines;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}
	
	public void setShipmentNumber(String shipmentNumber) {
		if (!StringUtils.isEmpty(shipmentNumber)
				&& shipmentNumber.length() > 30)
			throw new RuntimeException("Value [" + shipmentNumber + "] as the shipment number is invalid. " + 
					"The system expects a valid value of no more than 30 characters.");
		else if(StringUtils.isEmpty(shipmentNumber)){
			throw new RuntimeException("Value [" + shipmentNumber + "] as the shipment number is empty. " + 
					"The system expects a valid value of no more than 20 characters.");
		}
		this.shipmentNumber = shipmentNumber;
	}

	public IEntity clone() {
		return null;
	}
	
	public static Shipment load(String shipmentNumber, Session session, long packageId, ModeTypes mode) {
		Criteria criteria = session.createCriteria(Shipment.class)
			.add(Restrictions.eq("shipmentNumber", shipmentNumber));
		if(packageId != 0L)
			criteria.add(Restrictions.eq("pkg.id", packageId));
		if(mode!=null)
			criteria.add(Restrictions.eq("mode", mode));
		Shipment shipment = (Shipment)criteria.setMaxResults(1).uniqueResult();
		return shipment;
	}
	
	public static Shipment load(String laneId, Session session, long packageId) {
		Criteria criteria = session.createCriteria(Shipment.class)
			.add(Restrictions.eq("laneId", laneId));
		if(packageId != 0L)
			criteria.add(Restrictions.eq("pkg.id", packageId));
		Shipment shipment = (Shipment)criteria.uniqueResult();
		return shipment;
	}
	
	@SuppressWarnings("unchecked")
	public static Shipment load(String shipmentNumber, Session session) {
		Shipment shipment = null;
		Criteria criteria = session.createCriteria(Shipment.class)
			.add(Restrictions.eq("shipmentNumber", shipmentNumber));
		List<Shipment> shipments = criteria.list();
		if(null!=shipments && !shipments.isEmpty())
			shipment = (Shipment)shipments.get(0);
		return shipment;
	}
	
	@SuppressWarnings("unchecked")
	public static void deleteOrphans(Shipment shipment, UUID uuid, Session session) {
		Criteria criteria = session.createCriteria(ShipmentContainer.class)
			.add(Restrictions.eq("shipment", shipment))
			.add(Restrictions.not(Restrictions.eq("uuid", uuid.toString())));
		List<ShipmentContainer> removableShipmentContainers = criteria.list();
		if (!CollectionUtils.isEmpty(removableShipmentContainers)) {
			Map<Long, ShipmentContainer> removableEntityMap = new HashMap<Long, ShipmentContainer>();
			for (ShipmentContainer shipmentContainer : removableShipmentContainers) {
				long id = shipmentContainer.getId();
				removableEntityMap.put(Long.valueOf(id), shipmentContainer);
			}
			Set<Long> removableEntityMapKeySet = removableEntityMap.keySet();
			for (Long id : removableEntityMapKeySet) {
				ShipmentContainer shipmentContainer = removableEntityMap.get(id);
				shipment.getShipmentContainers().remove(shipmentContainer);
				session.delete(shipmentContainer);
			}
		}
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		if (!StringUtils.isEmpty(year)
				&& year.length() > 4){
			try {
				this.year = Integer.parseInt(year.replaceFirst("\\.0", ""))+"";
				if(this.year.length()<=4){
					return;
				}
			} catch (Throwable e) {
			}
			throw new RuntimeException("Value [" + year + "] as the shipment year is invalid. " + 
					"The system expects a valid year value.");
		} else {
			try {
				Integer.parseInt(year);
				this.year = year;
			} catch (Throwable e) {
				throw new RuntimeException("Value [" + year + "] as the shipment year is invalid. " + 
						"The system expects a valid year value.");
			}
		}
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
				throw new RuntimeException("Value [" + mode + "] as the shipment mode is invalid. The system expects " + 
						"a 1 character value of either 'A','O','M' or 'T'.");
			this.mode = modeType;
		}
	}

	public LogisticsServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(LogisticsServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
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

	public Branch getOrigBranch() {
		return origBranch;
	}

	public void setOrigBranch(Branch origBranch) {
		this.origBranch = origBranch;
	}

	public Branch getDestBranch() {
		return destBranch;
	}

	public void setDestBranch(Branch destBranch) {
		this.destBranch = destBranch;
	}

	public CMF getShipper() {
		return shipper;
	}

	public void setShipper(CMF shipper) {
		this.shipper = shipper;
	}

	public CMF getConsignee() {
		return consignee;
	}

	public void setConsignee(CMF consignee) {
		this.consignee = consignee;
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

	public List<ShipmentContainer> getShipmentContainers() {
		return shipmentContainers;
	}

	public void setShipmentContainers(List<ShipmentContainer> shipmentContainers) {
		this.shipmentContainers = shipmentContainers;
	}

	public Date getEtd() {
		return etd;
	}

	public void setEtd(Date etd) {
		this.etd = etd;
	}
	
	public void setEtd(String dateStr) {
		try {
			this.etd = DateUtils.toValidatedDate(StringUtils.upper(dateStr));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + dateStr + "] as the shipment ETD is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEta() {
		return eta;
	}

	public void setEta(Date eta) {
		this.eta = eta;
	}
	
	public void setEta(String dateStr) {
		try {
			this.eta = DateUtils.toValidatedDate(StringUtils.upper(dateStr));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + dateStr + "] as the shipment ETA is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getAtd() {
		return atd;
	}

	public void setAtd(Date atd) {
		this.atd = atd;
	}
	
	public void setAtd(String dateStr) {
		try {
			this.atd = DateUtils.toValidatedDate(StringUtils.upper(dateStr));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + dateStr + "] as the shipment ATD is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getAta() {
		return ata;
	}

	public void setAta(Date ata) {
		this.ata = ata;
	}
	
	public void setAta(String dateStr) {
		try {
			this.ata = DateUtils.toValidatedDate(StringUtils.upper(dateStr));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + dateStr + "] as the shipment ATA is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public void setCreateDate(String dateStr) {
		try {
			this.createDate = DateUtils.toValidatedDate(StringUtils.upper(dateStr));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + dateStr + "] as the shipment create date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	
	public void setBookingDate(String dateStr) {		
		try {
			this.bookingDate = DateUtils.toValidatedDate(StringUtils.upper(dateStr));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + dateStr + "] as the shipment booking date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	public void setReceivedDate(String dateStr) {
		try {
			this.receivedDate = DateUtils.toValidatedDate(StringUtils.upper(dateStr));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + dateStr + "] as the shipment received date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public void setDeliveryDate(String dateStr) {
		try {
			this.deliveryDate = DateUtils.toValidatedDate(StringUtils.upper(dateStr));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + dateStr + "] as the shipment delivery date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getRequiredDeliveryDate() {
		return requiredDeliveryDate;
	}

	public void setRequiredDeliveryDate(Date requiredDeliveryDate) {
		this.requiredDeliveryDate = requiredDeliveryDate;
	}
	
	public void setRequiredDeliveryDate(String dateStr) {
		try {
			this.requiredDeliveryDate = DateUtils.toValidatedDate(StringUtils.upper(dateStr));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + dateStr + "] as the shipment required delivery date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public boolean isEmpty() {
		return false;     
	}
	
	public BigDecimal getNumberOfPieces() {
		return numberOfPieces;
	}

	public void setNumberOfPieces(BigDecimal numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}
	
	public void setNumberOfPieces(String numberOfPieces) {
		try {
			if(!StringUtils.isEmpty(numberOfPieces) && numberOfPieces.equals("-")){
				numberOfPieces = "0";
			}
			Long numberOfPiecesLong = StringUtils.asLong(numberOfPieces);
			this.numberOfPieces 
				= (numberOfPiecesLong == null) ? null : BigDecimal.valueOf(numberOfPiecesLong);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + numberOfPieces + "] as the number of pieces is invalid. " + 
					"The system expects a valid number.");
		}
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	public void setWeight(String weight) {
		try {
			Double weightDouble = StringUtils.asDouble(weight);
			this.weight = (weightDouble == null) 
					? null : BigDecimal.valueOf(weightDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + weight + "] as the weight is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getChargeableWeight() {
		return chargeableWeight;
	}

	public void setChargeableWeight(BigDecimal chargeableWeight) {
		this.chargeableWeight = chargeableWeight;
	}
	
	public void setChargeableWeight(String chargeableWeight) {
		try {
			Double chargeableWeightDouble = StringUtils.asDouble(chargeableWeight);
			this.chargeableWeight = (chargeableWeightDouble == null) 
					? null : BigDecimal.valueOf(chargeableWeightDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + chargeableWeight + "] as the chargeable weight is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public void setVolume(BigDecimal volume) {
		this.volume = volume;
	}
	
	public void setVolume(String volume) {
		try {
			Double volumeDouble = StringUtils.asDouble(volume);
			this.volume = (volumeDouble == null)
					? null : BigDecimal.valueOf(volumeDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + volume + "] as the volume is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}
	
	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		if (!StringUtils.isEmpty(shipmentType)
				&& shipmentType.length() > 3)
			throw new RuntimeException("Value [" + shipmentType + "] as the shipment type is invalid. " + 
					"The system expects a valid type of no longer than 3 characters. Example: 'LCL' or 'FCL'.");
		this.shipmentType = shipmentType;
	}
	
	public String getShipmentTerms() {
		return shipmentTerms;
	}

	public void setShipmentTerms(String shipmentTerms) {
		if (!StringUtils.isEmpty(shipmentTerms)
				&& shipmentTerms.length() > 3)
			throw new RuntimeException("Value [" + shipmentTerms + "] as the shipment terms is invalid. " + 
					"The system expects a valid type of no longer than 3 characters.");
		this.shipmentTerms = shipmentTerms;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}
	
	public Package getPkg() {
		return pkg;
	}

	public void setPkg(Package pkg) {
		this.pkg = pkg;
	}
	
	public String getLaneId() {
		return laneId;
	}

	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}
	
	public String getMastBillNo() {
		return mastBillNo;
	}

	public void setMastBillNo(String mastBillNo) {
		this.mastBillNo = mastBillNo;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getVoyageName() {
		return voyageName;
	}

	public void setVoyageName(String voyageName) {
		this.voyageName = voyageName;
	}

	public String getVoyageNumber() {
		return voyageNumber;
	}

	public void setVoyageNumber(String voyageNumber) {
		this.voyageNumber = voyageNumber;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	public String getShipperAlias() {
		return shipperAlias;
	}

	public void setShipperAlias(String shipperAlias) {
		this.shipperAlias = shipperAlias;
	}

	public String getConsingeeAlias() {
		return consingeeAlias;
	}

	public void setConsingeeAlias(String consingeeAlias) {
		this.consingeeAlias = consingeeAlias;
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

	public String getOrigCountryCd() {
		return origCountryCd;
	}

	public void setOrigCountryCd(String origCountryCd) {
		this.origCountryCd = origCountryCd;
	}

	public String getDestCountryCd() {
		return destCountryCd;
	}

	public void setDestCountryCd(String destCountryCd) {
		this.destCountryCd = destCountryCd;
	}

	public String getOrigLocCd() {
		return origLocCd;
	}

	public void setOrigLocCd(String origLocCd) {
		this.origLocCd = origLocCd;
	}

	public String getDestLocCd() {
		return destLocCd;
	}

	public void setDestLocCd(String destLocCd) {
		this.destLocCd = destLocCd;
	}

	public void setInCustomerDate(Date inCustomerDate) {
		this.inCustomerDate = inCustomerDate;
	}
	
	public void setInCustomerDate(String inCustomerDate) {		
		try {
			this.inCustomerDate = DateUtils.toValidatedDate(StringUtils.upper(inCustomerDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + inCustomerDate + "] as the Out Customer Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getInCustomerDate() {
		return inCustomerDate;
	}

	public void setOutCustomerDate(Date outCustomerDate) {
		this.outCustomerDate = outCustomerDate;
	}
	
	public void setOutCustomerDate(String outCustomerDate) {		
		try {
			this.outCustomerDate = DateUtils.toValidatedDate(StringUtils.upper(outCustomerDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + outCustomerDate + "] as the Out Customer Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getOutCustomerDate() {
		return outCustomerDate;
	}

	public BigDecimal getDryBulkCntrs20Ft() {
		return dryBulkCntrs20Ft;
	}

	public void setDryBulkCntrs20Ft(BigDecimal dryBulkCntrs20Ft) {
		this.dryBulkCntrs20Ft = dryBulkCntrs20Ft;
	}
	
	public void setDryBulkCntrs20Ft(String dryBulkCntrs20Ft) {
		try {
			Double dryBulkCntrs20FtDouble = StringUtils.asDouble(dryBulkCntrs20Ft);
			this.dryBulkCntrs20Ft = (dryBulkCntrs20FtDouble == null) 
					? null : BigDecimal.valueOf(dryBulkCntrs20FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + dryBulkCntrs20Ft + "] as the 20 Ft Dry Bulk Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getDryCntrs20Ft() {
		return dryCntrs20Ft;
	}
	
	public void setDryCntrs20Ft(BigDecimal dryCntrs20Ft) {
		this.dryCntrs20Ft = dryCntrs20Ft;
	}
	
	public void setDryCntrs20Ft(String dryCntrs20Ft) {
		try {
			Double dryCntrs20FtDouble = StringUtils.asDouble(dryCntrs20Ft);
			this.dryCntrs20Ft = (dryCntrs20FtDouble == null) 
					? null : BigDecimal.valueOf(dryCntrs20FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + dryCntrs20Ft + "] as the 20 Ft Dry Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getReeferCntrs20Ft() {
		return reeferCntrs20Ft;
	}

	public void setReeferCntrs20Ft(BigDecimal reeferCntrs20Ft) {
		this.reeferCntrs20Ft = reeferCntrs20Ft;
	}
	
	public void setReeferCntrs20Ft(String reeferCntrs20Ft) {
		try {
			Double reeferCntrs20FtDouble = StringUtils.asDouble(reeferCntrs20Ft);
			this.reeferCntrs20Ft = (reeferCntrs20FtDouble == null) 
					? null : BigDecimal.valueOf(reeferCntrs20FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + reeferCntrs20Ft + "] as the 20 Ft Reefer Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getTankCntrs20Ft() {
		return tankCntrs20Ft;
	}

	public void setTankCntrs20Ft(BigDecimal tankCntrs20Ft) {
		this.tankCntrs20Ft = tankCntrs20Ft;
	}
	
	public void setTankCntrs20Ft(String tankCntrs20Ft) {
		try {
			Double tankCntrs20FtDouble = StringUtils.asDouble(tankCntrs20Ft);
			this.tankCntrs20Ft = (tankCntrs20FtDouble == null) 
					? null : BigDecimal.valueOf(tankCntrs20FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + tankCntrs20Ft + "] as the 20 Ft Tank Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getFlatCntrs20Ft() {
		return flatCntrs20Ft;
	}

	public void setFlatCntrs20Ft(BigDecimal flatCntrs20Ft) {
		this.flatCntrs20Ft = flatCntrs20Ft;
	}
	
	public void setFlatCntrs20Ft(String flatCntrs20Ft) {
		try {
			Double tankCntrs20FtDouble = StringUtils.asDouble(flatCntrs20Ft);
			this.flatCntrs20Ft = (tankCntrs20FtDouble == null) 
					? null : BigDecimal.valueOf(tankCntrs20FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + flatCntrs20Ft + "] as the 20 Ft Flat Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getHighCubeDryCntrs20Ft() {
		return highCubeDryCntrs20Ft;
	}

	public void setHighCubeDryCntrs20Ft(BigDecimal highCubeDryCntrs20Ft) {
		this.highCubeDryCntrs20Ft = highCubeDryCntrs20Ft;
	}
	
	public void setHighCubeDryCntrs20Ft(String highCubeDryCntrs20Ft) {
		try {
			Double highCubeDryCntrs20FtDouble = StringUtils.asDouble(highCubeDryCntrs20Ft);
			this.highCubeDryCntrs20Ft = (highCubeDryCntrs20FtDouble == null) 
					? null : BigDecimal.valueOf(highCubeDryCntrs20FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + highCubeDryCntrs20Ft + "] as the 20 Ft High Cube Dry Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getInsulatedCntrs20Ft() {
		return insulatedCntrs20Ft;
	}

	public void setInsulatedCntrs20Ft(BigDecimal insulatedCntrs20Ft) {
		this.insulatedCntrs20Ft = insulatedCntrs20Ft;
	}
	
	public void setInsulatedCntrs20Ft(String insulatedCntrs20Ft) {
		try {
			Double insulatedCntrs20FtDouble = StringUtils.asDouble(insulatedCntrs20Ft);
			this.insulatedCntrs20Ft = (insulatedCntrs20FtDouble == null) 
					? null : BigDecimal.valueOf(insulatedCntrs20FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + insulatedCntrs20Ft + "] as the 20 Ft Insulated Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getOpenTopCntrs20Ft() {
		return openTopCntrs20Ft;
	}

	public void setOpenTopCntrs20Ft(BigDecimal openTopCntrs20Ft) {
		this.openTopCntrs20Ft = openTopCntrs20Ft;
	}
	
	public void setOpenTopCntrs20Ft(String openTopCntrs20Ft) {
		try {
			Double openTopCntrs20FtDouble = StringUtils.asDouble(openTopCntrs20Ft);
			this.openTopCntrs20Ft = (openTopCntrs20FtDouble == null) 
					? null : BigDecimal.valueOf(openTopCntrs20FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + openTopCntrs20Ft + "] as the 20 Ft Open Top Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getDryCntrs40Ft() {
		return dryCntrs40Ft;
	}

	public void setDryCntrs40Ft(BigDecimal dryCntrs40Ft) {
		this.dryCntrs40Ft = dryCntrs40Ft;
	}
	
	public void setDryCntrs40Ft(String dryCntrs40Ft) {
		try {
			Double dryCntrs40FtDouble = StringUtils.asDouble(dryCntrs40Ft);
			this.dryCntrs40Ft = (dryCntrs40FtDouble == null) 
					? null : BigDecimal.valueOf(dryCntrs40FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + dryCntrs40Ft + "] as the 40 Ft Dry Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getHighCubeDryCntrs40Ft() {
		return highCubeDryCntrs40Ft;
	}

	public void setHighCubeDryCntrs40Ft(BigDecimal highCubeDryCntrs40Ft) {
		this.highCubeDryCntrs40Ft = highCubeDryCntrs40Ft;
	}
	
	public void setHighCubeDryCntrs40Ft(String highCubeDryCntrs40Ft) {
		try {
			Double highCubeDryCntrs40FtDouble = StringUtils.asDouble(highCubeDryCntrs40Ft);
			this.highCubeDryCntrs40Ft = (highCubeDryCntrs40FtDouble == null) 
					? null : BigDecimal.valueOf(highCubeDryCntrs40FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + highCubeDryCntrs40Ft + "] as the 40 Ft High Cube Dry Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getHighCubeReeferCntrs40Ft() {
		return highCubeReeferCntrs40Ft;
	}

	public void setHighCubeReeferCntrs40Ft(BigDecimal highCubeReeferCntrs40Ft) {
		this.highCubeReeferCntrs40Ft = highCubeReeferCntrs40Ft;
	}
	
	public void setHighCubeReeferCntrs40Ft(String highCubeReeferCntrs40Ft) {
		try {
			Double highCubeReeferCntrs40FtDouble = StringUtils.asDouble(highCubeReeferCntrs40Ft);
			this.highCubeReeferCntrs40Ft = (highCubeReeferCntrs40FtDouble == null) 
					? null : BigDecimal.valueOf(highCubeReeferCntrs40FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + highCubeReeferCntrs40Ft + "] as the 40 Ft High Cube Reefer Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getReeferCntrs40Ft() {
		return reeferCntrs40Ft;
	}

	public void setReeferCntrs40Ft(BigDecimal reeferCntrs40Ft) {
		this.reeferCntrs40Ft = reeferCntrs40Ft;
	}
	
	public void setReeferCntrs40Ft(String reeferCntrs40Ft) {
		try {
			Double reeferCntrs40FtDouble = StringUtils.asDouble(reeferCntrs40Ft);
			this.reeferCntrs40Ft = (reeferCntrs40FtDouble == null) 
					? null : BigDecimal.valueOf(reeferCntrs40FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + reeferCntrs40Ft + "] as the 40 Ft Reefer Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getHighCubeDryCntrs45Ft() {
		return highCubeDryCntrs45Ft;
	}

	public void setHighCubeDryCntrs45Ft(BigDecimal highCubeDryCntrs45Ft) {
		this.highCubeDryCntrs45Ft = highCubeDryCntrs45Ft;
	}
	
	public void setHighCubeDryCntrs45Ft(String highCubeDryCntrs45Ft) {
		try {
			Double highCubeDryCntrs45FtDouble = StringUtils.asDouble(highCubeDryCntrs45Ft);
			this.highCubeDryCntrs45Ft = (highCubeDryCntrs45FtDouble == null) 
					? null : BigDecimal.valueOf(highCubeDryCntrs45FtDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + highCubeDryCntrs45Ft + "] as the 45 Ft High Cube Dry Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getOtherCntrs() {
		return otherCntrs;
	}

	public void setOtherCntrs(BigDecimal otherCntrs) {
		this.otherCntrs = otherCntrs;
	}
	
	public void setOtherCntrs(String otherCntrs) {
		try {
			Double otherCntrsDouble = StringUtils.asDouble(otherCntrs);
			this.otherCntrs = (otherCntrsDouble == null) 
					? null : BigDecimal.valueOf(otherCntrsDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + otherCntrs + "] as the Other Cntrs is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public void setHbNumber(String hbNumber) {
		this.hbNumber = hbNumber;
	}

	public String getHbNumber() {
		return hbNumber;
	}

	public void setCustomsNumber(String customsNumber) {
		this.customsNumber = customsNumber;
	}

	public String getCustomsNumber() {
		return customsNumber;
	}
	

	public String getUserDefined4() {
		return userDefined4;
	}

	public void setUserDefined4(String userDefined4) {
		this.userDefined4 = userDefined4;
	}

	public String getUserDefined5() {
		return userDefined5;
	}

	public void setUserDefined5(String userDefined5) {
		this.userDefined5 = userDefined5;
	}

	public String getUserDefined6() {
		return userDefined6;
	}

	public void setUserDefined6(String userDefined6) {
		this.userDefined6 = userDefined6;
	}

	public String getUserDefined7() {
		return userDefined7;
	}

	public void setUserDefined7(String userDefined7) {
		this.userDefined7 = userDefined7;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return comments;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setGoodDescription(String goodDescription) {
		this.goodDescription = goodDescription;
	}

	public String getGoodDescription() {
		return goodDescription;
	}

	public void setRefTypes(List<RefType> refTypes) {
		RefTypes = refTypes;
	}

	public List<RefType> getRefTypes() {
		return RefTypes;
	}
}