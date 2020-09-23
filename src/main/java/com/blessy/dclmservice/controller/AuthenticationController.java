package com.blessy.dclmservice.controller;

import com.blessy.dclmservice.model.Account;
import com.blessy.dclmservice.services.AccountService;
import com.blessy.dclmservice.utils.WebPage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class AuthenticationController {

	private final AccountService accountService;

	@GetMapping({"/login","/"})
    public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    /* The user is logged in :) */
		    return "redirect:/home";
		}
		return WebPage.LOGIN.getPageName();
    }
	
	@GetMapping("/register")
    public String register(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Account user = new Account();
		model.addAttribute("user", user);
		if (!(auth instanceof AnonymousAuthenticationToken)) {
		    /* The user is logged in :) */
		    return "redirect:/home";
		}
		return WebPage.REGISTER.getPageName();
    }
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") @Valid Account saveUser, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return WebPage.REGISTER.getPageName();
		}

		Account user = accountService.findByUsername(saveUser.getUsername()).orElse(null);
		if(user != null) {
			model.addAttribute("error", "exist_username");
			return WebPage.REGISTER.getPageName();
		}
		
		user = accountService.findByEmail(saveUser.getEmail()).orElse(null);
		if(user != null) {
			model.addAttribute("error", "exist_email");
			return WebPage.REGISTER.getPageName();
		}

		accountService.addAccount(saveUser);
		
		return "redirect:/login";
	}

	@GetMapping("/access-denied")
    public String accessDenied() {
		return WebPage.ACCESS_DENIED.getPageName();
    }
	

}
