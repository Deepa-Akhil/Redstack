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
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "SHIPMENT_CONTAINER", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"ID", "UUID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "SHIPMENT_CONTAINER", 
	indexes = { 
		@Index(name = "shipcont_id_ix", columnNames = { "ID" } ),
		@Index(name = "shipcont_uuid_ix", columnNames = { "UUID" } )
	}
)
public class ShipmentContainer implements IEntity, Serializable {
	
	private static final long serialVersionUID = 8844440975542816655L;
	
	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SHIP_CONT_SEQ")
	@SequenceGenerator(name = "SHIP_CONT_SEQ", sequenceName = "GLOBAL.SHIP_CONT_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "UUID", nullable = false, length = 48)
	private String uuid;
	
	/** SHIPMENT_ID decimal(19) NOT NULL */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SHIPMENT_ID", referencedColumnName = "ID", nullable = false)
	})
	private Shipment shipment;
	
	/** CONTAINER_ID decimal(19) NOT NULL */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CONTAINER_ID", referencedColumnName = "ID", nullable = false)
	})
	private Container container;
	
	@Column(name = "NO_PIECES", nullable = true, precision = 10, scale = 0)
	private BigDecimal numberOfPieces;
	
	@Column(name = "WEIGHT", nullable = true, precision = 13, scale = 3)
	private BigDecimal weight;
	
	@Column(name = "CHARGEABLE_WEIGHT", nullable = true, precision = 13, scale = 3)
	private BigDecimal chargeableWeight;
	
	@Column(name = "VOLUME", nullable = true, precision = 13, scale = 3)
	private BigDecimal volume;
	
	@Column(name = "LANE_ID", nullable = true)
	private String laneId;
	
	/*@Column(name="SHIPMENT_TYPE", nullable = true, length = 3)
	private String shipmentType;*/
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		if (!StringUtils.isEmpty(uuid)
				&& uuid.length() > 48)
			throw new RuntimeException("Value [" + uuid + "] as the uuid is invalid. " + 
					"The system expects a valid uuid no longer than 48 characters.");
		this.uuid = uuid;
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
	
	public BigDecimal getNumberOfPieces() {
		return numberOfPieces;
	}

	public void setNumberOfPieces(BigDecimal numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}
	
	public void setNumberOfPieces(String numberOfPieces) {
		try {
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

	/*public String getShipmentType() {
		return shipmentType;
	}
	
	public void setShipmentType(String shipmentType) {
		if (!StringUtils.isEmpty(shipmentType)
				&& shipmentType.length() > 3)
			throw new RuntimeException("Value [" + shipmentType + "] as the shipment type is invalid. " + 
					"The system expects a valid type of no longer than 3 characters. Example: 'LCL' or 'FCL'.");
		this.shipmentType = shipmentType;
	}*/

	public boolean isEmpty() {
		return false;
	}

	public String getLaneId() {
		return laneId;
	}

	public void setLaneId(String laneId) {
		this.laneId = laneId;
	}
}