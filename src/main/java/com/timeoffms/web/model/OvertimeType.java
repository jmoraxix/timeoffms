package com.timeoffms.web.model;

import lombok.Getter;

public enum OvertimeType {

	OTHER("Other", 0),
	FRONTLINE_QA("Frontline QA", 4);

	@Getter
	private String name;

	@Getter
	private int defaultHours;

	private OvertimeType(String name, int defaultHours) {
		this.name = name;
		this.defaultHours = defaultHours;
	}
}
