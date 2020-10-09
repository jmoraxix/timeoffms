//package com.timeoffms.web.other;
//
//import com.timeoffms.web.model.TimeOffRequestStatus;
//import com.timeoffms.web.other.TimeOffRequestStatusService;
//import lombok.extern.log4j.Log4j2;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@Log4j2
//@RequestMapping("/admin/timeoffrequest/status")
//public class TimeOffRequestStatusController {
//
//    @Autowired
//    private TimeOffRequestStatusService timeOffRequestStatusService;
//
//    @GetMapping
//    public ModelAndView listTimeOffRequestStatus(ModelMap model){
//        List<TimeOffRequestStatus> timeOffRequestStatusList = timeOffRequestStatusService.findAll();
//        model.addAttribute("statusList", timeOffRequestStatusList);
//        return new ModelAndView("timeOffRequestStatus/list-status", model);
//    }
//
//    @GetMapping("/create")
//    public ModelAndView showCreateTimeOffRequestStatus(ModelMap model){
//        model.addAttribute("status", new TimeOffRequestStatus());
//        model.addAttribute("editMode", "create");
//        return new ModelAndView("timeOffRequestStatus/edit-status", model);
//    }
//
//    @PostMapping("/create")
//    public ModelAndView saveCreateTimeOffRequestStatus(@Valid @ModelAttribute("role") TimeOffRequestStatus role, BindingResult result, RedirectAttributes redirectAttrs) {
//        timeOffRequestStatusService.saveTimeOffRequestStatus(role);
//        redirectAttrs.addFlashAttribute("message", "Time Off Request Status created successfully");
//        return new ModelAndView("redirect:/admin/timeoffrequest/status");
//    }
//
//    @GetMapping("/edit/{timeOffRequestStatusCode}")
//    public ModelAndView showEditTimeOffRequestStatus(@PathVariable String timeOffRequestStatusCode, ModelMap model){
//        TimeOffRequestStatus timeOffRequestStatus = timeOffRequestStatusService.findTimeOffRequestStatus(timeOffRequestStatusCode);
//        model.addAttribute("status", timeOffRequestStatus);
//        model.addAttribute("editMode", "edit");
//        return new ModelAndView("timeOffRequestStatus/edit-status", model);
//    }
//
//    @PostMapping("/edit/{timeOffRequestStatusCode}")
//    public ModelAndView saveEditTimeOffRequestStatus(@Valid @ModelAttribute("timeOffRequestStatusCode") TimeOffRequestStatus timeOffRequestStatusCode, BindingResult result, RedirectAttributes redirectAttrs) {
//        timeOffRequestStatusService.saveTimeOffRequestStatus(timeOffRequestStatusCode);
//        redirectAttrs.addFlashAttribute("message", "Time Off Request Status updated successfully");
//        return new ModelAndView("redirect:/admin/timeoffrequest/status");
//    }
//
//    @GetMapping("/delete/{timeOffRequestStatusCode}")
//    public ModelAndView deleteTimeOffRequestStatus(@PathVariable String timeOffRequestStatusCode, RedirectAttributes redirectAttrs){
//        timeOffRequestStatusService.deleteTimeOffRequestStatus(timeOffRequestStatusCode);
//        redirectAttrs.addFlashAttribute("message", "Time Off Request Status deleted successfully");
//        return new ModelAndView("redirect:/admin/timeoffrequest/status");
//    }
//}
