package com.timeoffms.web.model;

import lombok.Getter;

public enum PhoneNumberType {

	PRIMARY("Primary"), OTHER("Other");

	@Getter private String type;

	private PhoneNumberType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
}
