package com.enterprise.common.collections;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.enterprise.common.enums.QuoteSummaryTypes;
import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.pojo.quote.QuoteSummaryItem;

public class QuoteSummaryBean implements IBean, Serializable {
	private static final long serialVersionUID = 3507003273932280987L;
	private final Map<QuoteSummaryTypes, QuoteSummaryItem> items = new HashMap<QuoteSummaryTypes, QuoteSummaryItem>();
	private boolean submitted = false;
	
	public void reset() {
		items.clear();
		submitted = false;
	}

	public Collection<QuoteSummaryItem> getValues() {
		List<QuoteSummaryItem> list = new ArrayList<QuoteSummaryItem>();
		list.addAll(items.values());
		Collections.sort(list, new QuoteSummaryBeanWeightComparator());
		return list;
	}
	
	public void put(QuoteSummaryTypes type) {
		QuoteSummaryItem bean = new QuoteSummaryItem(type);
		items.put(type, bean);
	}
	
	public void put(QuoteSummaryTypes type, int qty) {
		QuoteSummaryItem bean = new QuoteSummaryItem(type, qty);
		items.put(type, bean);
	}
	
	public void put(QuoteSummaryItem bean) {
		QuoteSummaryTypes type = bean.getType();
		items.put(type, bean);
	}
	
	public QuoteSummaryItem get(QuoteSummaryTypes type) {
		return items.get(type);
	}
	
	public BigDecimal getQuoteTotal() {
		BigDecimal total = BigDecimal.ZERO;
		Collection<QuoteSummaryItem> values = items.values();
		for (QuoteSummaryItem quoteSummaryItem : values) {
			total = total.add(quoteSummaryItem.getTotal());
		}
		return total;
	}
	
	public void remove(QuoteSummaryTypes type) {
		items.remove(type);
	}
	
	class QuoteSummaryBeanWeightComparator implements Comparator<QuoteSummaryItem> {
		public int compare(QuoteSummaryItem o1, QuoteSummaryItem o2) {
			int result = 0;
			if (o1 != null && o2 != null) {
				Integer o1SortSequence = Integer.valueOf(o1.getWeight());
				Integer o2SortSequence = Integer.valueOf(o2.getWeight());
				result = o1SortSequence.compareTo(o2SortSequence);
			}
			return result;
		}
	}

	public boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}
}