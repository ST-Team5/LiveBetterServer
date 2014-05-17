package com.livebetter.controllers;
import com.livebetter.domain.SystemUsers;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/systemuserses")
@Controller
@RooWebScaffold(path = "systemuserses", formBackingObject = SystemUsers.class)
public class SystemUsersController {
}
