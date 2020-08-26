package com.timeoffms.web.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@Log4j2
public class MainController {

   @GetMapping("/login")
   public ModelAndView showLogin(ModelMap model) {
       return new ModelAndView("login/login", model);
   }
}
