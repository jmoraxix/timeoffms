package com.timeoffms.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class TeamDto {

	private Long id;

	@NotEmpty
	private String name;

	private String location;

	private UserDto manager;

	private List<UserDto> members = new ArrayList<>();

	private List<UserDto> approvers = new ArrayList<>();

	public void addMember(UserDto userDto){
		if(members == null) members = new ArrayList<>();
		members.add(userDto);
	}

	public void addApprover(UserDto userDto){
		if(approvers == null) approvers = new ArrayList<>();
		approvers.add(userDto);
	}
}
