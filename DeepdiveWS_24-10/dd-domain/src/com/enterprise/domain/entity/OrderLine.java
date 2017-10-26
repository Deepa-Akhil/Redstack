package com.enterprise.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.Index;
import org.hibernate.criterion.Restrictions;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.util.DateUtils;
import com.enterprise.common.util.StringUtils;

@Entity
@Table(name = "ORDER_LINE", schema = "GLOBAL", uniqueConstraints =
		@UniqueConstraint(columnNames = {"LINE_ID", "ORDER_ID"}))
@org.hibernate.annotations.Table(
	appliesTo = "ORDER_LINE", 
	indexes = { 
		@Index(name = "order_line_id_ix", columnNames = { "ID" } )
	}
)
public class OrderLine implements IEntity, Serializable {

	private static final long serialVersionUID = 3119115232260904554L;
	
	@Id
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ORDER_LINE_SEQ")
	@SequenceGenerator(name = "ORDER_LINE_SEQ", sequenceName = "GLOBAL.ORDER_LINE_SEQ")
	@Column(name = "ID", nullable = false)
	private long id;
	
	
	@Column(name = "ITEM_REF1")
	private String itemRef1;
	
	@Column(name = "ITEM_REF2")
	private String itemRef2;
	
	@Column(name = "ITEM_REF3")
	private String itemRef3;
	
	@Column(name = "ITEM_REF4")
	private String itemRef4;
	
	@Column(name = "DESCRIPTION", nullable = true, columnDefinition="TEXT")
	private String description;
	
	@Column(name = "NET_AMOUNT", nullable = true, precision = 13, scale = 3)
	private BigDecimal netAmount;
	
	
	@Column(name = "QTY_ORDERED", nullable = true, precision = 10, scale = 0)
	private BigDecimal qtyOrdered;
	
	@Column(name="UOM")
	private String uom;
	
	@Column(name="EXPECTED_READY_DATE")
	private Date expReadyDate;
	
	@Column(name="EARLIEST_SHIPPING_DATE")
	private Date earlShipDate; 
	
	@Column(name="LATEST_SHIPPING_DATE")
	private Date latShipDate; 
	
	@Column(name="ROS_DATE")
	private Date roseDate; 
	
