package com.timeoffms.web.dto;

import com.timeoffms.web.constraint.FieldMatch;
import com.timeoffms.web.model.Country;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@FieldMatch.List({
		@FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match")
})
@Data
public class UserRegistrationDto {

	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

	@NotEmpty
	private String confirmPassword;

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate joiningDate;

	@NotNull
	private Country country;

}