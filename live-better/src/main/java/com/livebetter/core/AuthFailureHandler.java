package com.livebetter.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.livebetter.services.SystemUserService;


public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	@Autowired
	private SystemUserService systemUserService;
	
	public AuthFailureHandler() {
		setDefaultFailureUrl("/login.jsp?authentication_error=t");
	}
	
	
}
