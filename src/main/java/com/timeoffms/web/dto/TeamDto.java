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

	private List<UserDto> members;

	private List<UserDto> approvers;

	public void addMember(UserDto userDto){
		if(members == null) members = new ArrayList<>();
		members.add(userDto);
	}

	public void removeMember(int index){
		members.remove(index);
	}

	public void addApprover(UserDto userDto){
		if(approvers == null) approvers = new ArrayList<>();
		approvers.add(userDto);
	}

	public void removeApprover(int index){
		approvers.remove(index);
	}
}
