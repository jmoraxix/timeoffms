package com.timeoffms.web.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

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

	@NotNull
	private String location;
}