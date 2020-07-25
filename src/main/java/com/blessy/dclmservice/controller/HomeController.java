package com.blessy.dclmservice.controller;

import com.blessy.dclmservice.services.RoleService;
import com.blessy.dclmservice.services.UserService;
import com.blessy.dclmservice.utils.WebPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

	private final UserService userService;
	private final RoleService roleService;

	@Autowired
	public HomeController(UserService userService, RoleService roleService) {
		this.roleService = roleService;
		this.userService = userService;
	}

	@RequestMapping({"", "/"})
	public String showHome() {
		return WebPage.HOME.getPageName();
	}

}
