package com.enterprise.common.collections;

import java.util.Comparator;

import com.enterprise.common.enums.QuoteSummaryTypes;

public class QuoteSummaryTypesWeightComparator implements Comparator<QuoteSummaryTypes> {
	public int compare(QuoteSummaryTypes o1, QuoteSummaryTypes o2) {
		int result = 0;
		if (o1 != null && o2 != null) {
			Integer o1SortSequence = Integer.valueOf(o1.getWeight());
			Integer o2SortSequence = Integer.valueOf(o2.getWeight());
			result = o1SortSequence.compareTo(o2SortSequence);
		}
		return result;
	}
}