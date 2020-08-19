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
@Table(name = "time_off_request_type")
public class TimeOffRequestType {

	@Id
	private String code;

	@NotNull
	private String description;
}
