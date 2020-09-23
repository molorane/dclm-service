package com.blessy.dclmservice.security;

import com.blessy.dclmservice.config.DenominationProperties;
import com.blessy.dclmservice.model.CustomUserDetails;
import com.blessy.dclmservice.model.Account;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class ApplicationAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final int SESSION_INACTIVE_INTERVAL_HOURS = 2;
    public static final int SESSION_INACTIVE_INTERVAL_SECONDS = SESSION_INACTIVE_INTERVAL_HOURS * 60 * 60;

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    DenominationProperties denominationProperties;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication
            authentication) throws IOException, ServletException {
    	CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Account account = userDetails.getAccount();
        HttpSession session = request.getSession();
        session.setAttribute("authenticated_user", account);
        session.setAttribute("denomination",denominationProperties);
        session.setMaxInactiveInterval(SESSION_INACTIVE_INTERVAL_SECONDS); // Possible to have this value change between Roles

        logger.info(String.format("User %s successfully logged in", account.getEmail()));

        super.onAuthenticationSuccess(request, response, authentication);
    }

}
