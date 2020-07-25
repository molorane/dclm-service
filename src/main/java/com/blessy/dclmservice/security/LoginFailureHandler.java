package com.blessy.dclmservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Configuration
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

}
