package com.blessy.dclmservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private ApplicationAuthenticationSuccessHandler applicationAuthenticationSuccessHandler;

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {
		// TODO Auto-generated method stub

		try {
			applicationAuthenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
		} catch (ServletException e) {
			e.printStackTrace();
		}

		String targetUrl = determineTargetUrl(authentication);
		if (response.isCommitted()) {
			return;
		}
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		// TODO Auto-generated method stub

		String url = "/login";

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		List<String> roles = new ArrayList<>();

		authorities.forEach(role -> roles.add(role.getAuthority()));

		if (roles.size() > 0) {
			url = "/home";
		} else if (roles.size() == 0) {
			url = "/noroles";
		}
		return url;
	}

}
