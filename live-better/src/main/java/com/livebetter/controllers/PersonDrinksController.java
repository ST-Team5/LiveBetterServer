package com.livebetter.controllers;
import com.livebetter.domain.PersonDrinks;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/persondrinkses")
@Controller
@RooWebScaffold(path = "persondrinkses", formBackingObject = PersonDrinks.class)
public class PersonDrinksController {
}
