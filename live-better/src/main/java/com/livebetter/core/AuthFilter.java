package com.livebetter.core;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

public class AuthFilter extends UsernamePasswordAuthenticationFilter{

	@Override
	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
	    return ( StringUtils.hasText(obtainUsername(request)) && StringUtils.hasText(obtainPassword(request)) );
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	        Authentication authResult) throws IOException, ServletException{
	    super.successfulAuthentication(request, response, chain, authResult);
	    chain.doFilter(request, response);
	}
	
	
}
