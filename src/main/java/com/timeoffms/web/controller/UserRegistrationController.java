package com.timeoffms.web.controller;

import com.timeoffms.web.dto.UserRegistrationDto;
import com.timeoffms.web.model.User;
import com.timeoffms.web.service.CountryService;
import com.timeoffms.web.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
@Log4j2
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private CountryService countryService;

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public ModelAndView showRegistrationForm(ModelMap model) {
		model.addAttribute("countryList", countryService.findAll());
		return new ModelAndView("login/registration", model);
	}

	@PostMapping
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, ModelMap model, BindingResult result) {
		User existing = userService.findByUsername(userDto.getUsername());
		if (existing != null) {
			result.rejectValue("username", null, "There is already an account registered with that username");
		}

		if (result.hasErrors()) {
			return new ModelAndView("login/registration", model);
		}

		userService.save(userDto);
		return new ModelAndView("redirect:/login?registerSuccess");
	}
}