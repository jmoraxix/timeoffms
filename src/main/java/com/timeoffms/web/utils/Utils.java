package com.timeoffms.web.utils;

import com.timeoffms.web.model.User;
import com.timeoffms.web.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	private static final String AJAX_HEADER_NAME = "X-Requested-With";
	private static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

	private static Authentication getCurrentAuthentication(){
		return SecurityContextHolder.getContext().getAuthentication();
	}
	public static String getCurrentUsername(){
		return getCurrentAuthentication().getName();
	}

	public static User getCurrentUser(UserService userService){
		return userService.findByUsername(getCurrentUsername());
	}

	public static boolean currentAuthUserHasRole(String role){
		UserDetails userDetails = (UserDetails) getCurrentAuthentication().getPrincipal();
		return userDetails.getAuthorities().stream().anyMatch(r -> r.toString().equals(role));
	}
	public static ModelAndView renderAjaxFragment(ModelMap model, HttpServletRequest request, String fragment, String view) {
		if (AJAX_HEADER_VALUE.equals(request.getHeader(AJAX_HEADER_NAME))) {
			return new ModelAndView(fragment, model);
		} else {
			// It is a standard HTTP request, render whole page.
			return new ModelAndView(view, model);
		}
	}
}
