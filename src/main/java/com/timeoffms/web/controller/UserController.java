package com.timeoffms.web.controller;

import com.timeoffms.web.model.PhoneNumber;
import com.timeoffms.web.model.User;
import com.timeoffms.web.service.*;
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
@RequestMapping(path = {"/hr/user", "/admin/user"})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TeamService teamService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private PhoneNumberService phoneNumberService;

    @GetMapping
    public ModelAndView listUsers(ModelMap model){
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return new ModelAndView("users/list-users", model);
    }
    
    @GetMapping("/create")
    public ModelAndView showCreateUser(ModelMap model){
        model.addAttribute("user", new User());
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("teamList", teamService.findAll());
        model.addAttribute("editMode", "create");
        return new ModelAndView("users/edit-user", model);
    }

    @PostMapping("/create")
    public ModelAndView saveCreateUser(@Valid @ModelAttribute("user") User user, ModelMap model, BindingResult result, RedirectAttributes redirectAttrs) {
        if(result.hasErrors()){
            return new ModelAndView("users/edit-user", model);
        }
        userService.save(user);
        redirectAttrs.addFlashAttribute("message", "User created successfully");
        return new ModelAndView("redirect:/admin/user");
    }

    @GetMapping("/{userId}")
    public ModelAndView showUser(@PathVariable Long userId, ModelMap model){
        model.addAttribute("user", userService.findById(userId));
        return new ModelAndView("users/show-user-info", model);
    }

    @GetMapping("/edit/{userId}")
    public ModelAndView showEditUser(@PathVariable Long userId, ModelMap model){
        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("roleList", roleService.findAll());
        model.addAttribute("teamList", teamService.findAll());
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("editMode", "edit");
        return new ModelAndView("users/edit-user", model);
    }

    @PostMapping("/edit/{userId}")
    public ModelAndView saveEditUser(@Valid @ModelAttribute("user") User user, BindingResult result, RedirectAttributes redirectAttrs) {
        userService.save(user);
        redirectAttrs.addFlashAttribute("message", "User updated successfully");
        return new ModelAndView("redirect:/admin/user");
    }

    @GetMapping("/delete/{userId}")
    public ModelAndView deleteUser(@PathVariable Long userId, RedirectAttributes redirectAttrs){
        userService.delete(userId);
        redirectAttrs.addFlashAttribute("message", "User deleted successfully");
        return new ModelAndView("redirect:/admin/user");
    }

    //Phone number section
    @GetMapping("/{userId}/phone")
    public ModelAndView listPhoneNumbers(@PathVariable Long userId, ModelMap model){
        model.addAttribute("user", userService.findById(userId));
        return new ModelAndView("users/list-phones", model);
    }

    @GetMapping("/{userId}/phone/create")
    public ModelAndView showCreatePhoneNumber(@PathVariable Long userId, ModelMap model){
        model.addAttribute("phoneNumber", new PhoneNumber());
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("editMode", "create");
        return new ModelAndView("users/edit-phone", model);
    }

    @PostMapping("/{userId}/phone/create")
    public ModelAndView saveCreatePhoneNumber(@PathVariable Long userId, @Valid @ModelAttribute("phoneNumber") PhoneNumber phoneNumber, BindingResult result, RedirectAttributes redirectAttrs) {
        phoneNumberService.savePhoneNumber(phoneNumber);
        redirectAttrs.addFlashAttribute("message", "Phone number created successfully");
        return new ModelAndView("redirect:/admin/user/" + userId + "/phone");
    }

    @GetMapping("/{userId}/phone/edit/{phoneNumberId}")
    public ModelAndView showEditPhoneNumber(@PathVariable Long userId, @PathVariable Long phoneNumberId, ModelMap model){
        PhoneNumber phoneNumber = phoneNumberService.findPhoneNumber(phoneNumberId);
        model.addAttribute("phoneNumber", phoneNumber);
        model.addAttribute("countryList", countryService.findAll());
        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("editMode", "edit");
        return new ModelAndView("users/edit-phone", model);
    }

    @PostMapping("/{userId}/phone/edit/{phoneNumberId}")
    public ModelAndView saveEditPhoneNumber(@PathVariable Long userId, @PathVariable Long phoneNumberId, @Valid @ModelAttribute("phoneNumber") PhoneNumber phoneNumber, BindingResult result, RedirectAttributes redirectAttrs) {
        phoneNumberService.savePhoneNumber(phoneNumber);
        redirectAttrs.addFlashAttribute("message", "Phone number updated successfully");
        return new ModelAndView("redirect:/admin/user/" + userId + "/phone");
    }

    @GetMapping("/{userId}/phone/delete/{phoneNumberId}")
    public ModelAndView deletePhoneNumber(@PathVariable Long userId, @PathVariable Long phoneNumberId, RedirectAttributes redirectAttrs){
        phoneNumberService.deletePhoneNumber(phoneNumberId);
        redirectAttrs.addFlashAttribute("message", "Phone number deleted successfully");
        return new ModelAndView("redirect:/admin/user/" + userId + "/phone");
    }
}
