package com.enterprise.common.pojo.quote;

import java.io.Serializable;

import com.enterprise.common.enums.InfoMessageTypes;
import com.enterprise.common.pojo.InfoMessageBean;

public class ConfirmationBean implements Serializable, IStepable {
	private static final long serialVersionUID = -8958481670677523601L;
	private final String requestMapping = "/request_a_quote/input/confirmation/body";
	private final InfoMessageBean infoMessageBean = new InfoMessageBean();
	private int index = 0;
	
	public ConfirmationBean(int index) {
		this.index = index;
		String message = new String(
			"Kindly check your inbox for our formal quote in PDF format."
		);
		infoMessageBean.setMessage(message, InfoMessageTypes.Info);
	}
	
	public void reset() {
		
	}

	public String getStepName() {
		return "Confirmation";
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getRequestMapping() {
		return requestMapping;
	}

	public InfoMessageBean getInfoMessageBean() {
		return infoMessageBean;
	}

}