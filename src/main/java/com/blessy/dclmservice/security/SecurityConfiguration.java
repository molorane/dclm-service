 package com.blessy.dclmservice.security;

 import com.blessy.dclmservice.services.impl.UserDetailsServiceImpl;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.builders.WebSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

 @Configuration
 @EnableWebSecurity
 public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

     @Autowired
     private UserDetailsServiceImpl userDetailsService;

     @Autowired
     private LoginSuccessHandler loginSuccessHandler;

     @Override
     protected void configure(HttpSecurity httpSecurity) throws Exception {


         httpSecurity
         .csrf().disable();

         // The pages does not require login
         httpSecurity
         .authorizeRequests()
         .antMatchers("/login", "/logout", "/register")
         .permitAll()

         // userInfo page requires login as ROLE_USER or ROLE_ADMIN.
         // If no login, it will redirect to /login page.
         .antMatchers("/private/account/*")
         .hasRole("ADMIN_USER")

         // Home page for everyone
         .antMatchers("/home","/profile")
         .authenticated()


         // Page for admin
         .antMatchers("/admin/**")
         .hasRole("ADMIN")
         .antMatchers("/private/account/*")
         .hasRole("ADMIN")


         // Configuration for Login Form
         .and()
         .formLogin()
         .loginPage("/login")
         .successHandler(loginSuccessHandler)
         .and()
         .rememberMe().tokenValiditySeconds(2592000) // Cookie valid for 30 days
         .key("123") // Use custome key 123 to hash

         // Configuration for access denied
         .and()
         .exceptionHandling()
         .accessDeniedPage("/access-denied")


         // Configuration for logout
         .and()
         .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
         .logoutSuccessUrl("/login?logout=true")
         .deleteCookies("JSESSIONID")
         .and().csrf().disable()
         .sessionManagement()
         .maximumSessions(1);
     }


     @Bean
     public BCryptPasswordEncoder passwordEncoder() {
         return  new BCryptPasswordEncoder();
     }

     @Autowired
     public void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
     }

     @Override
     public void configure(WebSecurity web) {
         web.ignoring().antMatchers("/resources/**");
     }


 //	@Autowired
 //	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
 //
 //		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
 //
 //		auth.inMemoryAuthentication()
 //		.withUser("philip").password(encoder.encode("123")).roles("USER")
 //		.and()
 //		.withUser("fedora").password(encoder.encode("123")).roles("USER","ADMIN");
 //	}



 //	@Override
 //	public void configure(AuthenticationManagerBuilder auth) throws Exception {
 //
 //		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
 //
 //		auth.inMemoryAuthentication()
 //		.withUser("philip").password(encoder.encode("123")).roles("USER")
 //		.and()
 //		.withUser("fedora").password(encoder.encode("123")).roles("USER","ADMIN");
 //	}
 }
