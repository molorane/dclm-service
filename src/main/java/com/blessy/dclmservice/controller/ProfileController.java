package com.blessy.dclmservice.controller;

import com.blessy.dclmservice.config.DenominationProperties;
import com.blessy.dclmservice.model.Account;
import com.blessy.dclmservice.services.AccountService;
import com.blessy.dclmservice.services.AppRoleService;
import com.blessy.dclmservice.utils.WebPage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
@SessionAttributes("session")
public class ProfileController {

	private final AccountService accountService;
	private final AppRoleService roleService;

	@RequestMapping
	public String profile(Model model, HttpSession httpSession) {
		model.addAttribute("authenticated_user", httpSession.getAttribute("authenticated_user"));
		return WebPage.PROFILE.getPageName();
	}

	@RequestMapping("/change_password")
	public String changePassword() {
		return WebPage.CHANGE_PASSWORD.getPageName();
	}

}
