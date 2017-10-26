package com.enterprise.domain.entity;

import com.enterprise.common.entity.IEntity;
import com.enterprise.common.enums.ModeTypes;

public class Port extends Location implements IPort {

	private static final long serialVersionUID = 4117772006246262166L;

	@Override
	public IEntity clone() {
		return null;
	}
	;
	public ModeTypes getModeType() {
		throw new RuntimeException("getModeType() of the Port entity must be overridden in subclass."); 
	}
}