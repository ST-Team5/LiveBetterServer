package com.livebetter.controllers;
import com.livebetter.domain.SystemUserRoles;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/systemuserroleses")
@Controller
@RooWebScaffold(path = "systemuserroleses", formBackingObject = SystemUserRoles.class)
public class SystemUserRolesController {
}
