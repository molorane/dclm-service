package com.blessy.dclmservice.controller;

import com.blessy.dclmservice.model.Denomination;
import com.blessy.dclmservice.services.DenominationService;
import com.blessy.dclmservice.utils.WebPage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@RequestMapping("admin/denomination")
public class DenominationController {

    private final DenominationService denominationService;

    @RequestMapping("about")
    public String about(Model model) {
        Denomination denomination = denominationService.getDenomination(1);
        model.addAttribute("denomination", denomination);
        return WebPage.DENOMINATION.getPageName();
    }

    @PostMapping("/about")
    public String about(@ModelAttribute("denomination") @Valid Denomination denomination, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return WebPage.DENOMINATION.getPageName();
        }

        denominationService.updateDenomination(denomination);
        model.addAttribute("saved", true);
        return WebPage.DENOMINATION.getPageName();
    }

}
