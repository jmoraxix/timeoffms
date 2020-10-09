package com.timeoffms.web.model;

import lombok.Getter;

public enum PhoneNumberType {

	PRIMARY("Primary"), OTHER("Other");

	@Getter private String name;

	private PhoneNumberType(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
