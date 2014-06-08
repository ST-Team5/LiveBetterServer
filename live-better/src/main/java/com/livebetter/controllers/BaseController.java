package com.livebetter.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livebetter.core.UserContextImpl;
import com.livebetter.domain.SystemUser;


@Controller
public class BaseController {
	
	@Autowired
	private UserContextImpl userContext;
	
	@RequestMapping(value = "/auth/authenticated", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<String, Boolean> getCurrentUserSummary() {
		
		SystemUser user = userContext.getUser();
		Map<String, Boolean> result = new HashMap<String, Boolean>();
//		result.put("authorized", user != null);
		result.put("authorized", true);
		return result;
	}
	
	
}
