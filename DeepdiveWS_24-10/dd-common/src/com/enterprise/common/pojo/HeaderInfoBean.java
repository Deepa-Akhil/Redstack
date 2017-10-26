package com.enterprise.common.pojo;

import java.util.LinkedList;

import com.enterprise.common.enums.InfoMessageTypes;
import com.enterprise.common.interfaces.IBean;

public class HeaderInfoBean implements IBean {
	private static final long serialVersionUID = 7095494728802295136L;
	private final LinkedList<InfoMessageBean> infoMessages = new LinkedList<InfoMessageBean>();
	private final LinkedList<InfoMessageBean> errorMessages = new LinkedList<InfoMessageBean>();
	
	public void reset() {
		errorMessages.clear();
		infoMessages.clear();
	}

	public void addInfoMessage(InfoMessageBean infoMessageBean) {
		if (infoMessageBean != null) {
			boolean duplicateInfoBean = this.checkForDuplicateMessage(infoMessages, infoMessageBean);
			if (!duplicateInfoBean) {
				int targetIndex = this.calculateMessageIndex(infoMessages, 2);
				if (targetIndex == infoMessages.size()) {
					infoMessages.add(infoMessageBean);
				} else {
					infoMessages.set(targetIndex, infoMessageBean);
				}
			}
		}
	}
	
	private int calculateMessageIndex(LinkedList<InfoMessageBean> targetCollection, int maxSize) {
		int targetIndex = targetCollection.size(); 
		targetIndex = (targetIndex == maxSize) ? (maxSize - 1) : targetIndex;
		return targetIndex;
	}
	
	public void addErrorMessage(InfoMessageBean infoMessageBean) {
		if (infoMessageBean != null) {
			boolean duplicateInfoBean = this.checkForDuplicateMessage(errorMessages, infoMessageBean);
			if (!duplicateInfoBean) {
				int targetIndex = this.calculateMessageIndex(errorMessages, 1);
				if (targetIndex == errorMessages.size()) {
					errorMessages.add(infoMessageBean);
				} else {
					errorMessages.set(targetIndex, infoMessageBean);
				}
			}
		}
	}
		
	private boolean checkForDuplicateMessage(LinkedList<InfoMessageBean> targetCollection, InfoMessageBean infoMessageBean) {
		boolean duplicateInfoBean = false;
		InfoMessageTypes infoMessageBeanType = infoMessageBean.getType();
		String infoMessageBeanMessage = infoMessageBean.getMessage();
		for (InfoMessageBean bean : targetCollection) {
			if (bean != null && bean.getType().equals(infoMessageBeanType) && bean.getMessage().equalsIgnoreCase(infoMessageBeanMessage)) {
				duplicateInfoBean = true; break;
			}
		}
		return duplicateInfoBean;
	}
	
	public LinkedList<InfoMessageBean> getInfoMessages() {
		return infoMessages;
	}
	
	public LinkedList<InfoMessageBean> getErrorMessages() {
		return errorMessages;
	}
}