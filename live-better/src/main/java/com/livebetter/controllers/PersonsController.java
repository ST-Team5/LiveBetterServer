package com.livebetter.controllers;
import com.livebetter.domain.Persons;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/personses")
@Controller
@RooWebScaffold(path = "personses", formBackingObject = Persons.class)
public class PersonsController {
}
