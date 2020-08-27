package com.timeoffms.web.model;

import lombok.Getter;

public enum TimeOffRequestType {

	VACATION("Vacation"),
	COMPENSATION("Compensation"),
	SICK_LEAVE("Sick leave"),
	MATERNITY("Maternity"),
	PATERNITY_LEAVE("Paternity leave");

	@Getter private String name;

	TimeOffRequestType(String name) {
		this.name = name;
	}
}
