package com.timeoffms.web.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Validated
@Table(name = "overtime")
public class Overtime {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@ManyToOne
	private User user;

	@NotNull
	@ManyToOne
	private OvertimeType overtimeType;

	@NotNull
	private Timestamp date;

	@NotNull
	private int hours;

}