	@Column(name = "LINE_ID", nullable = false, length = 16)
	private String lineID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn(name = "ORDER_ID", referencedColumnName = "ID", nullable = true)
	})
	private Order orderData;
	
	@OneToMany(mappedBy = "orderLine", fetch = FetchType.LAZY)
	private List<OrderExpedLine> orderExpedLines = new ArrayList<OrderExpedLine>();
	
	@Column(name="USER_DEFINED_DATE_1")
	private Date userDefDate1;
	
	@Column(name="USER_DEFINED_DATE_2")
	private Date userDefDate2; 
	
	@Column(name="USER_DEFINED_DATE_3")
	private Date userDefDate3; 
	
	@Column(name="USER_DEFINED_DATE_4")
	private Date userDefDate4;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isEmpty() {
		return false;
	}

	public String getItemRef1() {
		return itemRef1;
	}

	public void setItemRef1(String itemRef1) {
		if(StringUtils.isEmpty(itemRef1)){
			throw new RuntimeException("Value [" + itemRef1 + "] as the Item Ref No.1 is empty. ");
		}
		this.itemRef1 = itemRef1;
	}

	public String getItemRef2() {
		return itemRef2;
	}

	public void setItemRef2(String itemRef2) {
		this.itemRef2 = itemRef2;
	}

	public String getItemRef3() {
		return itemRef3;
	}

	public void setItemRef3(String itemRef3) {
		this.itemRef3 = itemRef3;
	}

	public String getItemRef4() {
		return itemRef4;
	}

	public void setItemRef4(String itemRef4) {
		this.itemRef4 = itemRef4;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}
	
	public void setNetAmount(String netAmount) {
		try {
			Double localAmountDouble = StringUtils.asDouble(netAmount);
			this.netAmount = (localAmountDouble == null) 
					? null : BigDecimal.valueOf(localAmountDouble).setScale(3, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + netAmount + "] as the Net Amount is invalid. " + 
					"The system expects a valid decimal value. Example: 1098.456");
		}
	}

	public BigDecimal getQtyOrdered() {
		return qtyOrdered;
	}

	public void setQtyOrdered(BigDecimal qtyOrdered) {
		this.qtyOrdered = qtyOrdered;
	}
	
	public void setQtyOrdered(String qtyOrdered) {
		try {
			Double qtyOrderedDouble = StringUtils.asDouble(qtyOrdered);
			this.qtyOrdered = (qtyOrderedDouble == null) 
					? null : BigDecimal.valueOf(qtyOrderedDouble).setScale(0, BigDecimal.ROUND_HALF_UP);
		} catch (Throwable e) {
			throw new RuntimeException("Value [" + qtyOrdered + "] as the Quantity is invalid. " + 
					"The system expects a valid integer value. Example: 35");
		}
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public Date getExpReadyDate() {
		return expReadyDate;
	}

	public void setExpReadyDate(Date expReadyDate) {
		this.expReadyDate = expReadyDate;
	}
	
	public void setExpReadyDate(String expReadyDate) {
		try {
			this.expReadyDate = DateUtils.toValidatedDate(StringUtils.upper(expReadyDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + expReadyDate + "] as the Expected Ready date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getEarlShipDate() {
		return earlShipDate;
	}

	public void setEarlShipDate(Date earlShipDate) {
		this.earlShipDate = earlShipDate;
	}
	
	public void setEarlShipDate(String earlShipDate) {
		try {
			this.earlShipDate = DateUtils.toValidatedDate(StringUtils.upper(earlShipDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + earlShipDate + "] as the Earlier Shipment date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getLatShipDate() {
		return latShipDate;
	}

	public void setLatShipDate(Date latShipDate) {
		this.latShipDate = latShipDate;
	}
	
	public void setLatShipDate(String latShipDate) {
		try {
			this.latShipDate = DateUtils.toValidatedDate(StringUtils.upper(latShipDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + latShipDate + "] as the Latest Shipment date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getRoseDate() {
		return roseDate;
	}

	public void setRoseDate(Date roseDate) {
		this.roseDate = roseDate;
	}
	
	public Order getOrderData() {
		return orderData;
	}

	public void setOrderData(Order orderData) {
		this.orderData = orderData;
	}
	
	public List<OrderExpedLine> getOrderExpedLines() {
		return orderExpedLines;
	}

	public void setOrderExpedLines(List<OrderExpedLine> orderExpedLines) {
		this.orderExpedLines = orderExpedLines;
	}
	
	public void setRoseDate(String roseDate) {
		try {
			this.roseDate = DateUtils.toValidatedDate(StringUtils.upper(roseDate));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + roseDate + "] as the ROS date is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public String getLineID() {
		return lineID;
	}

	public void setLineID(String lineID) {
		try {
			double db = Double.parseDouble(lineID);
			int in = (int) db;
			this.lineID = in+"";
		} catch (Exception e) {
			this.lineID = lineID;
		}
	}
	
	public static OrderLine load(String lineId, Session session, long orderId) {
		if(StringUtils.isEmpty(lineId)){
			return null;
		}
		Criteria criteria = session.createCriteria(OrderLine.class)
			.add(Restrictions.eq("lineID", lineId));
		if(orderId != 0L)
			criteria.add(Restrictions.eq("orderData.id", orderId));
		OrderLine order = (OrderLine)criteria.uniqueResult();
		return order;
	}
	
	public static OrderLine loadUnique(String itemRef1,String desc, Session session, long orderId) {
		if(StringUtils.isEmpty(itemRef1)){
			return null;
		}
		Criteria criteria = session.createCriteria(OrderLine.class)
			.add(Restrictions.eq("itemRef1", itemRef1));
		if(!StringUtils.isEmpty(desc)){
			criteria.add(Restrictions.eq("description", desc));;
		}
		if(orderId != 0L)
			criteria.add(Restrictions.eq("orderData.id", orderId));
		OrderLine order = (OrderLine)criteria.uniqueResult();
		return order;
	}
	
	public void setUserDefDate1(Date userDefDate1) {
		this.userDefDate1 = userDefDate1;
	}

	public void setUserDefDate1(String userDefDate1) {
		try {
			this.userDefDate1 = DateUtils.toValidatedDate(StringUtils.upper(userDefDate1));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + userDefDate1 + "] as the User defined date1 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getUserDefDate1() {
		return userDefDate1;
	}

	public void setUserDefDate2(Date userDefDate2) {
		this.userDefDate2 = userDefDate2;
	}
	
	public void setUserDefDate2(String userDefDate2) {
		try {
			this.userDefDate2 = DateUtils.toValidatedDate(StringUtils.upper(userDefDate2));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + userDefDate2 + "] as the User defined date2 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getUserDefDate2() {
		return userDefDate2;
	}
	
	public void setUserDefDate3(String userDefDate3) {
		try {
			this.userDefDate3 = DateUtils.toValidatedDate(StringUtils.upper(userDefDate3));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + userDefDate3 + "] as the User defined date3 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public void setUserDefDate3(Date userDefDate3) {
		this.userDefDate3 = userDefDate3;
	}

	public Date getUserDefDate3() {
		return userDefDate3;
	}

	public void setUserDefDate4(Date userDefDate4) {
		this.userDefDate4 = userDefDate4;
	}
	
	public void setUserDefDate4(String userDefDate4) {
		try {
			this.userDefDate4 = DateUtils.toValidatedDate(StringUtils.upper(userDefDate4));
		} catch (RuntimeException e) {
			throw new RuntimeException("Value [" + userDefDate4 + "] as the User defined date4 is invalid. " + 
					"The system expects a valid date value. Example: 12-Feb-2014.");
		}
	}

	public Date getUserDefDate4() {
		return userDefDate4;
	}
}
