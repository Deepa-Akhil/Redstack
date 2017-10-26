package com.enterprise.common.enums;


public enum ModeTypes {
	O ("Ocean"),
	A ("Air"),
	M ("Multi Mode"),
	T ("Trunk"),
	R ("Rai"),
	C ("Cou"),
	H ("Oth");
	
	final String mode;
	
	ModeTypes(final String mode) {
		this.mode = mode;
	}
	
	public static ModeTypes valueOfByMode(String mode) {
		for (ModeTypes modeType : ModeTypes.values())
			if (modeType.getMode().equals(mode))
				return modeType;
		return null;
	}

	public String getMode() {
		return mode;
	}
}