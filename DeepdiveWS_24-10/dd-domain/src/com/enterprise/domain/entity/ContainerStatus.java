package com.enterprise.domain.entity;

import java.io.Serializable;
import java.util.Date;
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
import com.enterprise.common.util.DateUtils;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "CONTAINER_MOVEMENT", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"DEST_PORT", "CONTAINER_NUMBER", "ETA_DATE", "PACKAGE_ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "CONTAINER_MOVEMENT", 
	indexes = { 
		@Index(name = "containerMapping_id_index", columnNames = { "ID" }),
		@Index(name = "FKFDFHGH55JHHU9975F1_idx", columnNames = { "PACKAGE_ID" }),
		@Index(name = "FKFDF619XSDDYY6685F1_idx", columnNames = { "SHIPMENT_ID" }),
		@Index(name = "FKSSSSS4445677D975F1_idx", columnNames = { "CONTAINER_ID" })
	}
)
public class ContainerStatus implements IEntity, Serializable {

	private static final long serialVersionUID = 5892504562556054871L;

	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CONTAINER_MOVEMENT_SEQ")
	@SequenceGenerator(name = "CONTAINER_MOVEMENT_SEQ", sequenceName = "GLOBAL.CONTAINER_MOVEMENT_SEQ")
	@Column(name = "ID", nullable = false)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	private long id;
	
	@Column(name = "JOB_NUMBER")
	private String jobNumber;
	
	@Column(name = "VESSEL")
	private String vessel;
	
	@Column(name = "VOYAGE")
	private String voyage;
	
	@Column(name = "DEST_PORT")
	private String destPort;
	
	@Column(name = "CONTAINER_NUMBER", nullable = false)
	private String containerNumber;
	
	@Column(name = "SHIPMENT_TYPE")
	private String shipmentType;
	
	@Column(name = "CONTAINER_SIZE")
	private String containerSize;
	
