package com.enterprise.common.pojo.quote;

import java.io.Serializable;
import java.util.List;

import com.enterprise.common.collections.QuoteSummaryBean;
import com.enterprise.common.enums.QuoteSummaryTypes;
import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.pojo.CheckboxInputBean;

public class WorkflowSectionBean implements Serializable, IBean {
	private static final long serialVersionUID = -6095631623949988533L;
	private final QuoteSummaryBean quoteSummaryBean;
	private final CheckboxInputBean workflowSolutionCheckboxInputBean = new CheckboxInputBean(false, true);

	public WorkflowSectionBean(QuoteSummaryBean quoteSummaryBean) {
		this.quoteSummaryBean = quoteSummaryBean;
	}
	
	public void initWorkflowSolutionSection(boolean checked) {
		if (checked) {
			quoteSummaryBean.put(QuoteSummaryTypes.WORKFLOW_SOLUTION);
		} else {
			List<QuoteSummaryTypes> valuesByParent = QuoteSummaryTypes.valuesByParent(QuoteSummaryTypes.WORKFLOW_SOLUTION);
			for (QuoteSummaryTypes quoteSummaryTypes : valuesByParent) {
				quoteSummaryBean.remove(quoteSummaryTypes);
			}
			quoteSummaryBean.remove(QuoteSummaryTypes.WORKFLOW_SOLUTION);
		}
	}
	
	public void reset() {
		workflowSolutionCheckboxInputBean.reset();
	}

	public CheckboxInputBean getWorkflowSolutionCheckboxInputBean() {
		return workflowSolutionCheckboxInputBean;
	}	
}