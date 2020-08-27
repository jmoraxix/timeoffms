package com.timeoffms.web.model;

import lombok.Getter;

public enum OvertimeStatus {

	PENDING_APPROVAL("Pending Approval"),
	APPROVED("Approved"),
	REJECTED("Rejected"),
	CANCELLED("Cancelled");

	@Getter private String name;

	private OvertimeStatus(String name) {
		this.name = name;
	}
}
