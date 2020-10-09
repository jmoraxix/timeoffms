//package com.timeoffms.web.other;
//
//import com.timeoffms.web.model.TimeOffRequestType;
//import com.timeoffms.web.other.TimeOffRequestTypeService;
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
//@RequestMapping("/admin/timeoffrequest/type")
//public class TimeOffRequestTypeController {
//
//    @Autowired
//    private TimeOffRequestTypeService timeOffRequestTypeService;
//
//    @GetMapping
//    public ModelAndView listTimeOffRequestTypes(ModelMap model){
//        List<TimeOffRequestType> timeOffRequestTypeList = timeOffRequestTypeService.findAll();
//        model.addAttribute("typeList", timeOffRequestTypeList);
//        return new ModelAndView("timeOffRequestType/list-type", model);
//    }
//
//    @GetMapping("/create")
//    public ModelAndView showCreateTimeOffRequestType(ModelMap model){
//        model.addAttribute("type", new TimeOffRequestType());
//        model.addAttribute("editMode", "create");
//        return new ModelAndView("timeOffRequestType/edit-type", model);
//    }
//
//    @PostMapping("/create")
//    public ModelAndView saveCreateTimeOffRequestType(@Valid @ModelAttribute("role") TimeOffRequestType role, BindingResult result, RedirectAttributes redirectAttrs) {
//        timeOffRequestTypeService.saveTimeOffRequestType(role);
//        redirectAttrs.addFlashAttribute("message", "Time Off Request Type created successfully");
//        return new ModelAndView("redirect:/admin/timeoffrequest/type");
//    }
//
//    @GetMapping("/edit/{timeOffRequestTypeCode}")
//    public ModelAndView showEditTimeOffRequestType(@PathVariable String timeOffRequestTypeCode, ModelMap model){
//        TimeOffRequestType timeOffRequestType = timeOffRequestTypeService.findTimeOffRequestType(timeOffRequestTypeCode);
//        model.addAttribute("type", timeOffRequestType);
//        model.addAttribute("editMode", "edit");
//        return new ModelAndView("timeOffRequestType/edit-type", model);
//    }
//
//    @PostMapping("/edit/{timeOffRequestTypeCode}")
//    public ModelAndView saveEditTimeOffRequestType(@Valid @ModelAttribute("timeOffRequestTypeCode") TimeOffRequestType timeOffRequestTypeCode, BindingResult result, RedirectAttributes redirectAttrs) {
//        timeOffRequestTypeService.saveTimeOffRequestType(timeOffRequestTypeCode);
//        redirectAttrs.addFlashAttribute("message", "Time Off Request Type updated successfully");
//        return new ModelAndView("redirect:/admin/timeoffrequest/type");
//    }
//
//    @GetMapping("/delete/{timeOffRequestTypeCode}")
//    public ModelAndView deleteTimeOffRequestType(@PathVariable String timeOffRequestTypeCode, RedirectAttributes redirectAttrs){
//        timeOffRequestTypeService.deleteTimeOffRequestType(timeOffRequestTypeCode);
//        redirectAttrs.addFlashAttribute("message", "Time Off Request Type deleted successfully");
//        return new ModelAndView("redirect:/admin/timeoffrequest/type");
//    }
//}
