package com.timeoffms.web.controller;

import com.timeoffms.web.service.TimeOffRequestService;
import com.timeoffms.web.service.UserService;
import com.timeoffms.web.utils.Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Log4j2
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private TimeOffRequestService timeOffRequestService;

    @GetMapping("/login")
    public ModelAndView showLogin(ModelMap model) {
           return new ModelAndView("login/login", model);
       }

    @GetMapping("/")
    public ModelAndView showHome(ModelMap model) {
		model.addAttribute("availableVacationDays", Utils.getCurrentUser(userService).getAvailableVacationDays());
        model.addAttribute("activeRequestList", timeOffRequestService.findAllActiveFromCurrentUser());
        if(Utils.currentAuthUserHasRole("APPROVER"))
        	model.addAttribute("pendingApprovalsList", timeOffRequestService.findAllToApproveByCurrentUser());
        return new ModelAndView("home/home", model);
    }
}
