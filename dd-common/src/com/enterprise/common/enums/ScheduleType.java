package com.enterprise.common.enums;

public enum ScheduleType {
	M ("Monthly"),
	W ("Weekly"),
	O ("NA"),
	D ("Daily");
	
	final String schedule;
	
	ScheduleType(final String load) {
		this.schedule = load;
	}
	
	public static ScheduleType valueOfByMode(String schedule) {
		for (ScheduleType loadType : ScheduleType.values())
			if (loadType.getSchedule().equals(schedule))
				return loadType;
		return null;
	}

	public String getSchedule() {
		return schedule;
	}
}
