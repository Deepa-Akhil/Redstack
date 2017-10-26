package com.enterprise.common.pojo.quote;

import com.enterprise.common.interfaces.IBean;
import com.enterprise.common.pojo.InfoMessageBean;

public interface IStepable extends IBean {
	public abstract String getStepName();
	public abstract int getIndex();
	public abstract void setIndex(int index);
	public abstract String getRequestMapping();
	public abstract InfoMessageBean getInfoMessageBean();
}