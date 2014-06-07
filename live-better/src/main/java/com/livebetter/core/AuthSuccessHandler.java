package com.livebetter.core;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.livebetter.services.SystemUserService;

public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Autowired
	private SystemUserService systemUserService;
	
	private final String defaultRedirectUrl = "/LiveBetterClient/#/";
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Object principal = authentication.getPrincipal();
		String redirectUrl;
		
		if(principal instanceof UserDetails){
			redirectUrl = getRedirectUrlBasedOnAuthorities(((UserDetails)principal).getAuthorities());
		}else if (principal instanceof User){
			redirectUrl = getRedirectUrlBasedOnAuthorities(((User) principal).getAuthorities());
		}else{
			redirectUrl = defaultRedirectUrl;
		}
		
		response.sendRedirect(redirectUrl);
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	
	private String getRedirectUrlBasedOnAuthorities(Collection<? extends GrantedAuthority> authorities){
		if(authorities != null && !authorities.isEmpty()){
			for(GrantedAuthority authority :authorities){
				if("ROLE_SUPER_ADMIN".equals(authority.getAuthority())){
					//TODO
					return defaultRedirectUrl;
				}else{
					return defaultRedirectUrl;
				}
			}
		}
		return defaultRedirectUrl;
	}
}
