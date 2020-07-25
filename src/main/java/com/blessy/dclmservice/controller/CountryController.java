package com.blessy.dclmservice.controller;


import com.blessy.dclmservice.model.Continent;
import com.blessy.dclmservice.model.Country;
import com.blessy.dclmservice.services.ContinentService;
import com.blessy.dclmservice.services.CountryService;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/countries")
public class CountryController {

    private final ContinentService continentService;
    private final CountryService countryService;

    @RequestMapping("addCountry/{continent_id}")
    public String addCountry(@PathVariable("continent_id") Long continent_id, Model model) {
        Continent continent = continentService.findById(continent_id).get();
        Country country = new Country();
        country.setContinent(continent);
        model.addAttribute("country", country);
        return WebPage.ADD_CONTINENT_COUNTRY.getPageName();
    }

    @PostMapping("addCountry/{continent_id}")
    public String addCountry(@ModelAttribute("country") @Valid Country country, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return WebPage.ADD_CONTINENT_COUNTRY.getPageName();
        }
        countryService.addCountry(country);
        model.addAttribute("saved", true);
        return WebPage.ADD_CONTINENT_COUNTRY.getPageName();
    }

    @RequestMapping("/{continent_id}")
    public String countriesInContinent(@PathVariable("continent_id") Long continent_id, Model model) {
        Continent continent = continentService.findById(continent_id).get();
        model.addAttribute("continent", continent);
        return WebPage.CONTINENT_COUNTRIES.getPageName();
    }
}
