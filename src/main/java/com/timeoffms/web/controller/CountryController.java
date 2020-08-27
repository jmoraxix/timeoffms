package com.timeoffms.web.controller;

import com.timeoffms.web.model.Country;
import com.timeoffms.web.service.CountryService;
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
@RequestMapping("/admin/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ModelAndView listCountries(ModelMap model){
        List<Country> countryList = countryService.findAll();
        model.addAttribute("countryList", countryList);
        return new ModelAndView("countries/list-countries", model);
    }
    
    @GetMapping("/create")
    public ModelAndView showCreateCountry(ModelMap model){
        model.addAttribute("country", new Country());
        model.addAttribute("editMode", "create");
        return new ModelAndView("countries/edit-country", model);
    }

    @PostMapping("/create")
    public ModelAndView saveCreateCountry(@Valid @ModelAttribute("country") Country country, BindingResult result, RedirectAttributes redirectAttrs) {
        countryService.saveCountry(country);
        redirectAttrs.addFlashAttribute("message", "Country created successfully");
        return new ModelAndView("redirect:/admin/countries");
    }

    @GetMapping("/edit/{countryCode}")
    public ModelAndView showEditCountry(@PathVariable String countryCode, ModelMap model){
        model.addAttribute("country", countryService.findCountry(countryCode));
        model.addAttribute("editMode", "edit");
        return new ModelAndView("countries/edit-country", model);
    }

    @PostMapping("/edit/{countryCode}")
    public ModelAndView saveEditCountry(@Valid @ModelAttribute("country") Country country, BindingResult result, RedirectAttributes redirectAttrs) {
        countryService.saveCountry(country);
        redirectAttrs.addFlashAttribute("message", "Country updated successfully");
        return new ModelAndView("redirect:/admin/countries");
    }

    @GetMapping("/delete/{countryCode}")
    public ModelAndView deleteCountry(@PathVariable String countryCode, RedirectAttributes redirectAttrs){
        countryService.deleteCountry(countryCode);
        redirectAttrs.addFlashAttribute("message", "Country deleted successfully");
        return new ModelAndView("redirect:/admin/countries");
    }
}
