package com.blessy.dclmservice.controller;

import com.blessy.dclmservice.services.AppRoleService;
import com.blessy.dclmservice.services.AccountService;
import com.blessy.dclmservice.utils.WebPage;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
@AllArgsConstructor
public class HomeController {

	private final AccountService accountService;
	private final AppRoleService roleService;

	@RequestMapping({"", "/"})
	public String showHome() {
		return WebPage.HOME.getPageName();
	}

}
