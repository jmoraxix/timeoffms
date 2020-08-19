package com.timeoffms.web.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Validated
@Table(name = "overtime_type")
public class OvertimeType {

	@Id
	private String code;

	@NotNull
	private String description;

	@NotNull
	private int defaultHours;
}
