package com.timeoffms.web.model;

import lombok.Getter;

public enum TimeOffRequestStatus {

	PENDING_APPROVAL("Pending Approval"),
	APPROVED("Approved"),
	REJECTED("Rejected"),
	CANCELLED("Cancelled");

	@Getter private String name;

	private TimeOffRequestStatus(String name) {
		this.name = name;
	}
}
