package com.blessy.dclmservice.controller;

import com.blessy.dclmservice.model.CustomUserDetails;
import com.blessy.dclmservice.model.Account;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LockScreenController {
	
	
	@RequestMapping("/lockscreen")
	public ModelAndView lockScreen(Authentication authentication) {
		
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Account account = userDetails.getAccount();
		
		ModelAndView modelAndView = new ModelAndView("lock-screen");
		modelAndView.addObject("account", account);
		
		return modelAndView;
	}

}
