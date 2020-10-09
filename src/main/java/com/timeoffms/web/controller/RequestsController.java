package com.timeoffms.web.controller;

import com.timeoffms.web.model.TimeOffRequest;
import com.timeoffms.web.model.User;
import com.timeoffms.web.service.TimeOffRequestService;
import com.timeoffms.web.service.UserService;
import com.timeoffms.web.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = {"/hr/requests", "/myRequests"})
public class RequestsController {

	@Autowired
	private TimeOffRequestService timeOffRequestService;

	@Autowired
	private UserService userService;

	@GetMapping
	public ModelAndView listRequests(ModelMap model){
		User currentUser = Utils.getCurrentUser(userService);
		model.addAttribute("currentUser", currentUser);

		List<TimeOffRequest> requestsList = listUserValidRequests(currentUser);
		model.addAttribute("requestsList", requestsList);
		return new ModelAndView("timeOffRequest/list-requests", model);
	}

	@GetMapping("/{id}")
	public ModelAndView showRequest(@PathVariable(required = false) Long id, ModelMap model){
		model.addAttribute("request", timeOffRequestService.findIfExists(id));
		return new ModelAndView("timeOffRequest/show-request", model);
	}

	@GetMapping(path = {"/create", "/{id}/edit"})
	public ModelAndView showEditForm(@PathVariable(required = false) Long id, ModelMap model, @RequestHeader("referer") String referer){
		User currentUser = Utils.getCurrentUser(userService);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("request", timeOffRequestService.findIfExists(id));
		model.addAttribute("userList", userService.findAll());
		model.addAttribute("goBackUrl", referer);
		return new ModelAndView("timeOffRequest/edit-request", model);
	}

	@PostMapping(params = "save", path = {"/create", "/{id}/edit"})
	public ModelAndView saveTeam(@Valid @ModelAttribute("request") TimeOffRequest timeOffRequest, BindingResult result, RedirectAttributes redirectAttrs) {
		timeOffRequest.setRequestedBy(Utils.getCurrentUser(userService));
		timeOffRequest.setUpdatedBy(Utils.getCurrentUser(userService));
		timeOffRequestService.save(timeOffRequest);
		redirectAttrs.addFlashAttribute("message", "Request saved successfully");
		return new ModelAndView("redirect:");
	}

	private List<TimeOffRequest> listUserValidRequests(User currentUser){
		return currentUser.hasRole("HR") || currentUser.hasRole("ADMIN") ? timeOffRequestService.findAll() : timeOffRequestService.findAllFromCurrentUser();
	}

}