	@Column(name = "WEIGHT")
	private String wieght;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ETA_DATE")
	private Date etaDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "WHARF_AVAILABLE_DATE")
	private Date warfAvailDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "WHARF_STORAGE_DATE")
	private Date wharfStoreDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "YARD_AVALIABLE_DATE")
	private Date yardAvailableDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ESTIMATED_DELIVERY_DATE")
	private Date estDelDate;
	
	@Column(name = "ESTIMATED_DELIVERY_TIME")
	private String estDelTime;
	
	@Column(name = "DELIVERY_LOCATION")
	private String deliveryLocation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DELIVERY_DATE")
	private Date deliveryDate;
	
	@Column(name = "DETENTION_DATE")
	private Date detentionDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EMPTY_RET_TAR_DATE")
	private Date emptyRetTargetDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EMPTY_RETURN_DATE")
	private Date emptyRetDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEHIRE_DATE")
	private Date dehireDate;
	
	@Column(name = "SHIPMENT_NUMBER")
	private String shipmentNumber;
	
	@Column(name = "USER_DEFINED1")
	private String userDefined1;
	
	@Column(name = "USER_DEFINED2")
	private String userDefined2;
	
	@Column(name = "USER_DEFINED3")
	private String userDefined3;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CONTAINER_ID", referencedColumnName = "ID", nullable = true)
	})
	private Container container;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON")
	private Date updatedOn = new Date();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getVessel() {
		return vessel;
	}

	public void setVessel(String vessel) {
		this.vessel = vessel;
	}

	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	public String getDestPort() {
		return destPort;
	}

	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}

	public String getContainerNumber() {
		return containerNumber;
	}

	public void setContainerNumber(String containerNumber) {
		this.containerNumber = containerNumber;
	}

	public String getShipmentType() {
		return shipmentType;
	}

	public void setShipmentType(String shipmentType) {
		this.shipmentType = shipmentType;
	}

	public String getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}

	public String getWieght() {
		return wieght;
	}

	public void setWieght(String wieght) {
		this.wieght = wieght;
	}

	public Date getEtaDate() {
		return etaDate;
	}

	public void setEtaDate(Date etaDate) {
		this.etaDate = etaDate;
	}
	
	public void setEtaDate(String etaDate) {		
		try {
			this.etaDate = DateUtils.toValidatedDate(StringUtils.upper(etaDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + etaDate + "] as the ETA Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getWarfAvailDate() {
		return warfAvailDate;
	}

	public void setWarfAvailDate(Date warfAvailDate) {
		this.warfAvailDate = warfAvailDate;
	}
	
	public void setWarfAvailDate(String warfAvailDate) {		
		try {
			this.warfAvailDate = DateUtils.toValidatedDate(StringUtils.upper(warfAvailDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + warfAvailDate + "] as the Wharf Available Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getWharfStoreDate() {
		return wharfStoreDate;
	}

	public void setWharfStoreDate(Date wharfStoreDate) {
		this.wharfStoreDate = wharfStoreDate;
	}
	
	public void setWharfStoreDate(String wharfStoreDate) {		
		try {
			this.wharfStoreDate = DateUtils.toValidatedDate(StringUtils.upper(wharfStoreDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + wharfStoreDate + "] as the Wharf Storage Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEstDelDate() {
		return estDelDate;
	}

	public void setEstDelDate(Date estDelDate) {
		this.estDelDate = estDelDate;
	}
	
	public void setEstDelDate(String estDelDate) {		
		try {
			this.estDelDate = DateUtils.toValidatedDate(StringUtils.upper(estDelDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + estDelDate + "] as the Estimated Delivery Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public String getEstDelTime() {
		return estDelTime;
	}

	public void setEstDelTime(String estDelTime) {
		this.estDelTime = estDelTime;
	}

	public String getDeliveryLocation() {
		return deliveryLocation;
	}

	public void setDeliveryLocation(String deliveryLocation) {
		this.deliveryLocation = deliveryLocation;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	
	public void setDeliveryDate(String deliveryDate) {		
		try {
			this.deliveryDate = DateUtils.toValidatedDate(StringUtils.upper(deliveryDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + deliveryDate + "] as the Delivery Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getDetentionDate() {
		return detentionDate;
	}

	public void setDetentionDate(Date detentionDate) {
		this.detentionDate = detentionDate;
	}
	
	public void setDetentionDate(String detentionDate) {		
		try {
			this.detentionDate = DateUtils.toValidatedDate(StringUtils.upper(detentionDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + detentionDate + "] as the Detention Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEmptyRetTargetDate() {
		return emptyRetTargetDate;
	}

	public void setEmptyRetTargetDate(Date emptyRetTargetDate) {
		this.emptyRetTargetDate = emptyRetTargetDate;
	}
	
	public void setEmptyRetTargetDate(String emptyRetTargetDate) {		
		try {
			this.emptyRetTargetDate = DateUtils.toValidatedDate(StringUtils.upper(emptyRetTargetDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + emptyRetTargetDate + "] as the Empty Return Target Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEmptyRetDate() {
		return emptyRetDate;
	}

	public void setEmptyRetDate(Date emptyRetDate) {
		this.emptyRetDate = emptyRetDate;
	}
	
	public void setEmptyRetDate(String emptyRetDate) {		
		try {
			this.emptyRetDate = DateUtils.toValidatedDate(StringUtils.upper(emptyRetDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + emptyRetDate + "] as the Empty Return Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}
	
	public void setDehireDate(Date dehireDate) {
		this.dehireDate = dehireDate;
	}

	public Date getDehireDate() {
		return dehireDate;
	}
	
	public void setDehireDate(String dehireDate) {		
		try {
			this.dehireDate = DateUtils.toValidatedDate(StringUtils.upper(dehireDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + dehireDate + "] as the Dehire Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}
	
	public void setYardAvailableDate(Date yardAvailableDate) {
		this.yardAvailableDate = yardAvailableDate;
	}

	public Date getYardAvailableDate() {
		return yardAvailableDate;
	}
	
	public void setYardAvailableDate(String yardAvailableDate) {		
		try {
			this.yardAvailableDate = DateUtils.toValidatedDate(StringUtils.upper(yardAvailableDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + yardAvailableDate + "] as the Yard Available Date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
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

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static ContainerStatus load(String conatainerNumber, String destPort, Session session, long packageId) {
		Criteria criteria = session.createCriteria(ContainerStatus.class);
		/*if(StringUtils.isEmpty(destPort))
			criteria.add(Restrictions.isNull("destPort"));
		else 
			criteria.add(Restrictions.eq("destPort", destPort));*/
		criteria.add(Restrictions.eq("containerNumber", conatainerNumber));
		criteria.add(Restrictions.eq("pkg.id", packageId));
		List<ContainerStatus> containerStatuss = criteria.list();
		ContainerStatus containerStatus = null;
		if(null!=containerStatuss && !containerStatuss.isEmpty())
			containerStatus = (ContainerStatus)containerStatuss.get(0);
		return containerStatus;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}
}