package com.timeoffms.web.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Validated
@Table(name = "time_off_request")
public class TimeOffRequest {

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@ManyToOne
	private User user;

	@NotNull
	@ManyToOne
	private TimeOffRequestType timeOffRequestType;

	@NotNull
	private OffsetDateTime startDate;

	@NotNull
	private OffsetDateTime endDate;

	@NotNull
	@ManyToOne
	private TimeOffRequestStatus timeOffRequestStatus;
}
