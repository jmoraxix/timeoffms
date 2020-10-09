package com.timeoffms.web.controller;

import com.timeoffms.web.model.Role;
import com.timeoffms.web.service.RoleService;
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
@RequestMapping("/admin/user/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ModelAndView listRoles(ModelMap model){
        List<Role> roleList = roleService.findAll();
        model.addAttribute("roleList", roleList);
        return new ModelAndView("roles/list-roles", model);
    }

    @GetMapping("/create")
    public ModelAndView showCreateRole(ModelMap model){
        model.addAttribute("role", new Role());
        model.addAttribute("editMode", "create");
        return new ModelAndView("roles/edit-role", model);
    }

    @PostMapping("/create")
    public ModelAndView saveCreateRole(@Valid @ModelAttribute("role") Role role, BindingResult result, RedirectAttributes redirectAttrs) {
        roleService.saveRole(role);
        redirectAttrs.addFlashAttribute("message", "Role created successfully");
        return new ModelAndView("redirect:/admin/user/roles");
    }

    @GetMapping("/edit/{roleId}")
    public ModelAndView showEditRole(@PathVariable Long roleId, ModelMap model){
        Role role = roleService.findRole(roleId);
        model.addAttribute("role", role);
        model.addAttribute("editMode", "edit");
        return new ModelAndView("roles/edit-role", model);
    }

    @PostMapping("/edit/{roleId}")
    public ModelAndView saveEditRole(@Valid @ModelAttribute("role") Role role, BindingResult result, RedirectAttributes redirectAttrs) {
        roleService.saveRole(role);
        redirectAttrs.addFlashAttribute("message", "Role updated successfully");
        return new ModelAndView("redirect:/admin/user/roles/");
    }

    @GetMapping("/delete/{roleId}")
    public ModelAndView deleteRole(@PathVariable Long roleId, RedirectAttributes redirectAttrs){
        roleService.deleteRole(roleId);
        redirectAttrs.addFlashAttribute("message", "Role deleted successfully");
        return new ModelAndView("redirect:/admin/user/roles/");
    }
}
