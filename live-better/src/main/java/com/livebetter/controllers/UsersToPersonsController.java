package com.livebetter.controllers;
import com.livebetter.domain.UsersToPersons;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/userstopersonses")
@Controller
@RooWebScaffold(path = "userstopersonses", formBackingObject = UsersToPersons.class)
public class UsersToPersonsController {
}
