package com.blessy.dclmservice.controller;

import com.blessy.dclmservice.config.DenominationProperties;
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

    private final DenominationProperties denominationProperties;

    @RequestMapping("about")
    public String about(Model model) {
        model.addAttribute("denomination", denominationProperties);
        return WebPage.DENOMINATION.getPageName();
    }

}
