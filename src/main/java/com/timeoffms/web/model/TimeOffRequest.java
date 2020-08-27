package com.timeoffms.web.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

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
	private User requestedBy;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private TimeOffRequestType timeOffRequestType;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime requestedDate = LocalDateTime.now();

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime startDate;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
	private LocalDateTime endDate;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TimeOffRequestStatus timeOffRequestStatus = TimeOffRequestStatus.PENDING_APPROVAL;

	private String reason;

	@ManyToOne
	private User updatedBy;

	public String getRangeDates(){
		return "From " + startDate.toString() + " to " + endDate;
	}

	public long getTotalDays(){
		return DAYS.between(startDate, endDate);
	}
}
