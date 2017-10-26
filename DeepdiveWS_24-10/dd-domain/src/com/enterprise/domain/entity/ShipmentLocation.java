package com.enterprise.domain.entity;

import java.io.Serializable;

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Index;

import com.enterprise.common.entity.IEntity;

@Entity
@Table(name = "SHIPMENT_LOCATION", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "SHIPMENT_LOCATION", 
	indexes = { 
		@Index(name = "ship_loc_id_ix", columnNames = { "ID" } )
	}
)
public class ShipmentLocation implements IEntity, Serializable {
	
	private static final long serialVersionUID = -4833130999457896075L;
	
	/** ID decimal(19) PRIMARY KEY NOT NULL, */
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SHIPMENT_SEQ")
	@SequenceGenerator(name = "SHIPMENT_SEQ", sequenceName = "GLOBAL.SHIPMENT_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	/** SHIPMENT_ID decimal(19) NOT NULL, */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( {
        @JoinColumn(name="SHIPMENT_ID", referencedColumnName = "ID", nullable = false)
	} )
	@Cascade({CascadeType.SAVE_UPDATE})
	private Shipment shipment;
	
	/** ORIG_CITY_ID decimal(19) NOT NULL, */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( {
        @JoinColumn(name="ORIG_CITY_ID", referencedColumnName = "ID", nullable = false)
	} )
	@Cascade({CascadeType.SAVE_UPDATE})
	private City origCity;
	
	/** DEST_CITY_ID decimal(19) NOT NULL, */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns( {
        @JoinColumn(name="DEST_CITY_ID", referencedColumnName = "ID", nullable = false)
	} )
	@Cascade({CascadeType.SAVE_UPDATE})
	private City destCity;
	

	public ShipmentLocation() {/* no implementation */}
	
	public ShipmentLocation(Shipment shipment) {
		this.shipment = shipment;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
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

	public boolean isEmpty() {
		return false;
	}
}