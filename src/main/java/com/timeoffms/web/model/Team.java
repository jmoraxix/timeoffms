package com.timeoffms.web.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "user_teams", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName =
			"id"))
	private List<User> members;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "team_approver", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "approver_id",
			referencedColumnName = "id"))
	private List<User> approvers;

	public List<User> getMembers(){
		return Optional.ofNullable(members).orElse(new ArrayList<>());
	}

	public List<User> getApprovers(){
		return Optional.ofNullable(approvers).orElse(new ArrayList<>());
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Team{");
		sb.append("id=").append(id);
		sb.append(", name='").append(name).append('\'');
		sb.append(", manager=").append(manager.getFullname());
		sb.append(", members=").append(members.stream().map(m -> m.getFullname()).reduce(", ", String::concat));
		sb.append(", alternateApprovers=").append(approvers.stream().map(m -> m.getFullname()).reduce(", ", String::concat));
		sb.append('}');
		return sb.toString();
	}
}