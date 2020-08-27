package com.timeoffms.web.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDateTime;

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
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private OvertimeType overtimeType;

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime reportedDate = LocalDateTime.now();

	@NotNull
	private int hours;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private OvertimeStatus overtimeStatus = OvertimeStatus.PENDING_APPROVAL;

}