package com.livebetter.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.livebetter.domain.SystemUser;
import com.livebetter.services.SystemUserService;

public class UserContextImpl implements UserContext{

	@Autowired
	private SystemUserService systemUserService;
	
	@Override
	public SystemUser getUser() {
		SystemUser currentUser = null;
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			System.out.println("Principal : " + principal.toString());
			if(principal instanceof UserDetails){
				currentUser =  systemUserService.findSystemUserByUserName(((UserDetails)principal).getUsername());
			}
			
			//Hardcoded user
			if(currentUser == null){
				currentUser = systemUserService.findSystemUsers(1l);
			}

		return currentUser;
	}

}
