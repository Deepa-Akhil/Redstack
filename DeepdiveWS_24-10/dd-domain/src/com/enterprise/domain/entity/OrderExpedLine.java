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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "ORDER_EXPED_LINE", schema = "GLOBAL"/*, uniqueConstraints =
		@UniqueConstraint(columnNames = {"SHIPMENT_NUMBER", "ORDER_LINE_ID"})*/)
@org.hibernate.annotations.Table(
	appliesTo = "ORDER_EXPED_LINE", 
	indexes = { 
		@Index(name = "order_exped_line_id_ix", columnNames = { "ID" } )
	}
)
public class OrderExpedLine implements IEntity, Serializable {

	private static final long serialVersionUID = -5205403010703803401L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_EXPED_LINE_SEQ")
	@SequenceGenerator(name = "ORDER_EXPED_LINE_SEQ", sequenceName = "GLOBAL.ORDER_EXPED_LINE_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	@Column(name = "QTY_BOOKED", nullable = true, precision = 10, scale = 0)
	private BigDecimal qtyBooked;
	
	@Column(name = "QTY_RECEIVED", nullable = true, precision = 10, scale = 0)
	private BigDecimal qtyReceived;
	
	@Column(name = "QTY_SHIPPED", nullable = true, precision = 10, scale = 0)
	private BigDecimal qtyShipped;
	
	@Column(name = "CARTONS_BOOKED", nullable = true, precision = 10, scale = 0)
	private BigDecimal cartonsBooked;
	
	@Column(name = "CARTONS_RECEIVED", nullable = true, precision = 10, scale = 0)
	private BigDecimal cartonsReceived;
	
	@Column(name = "CARTONS_SHIPPED", nullable = true, precision = 10, scale = 0)
	private BigDecimal cartonsShipped;
	
	@Column(name = "CBM_BOOKED", nullable = true, precision = 10, scale = 0)
	private BigDecimal cbmBooked;
	
	@Column(name = "CBM_RECEIVED", nullable = true, precision = 10, scale = 0)
	private BigDecimal cbmReceived;
	
	@Column(name = "CBM_SHIPPED", nullable = true, precision = 10, scale = 0)
	private BigDecimal cbmShipped;
	
	@Column(name = "SHIPMENT_NUMBER", nullable = true, length = 21)
	private String shipmentNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "CONTAINER_ID", referencedColumnName = "ID", nullable = true)
	})
	private Container container;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORDER_LINE_ID", referencedColumnName = "ID", nullable = true)
	})
	private OrderLine orderLine;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "SHIPMENT_ID", referencedColumnName = "ID", nullable = true)
	})
	private Shipment shipment;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public BigDecimal getQtyBooked() {
		return qtyBooked;
	}

	public void setQtyBooked(BigDecimal qtyBooked) {
		this.qtyBooked = qtyBooked;
	}
	
	public void setQtyBooked(String qtyBooked) {
		try {
			Double localAmountDouble = StringUtils.asDouble(qtyBooked);
			this.qtyBooked = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + qtyBooked + "] as the Quantity Booked is invalid. " + 
					"The system expects a valid integer value. Example: 35");
		}
	}

	public BigDecimal getQtyReceived() {
		return qtyReceived;
	}

	public void setQtyReceived(BigDecimal qtyReceived) {
		this.qtyReceived = qtyReceived;
	}
	
	public void setQtyReceived(String qtyReceived) {
		try {
			Double localAmountDouble = StringUtils.asDouble(qtyReceived);
			this.qtyReceived = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + qtyReceived + "] as the Quantity Received is invalid. " + 
					"The system expects a valid integer value. Example: 35");
		}
	}

	public BigDecimal getQtyShipped() {
		return qtyShipped;
	}

	public void setQtyShipped(BigDecimal qtyShipped) {
		this.qtyShipped = qtyShipped;
	}
	
	public void setQtyShipped(String qtyShipped) {
		try {
			Double localAmountDouble = StringUtils.asDouble(qtyShipped);
			this.qtyShipped = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + qtyShipped + "] as the Quanity Shipped is invalid. " + 
					"The system expects a valid integer value. Example: 35");
		}
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public boolean isEmpty() {
		return false;
	}
	
	public OrderLine getOrderLine() {
		return orderLine;
	}

	public void setOrderLine(OrderLine orderLine) {
		this.orderLine = orderLine;
	}
	
	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	
	public BigDecimal getCartonsBooked() {
		return cartonsBooked;
	}

	public void setCartonsBooked(BigDecimal cartonsBooked) {
		this.cartonsBooked = cartonsBooked;
	}
	
	public void setCartonsBooked(String cartonsBooked) {
		try {
			Double localAmountDouble = StringUtils.asDouble(cartonsBooked);
			this.cartonsBooked = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + cartonsBooked + "] as the Cartons Booked is invalid. " + 
					"The system expects a valid integer value. Example: 35");
		}
	}

	public BigDecimal getCartonsReceived() {
		return cartonsReceived;
	}

	public void setCartonsReceived(BigDecimal cartonsReceived) {
		this.cartonsReceived = cartonsReceived;
	}
	
	public void setCartonsReceived(String cartonsReceived) {
		try {
			Double localAmountDouble = StringUtils.asDouble(cartonsReceived);
			this.cartonsReceived = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + cartonsReceived + "] as the Cartons Received is invalid. " + 
					"The system expects a valid integer value. Example: 35");
		}
	}

	public BigDecimal getCartonsShipped() {
		return cartonsShipped;
	}

	public void setCartonsShipped(BigDecimal cartonsShipped) {
		this.cartonsShipped = cartonsShipped;
	}
	
	public void setCartonsShipped(String cartonsShipped) {
		try {
			Double localAmountDouble = StringUtils.asDouble(cartonsShipped);
			this.cartonsShipped = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + cartonsShipped + "] as the Cartons Shipped is invalid. " + 
					"The system expects a valid integer value. Example: 35");
		}
	}

	public BigDecimal getCbmBooked() {
		return cbmBooked;
	}

	public void setCbmBooked(BigDecimal cbmBooked) {
		this.cbmBooked = cbmBooked;
	}

	public void setCbmBooked(String cbmBooked) {
		try {
			Double localAmountDouble = StringUtils.asDouble(cbmBooked);
			this.cbmBooked = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + cbmBooked + "] as the CBM booked is invalid. " + 
					"The system expects a valid integer value. Example: 35");
		}
	}
	
	public BigDecimal getCbmReceived() {
		return cbmReceived;
	}

	public void setCbmReceived(BigDecimal cbmReceived) {
		this.cbmReceived = cbmReceived;
	}
	
	public void setCbmReceived(String cbmReceived) {
		try {
			Double localAmountDouble = StringUtils.asDouble(cbmReceived);
			this.cbmReceived = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + cbmReceived + "] as the CBM Received is invalid. " + 
					"The system expects a valid integer value. Example: 35");
		}
	}

	public BigDecimal getCbmShipped() {
		return cbmShipped;
	}

	public void setCbmShipped(BigDecimal cbmShipped) {
		this.cbmShipped = cbmShipped;
	}
	
	public void setCbmShipped(String cbmShipped) {
		try {
			Double localAmountDouble = StringUtils.asDouble(cbmShipped);
			this.cbmShipped = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + cbmShipped + "] as the CBM Shipped is invalid. " + 
					"The system expects a valid integer value. Example: 35");
		}
	}
	
	public static OrderExpedLine load(String shipmentNo, Session session, long lineId) {
		Criteria criteria = session.createCriteria(OrderExpedLine.class)
			.add(Restrictions.eq("shipmentNumber", shipmentNo))
			.add(Restrictions.eq("orderLine.id", lineId));
		OrderExpedLine orderExpedLine = (OrderExpedLine)criteria.uniqueResult();
		return orderExpedLine;
	}

}
