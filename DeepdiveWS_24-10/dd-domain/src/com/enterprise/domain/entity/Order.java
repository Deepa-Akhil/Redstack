package com.enterprise.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.enums.ModeTypes;
import com.enterprise.common.util.DateUtils;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "ORDER", schema = "GLOBAL", uniqueConstraints =
    @UniqueConstraint(columnNames = {"ORDER_NUMBER", "PACKAGE_ID"})
)
@org.hibernate.annotations.Table(
	appliesTo = "ORDER", 
	indexes = { 
		@Index(name = "order_id_ix", columnNames = { "ID" } )
	}
)
public class Order implements IEntity, Serializable {

	private static final long serialVersionUID = 4940743599809348810L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_SEQ")
	@SequenceGenerator(name = "ORDER_SEQ", sequenceName = "GLOBAL.ORDER_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE", nullable = false, updatable=false)
	private Date creationDate = new Date();
	
    @Column(name = "ORDER_RAISED_DATE")
	private Date oredrRaisedOn ;

	@Column(name = "ORDER_NUMBER", nullable = false, length = 16)
	private String orderNumber;
	
	@Column(name = "ORDER_TYPE")
	private String orderType;
	
	@Column(name = "BUYER")
	private String buyer;
	
	@Column(name = "SUPPLIER")
	private String supplier;
	
	@Column(name = "END_CONSUMER")
	private String endConsumer;
	
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
	
	@Enumerated(EnumType.STRING)
	@Column(name="MODE", length = 1)
	private ModeTypes mode;
	
	@Column(name = "ORDER_VALUE")
	private String orderValue;
	
	@OneToMany(mappedBy = "orderData", fetch = FetchType.LAZY)
	private List<OrderLine> orderLines = new ArrayList<OrderLine>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CURRENCY_ID", referencedColumnName = "ID", nullable = true)
	})
	private Currency currency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "PACKAGE_ID", referencedColumnName = "ID", nullable = true)
	})
	private Package pkg;
	

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		if(StringUtils.isEmpty(orderNumber)){
			throw new RuntimeException("Value [" + orderNumber + "] as the Order Number is empty. ");
		}
		this.orderNumber = orderNumber;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		if(StringUtils.isEmpty(buyer)){
			throw new RuntimeException("Value [" + buyer + "] as the Buyer is empty. ");
		}
		this.buyer = buyer;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getEndConsumer() {
		return endConsumer;
	}

	public void setEndConsumer(String endConsumer) {
		this.endConsumer = endConsumer;
	}

	public Location getOrigPort() {
		return origPort;
	}

	public void setOrigPort(Location origPort) {
		this.origPort = origPort;
	}
	
	public void setOrigPort(IPort destPort) {
		this.origPort = (Location)destPort;
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

	public ModeTypes getMode() {
		return mode;
	}

	public void setMode(ModeTypes mode) {
		this.mode = mode;
	}
	
	public void setMode(String mode) {
		ModeTypes modeType = null;
		try {
			modeType = ModeTypes.valueOf(mode);
			this.mode = modeType;
		} catch (Throwable e) {
			String correctedMode = mode;
			if(!StringUtils.isEmpty(mode) && mode.length() > 1){
				correctedMode = mode.substring(0, 1).toUpperCase() + mode.substring(1).toLowerCase();
			}
			modeType = ModeTypes.valueOfByMode(correctedMode);
			if (modeType == null)
				throw new RuntimeException("Value [" + mode + "] as the rate mode is invalid. The system expects " + 
						"a 1 character value of either 'A','O' or 'M'.");
			this.mode = modeType;
		}
	}

	public String getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(String orderValue) {
		this.orderValue = orderValue;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Package getPkg() {
		return pkg;
	}

	public void setPkg(Package pkg) {
		this.pkg = pkg;
	}
	
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	

	public Date getOredrRaisedOn() {
		return oredrRaisedOn;
	}

	public void setOredrRaisedOn(Date oredrRaisedOn) {
		this.oredrRaisedOn = oredrRaisedOn;
	}
	
	public void setOredrRaisedOn(String oredrRaisedOn) {
		try {
			this.oredrRaisedOn = DateUtils.toValidatedDate(StringUtils.upper(oredrRaisedOn));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + oredrRaisedOn + "] as the Order Raised date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public boolean isEmpty() {
		return false;
	}
	
	public static Order load(String orderNumber, Session session, long packageId) {
		Criteria criteria = session.createCriteria(Order.class)
			.add(Restrictions.eq("orderNumber", orderNumber));
		if(packageId != 0L)
			criteria.add(Restrictions.eq("pkg.id", packageId));
		Order order = (Order)criteria.uniqueResult();
		return order;
	}
	
	public static void setOrderLineClearOld(Set<Long> orderIds, Set<Long> ids, Session session) {
		if(ids!=null && orderIds!=null && !ids.isEmpty() && !orderIds.isEmpty()) {
			String hql = "delete from OrderLine "
					+ "WHERE id not in(:id) and orderData.id in(:orderIds)";
			Query query = session.createQuery(hql);
			query.setParameterList("id", ids);
			query.setParameterList("orderIds", orderIds);
			query.executeUpdate();
		}
	}
	
	public static void setOrderExpedLineClearOld(Set<Long> orderLineIds, Set<Long> ids, Session session) {
		if(ids!=null && orderLineIds!=null && !ids.isEmpty() && !orderLineIds.isEmpty()) {
			String hql = "delete from OrderExpedLine "
					+ "WHERE id not in(:id) and orderLine.id in(:orderLineIds)";
			Query query = session.createQuery(hql);
			query.setParameterList("id", ids);
			query.setParameterList("orderLineIds", orderLineIds);
			query.executeUpdate();
		}
	}

}
