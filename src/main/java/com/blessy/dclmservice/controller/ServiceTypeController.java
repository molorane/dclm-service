package com.blessy.dclmservice.controller;

import com.blessy.dclmservice.model.ServiceType;
import com.blessy.dclmservice.services.ServiceTypeService;
import com.blessy.dclmservice.utils.WebPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@RequiredArgsConstructor
@Controller
@RequestMapping("admin/services")
public class ServiceTypeController {

    private final ServiceTypeService serviceTypeService;

    @RequestMapping({"/",""})
    public String continents(Model model) {
        model.addAttribute("services", serviceTypeService.findAll());
        return WebPage.SERVICE_TYPES.getPageName();
    }

    @RequestMapping("/addService")
    public String addGetService(Model model) {
        model.addAttribute("service_type", new ServiceType());
        return WebPage.ADD_SERVICE_TYPE.getPageName();
    }

    @PostMapping("/addService")
    public String addPostService(@ModelAttribute("service_type") @Valid ServiceType serviceType, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return WebPage.ADD_SERVICE_TYPE.getPageName();
        }
        serviceTypeService.addServiceType(serviceType);
        model.addAttribute("saved", true);
        return WebPage.ADD_SERVICE_TYPE.getPageName();
    }

    @RequestMapping("/editService/{id}")
    public String editService(@PathVariable("id") Long id, Model model) {
        ServiceType serviceType = serviceTypeService.findById(id).get();
        model.addAttribute("service_type", serviceType);
        return WebPage.EDIT_SERVICE_TYPE.getPageName();
    }

    @PostMapping("/editService/{id}")
    public String addPostServiceType(@ModelAttribute("service_type") @Valid ServiceType serviceType, BindingResult bindingResult, Model model, @PathVariable("id") Long id) {
        if(bindingResult.hasErrors()) {
            return WebPage.EDIT_SERVICE_TYPE.getPageName();
        }
        serviceType.setId(id);
        serviceTypeService.editServiceType(serviceType);
        model.addAttribute("saved", true);
        return WebPage.EDIT_SERVICE_TYPE.getPageName();
    }

}
