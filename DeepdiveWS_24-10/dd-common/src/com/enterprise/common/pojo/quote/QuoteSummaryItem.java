package com.enterprise.common.pojo.quote;

import java.io.Serializable;
import java.math.BigDecimal;

import com.enterprise.common.enums.QuoteSummaryTypes;
import com.enterprise.common.util.StringUtils;

public class QuoteSummaryItem implements Serializable {
	private static final long serialVersionUID = -6803534234321993031L;
	private QuoteSummaryTypes type = null;
	private String description = null;
	private BigDecimal unitPrice = BigDecimal.ZERO;
	private int qty = 1;
	private int weight = 0;
	
	public QuoteSummaryItem(QuoteSummaryTypes type) {
		this.type = type;
		this.description = type.getDescription();
		this.unitPrice = BigDecimal.valueOf(StringUtils.isEmpty(type.getPrice()) ? 0L : Long.parseLong(type.getPrice()));
		this.qty = 1; 
		this.weight = type.getWeight();
	}
	
	public QuoteSummaryItem(QuoteSummaryTypes type, int qty) {
		this(type);
		this.qty = qty;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice.setScale(0);
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public BigDecimal getTotal() {
		return this.unitPrice.multiply(BigDecimal.valueOf(this.qty)).setScale(0);
	}

	public QuoteSummaryTypes getType() {
		return type;
	}
}