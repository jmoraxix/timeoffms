package com.timeoffms.web.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

	@ManyToOne
	@NotNull
	@JoinColumn(name="manager_id")
	private User manager;

	@OneToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="team_id")
	private List<User> members;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "alternate_team_approver", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "approver_id",
			referencedColumnName = "id"))
	private List<User> alternateApprovers;

	public List<User> getMembers(){
		return members == null ? new ArrayList<>() : members;
	}

	public void addMember(User user){
		if(members == null) members = new ArrayList<>();
		members.add(user);
	}

	public void removeMember(User user){
		members.remove(user);
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Team{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", manager=").append(manager);
		sb.append(", members=").append(members.stream().map(m -> m.getFullname()).reduce(", ", String::concat));
		sb.append(", alternateApprovers=").append(alternateApprovers);
		sb.append('}');
		return sb.toString();
	}
}