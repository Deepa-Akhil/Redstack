package com.enterprise.domain.entity;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;

@Entity
@Table(name = "REF_TYPE", schema = "GLOBAL")
@org.hibernate.annotations.Table(
	appliesTo = "REF_TYPE", 
	indexes = { 
		@Index(name = "ref_type_id_ix", columnNames = { "ID" } )
	}
)
public class RefType implements IEntity, Serializable  {

	private static final long serialVersionUID = -31399393978666530L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "REF_TYPE_SEQ")
	@SequenceGenerator(name = "REF_TYPE_SEQ", sequenceName = "GLOBAL.REF_TYPE_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name="REF_TYPE", columnDefinition = "TINYINT", length=1, updatable = false)
    private int type = 1;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SHIPMENT_ID", referencedColumnName = "ID", nullable = false)
	})
	private Shipment shipment;
	
	@Column(name = "REF_VALUE", columnDefinition="Text")
	private String refValue;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_ON")
	private Date updatedOn = new Date();
	
	@Transient
	private String refType1;
	
	@Transient
	private String refType2;
	
	@Transient
	private String refType3;
	
	public boolean isEmpty() {
		return false;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public String getRefValue() {
		return refValue;
	}

	public void setRefValue(String refValue) {
		this.refValue = refValue;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	public String getRefType1() {
		return refType1;
	}

	public void setRefType1(String refType1) {
		setType(1);
		setRefValue(refType1);
		this.refType1 = refType1;
	}

	public String getRefType2() {
		return refType2;
	}

	public void setRefType2(String refType2) {
		setType(2);
		setRefValue(refType2);
		this.refType2 = refType2;
	}

	public String getRefType3() {
		return refType3;
	}

	public void setRefType3(String refType3) {
		setType(3);
		setRefValue(refType3);
		this.refType3 = refType3;
	}

	public static RefType load(String refValue, Session session, long shipmentId) {
		Criteria criteria = session.createCriteria(RefType.class);
		criteria.add(Restrictions.eq("shipment.id", shipmentId));
		return (RefType)criteria.uniqueResult();
	}
}
