package com.paweljarosz.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true, prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userService;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth
		.userDetailsService(userService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//if (securityProperties.isRequireSsl()) http.requiresChannel().anyRequest().requiresSecure();

		http.sessionManagement().maximumSessions(1).and().invalidSessionUrl("/login");

		http
		.authorizeRequests()

		.antMatchers(HttpMethod.GET,"/css/**","/images/**","/js/**").permitAll()
		.antMatchers("/font-awesome/**").permitAll()
		.antMatchers("/tinymce/**").permitAll()

		.antMatchers("/").permitAll()
		.antMatchers("/index").permitAll()
		.antMatchers("/404").permitAll()
		.antMatchers("/register").permitAll()
		.antMatchers("/forgot").permitAll()
		.antMatchers("/user/changePassword").permitAll()
		.antMatchers("/resetPasswordFailed").permitAll()
		.antMatchers("/policy").permitAll()
		.antMatchers("/learnMore").permitAll()
		.antMatchers("/learnMore").permitAll()

		.antMatchers("/gameDetails/**").permitAll()
		.antMatchers("/comments/**").permitAll()

		.antMatchers("/startGuide").permitAll()


		//.antMatchers("/admin/**").hasRole("ADMIN")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/login")
			.usernameParameter("usernameparam")
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/logout")
			.deleteCookies("remember-me")
			.logoutSuccessUrl("/")
			.permitAll();
		//.and();
		//.rememberMe();
	}


}
