package com.timeoffms.web.controller;

import com.timeoffms.web.dto.TeamDto;
import com.timeoffms.web.mapper.TeamMapper;
import com.timeoffms.web.mapper.UserMapper;
import com.timeoffms.web.model.Team;
import com.timeoffms.web.service.TeamService;
import com.timeoffms.web.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static com.timeoffms.web.utils.Utils.renderAjaxFragment;

@RestController
@Log4j2
@RequestMapping("/admin/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView listTeams(ModelMap model){
        List<Team> teamList = teamService.findAll();
        model.addAttribute("teamList", teamList);
        return new ModelAndView("teams/list-teams", model);
    }

    @GetMapping(path = {"/create", "/edit/{id}"})
    public ModelAndView showForm(@PathVariable(required = false) Long id, ModelMap model){
        model.addAttribute("team", TeamMapper.MAPPER.teamToTeamDto(id == null ? new Team() : teamService.findTeam(id)));
        model.addAttribute("userList", userService.findAll());
        return new ModelAndView("teams/edit-team", model);
    }

    @PostMapping(params = "save", path = {"/create", "/edit/{id}"})
    public ModelAndView saveTeam(@Valid @ModelAttribute("team") TeamDto teamDto, BindingResult result, ModelMap model, RedirectAttributes redirectAttrs) {
        teamService.saveTeam(TeamMapper.MAPPER.teamDtoToTeam(teamDto));
        redirectAttrs.addFlashAttribute("message", "Team updated successfully");
        return new ModelAndView("redirect:/admin/teams");
    }

    @PostMapping(params = "addMember", path = {"/create", "/edit/{id}"})
    public ModelAndView addMember(@ModelAttribute("team") TeamDto teamDto, @RequestParam("addMember") Long memberId, ModelMap model, HttpServletRequest request) {
        teamDto.addMember(UserMapper.MAPPER.userToUserDto(userService.findById(memberId)));;
        return renderAjaxFragment(model, request, "teams/edit-team :: #members", "teams/edit-team");
    }

    @PostMapping(params = "addApprover", path = {"/create", "/edit/{id}"})
    public ModelAndView addApprover(@ModelAttribute("team") TeamDto teamDto, @RequestParam("addApprover") Long approverId, ModelMap model, HttpServletRequest request) {
        teamDto.addApprover(UserMapper.MAPPER.userToUserDto(userService.findById(approverId)));;
        return renderAjaxFragment(model, request, "teams/edit-team :: #approvers", "teams/edit-team");
    }

    @GetMapping("/delete/{teamId}")
    public ModelAndView deleteTeam(@PathVariable Long teamId, RedirectAttributes redirectAttrs){
        teamService.deleteTeam(teamId);
        redirectAttrs.addFlashAttribute("message", "Team deleted successfully");
        return new ModelAndView("redirect:/admin/teams/");
    }
}
