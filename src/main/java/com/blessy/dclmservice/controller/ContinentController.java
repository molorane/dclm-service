package com.blessy.dclmservice.controller;



import com.blessy.dclmservice.model.Continent;
import com.blessy.dclmservice.model.Country;
import com.blessy.dclmservice.model.State;
import com.blessy.dclmservice.services.CityService;
import com.blessy.dclmservice.services.ContinentService;
import com.blessy.dclmservice.services.CountryService;
import com.blessy.dclmservice.services.StateService;
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
@RequestMapping("admin/continents")
@RequiredArgsConstructor
public class ContinentController {

    private final ContinentService continentService;
    private final CountryService countryService;
    private final CityService cityService;
    private final StateService stateService;

    @RequestMapping
    public String retrieveAllContinents(Model model) {
        model.addAttribute("continents", continentService.findAll());
        return WebPage.CONTINENTS.getPageName();
    }

    @RequestMapping("addContinent")
    public String addGetContinent(Model model) {
        model.addAttribute("continent", new Continent());
        return WebPage.ADD_CONTINENT.getPageName();
    }

    @PostMapping("addContinent")
    public String addPostContinent(@ModelAttribute("continent") @Valid Continent continent, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return WebPage.ADD_CONTINENT.getPageName();
        }
        continentService.addContinent(continent);
        model.addAttribute("saved", true);
        return WebPage.ADD_CONTINENT.getPageName();
    }

    @RequestMapping("{continent_id}")
    public String countriesInContinent(@PathVariable("continent_id") Long continent_id, Model model) {
        Continent continent = continentService.findById(continent_id).get();
        model.addAttribute("continent", continent);
        return WebPage.CONTINENT_COUNTRIES.getPageName();
    }

    @RequestMapping("{continent_id}/{country_id}")
    public String countryStates(@PathVariable("country_id") Long country_id, Model model) {
        Country country = countryService.findById(country_id).get();
        model.addAttribute("country", country);
        return WebPage.CONTINENT_COUNTRY_STATES.getPageName();
    }

    @RequestMapping("{continent_id}/{country_id}/{state_id}")
    public String stateCities(@PathVariable("state_id") Long state_id, Model model) {
        State state = stateService.findById(state_id).get();
        model.addAttribute("state", state);
        return WebPage.CONTINENT_COUNTRY_STATE_CITIES.getPageName();
    }

}
