package com.timeoffms.web.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Validated
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Column(unique = true)
	private String name;

	private String location;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "user_teams", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private List<User> members;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "default_approver", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "approver_id", referencedColumnName = "id"))
	private List<User> approvers;

	public boolean isApprover(User user){
		return this.approvers.stream().anyMatch(approver -> approver.getUsername().equals(user.getUsername()));
	}

	public List<User> getMembers(){
		return members == null ? new ArrayList<>() : members;
	}

	public List<User> getApprovers(){
		return approvers == null ? new ArrayList<>() : approvers;
	}

	public void addMember(User user){
		if(members == null) members = new ArrayList<>();
		members.add(user);
	}

	public void removeMember(User user){
		members.remove(user);
	}

	public void addApprover(User user){
		if(approvers == null) approvers = new ArrayList<>();
		approvers.add(user);
	}

	public void removeApprover(User user){
		members.remove(user);
	}
}