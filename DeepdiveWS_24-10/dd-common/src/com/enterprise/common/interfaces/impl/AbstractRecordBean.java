package com.enterprise.common.interfaces.impl;

import com.enterprise.common.interfaces.IRecord;

public abstract class AbstractRecordBean implements IRecord {
	private static final long serialVersionUID = 8816172000334805167L;

	public abstract long getRecordId();
}