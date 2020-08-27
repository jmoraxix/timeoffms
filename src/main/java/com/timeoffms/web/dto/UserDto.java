package com.timeoffms.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {

	@NotEmpty
	private Long id;

	private String username;

	private String email;

	private String firstName;

	private String lastName;

	public String getFullname(){
		return this.firstName + " " + this.lastName;
	}
}
