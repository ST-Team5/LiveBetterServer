package com.livebetter.controllers;
import com.livebetter.domain.PersonMeals;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/personmealses")
@Controller
@RooWebScaffold(path = "personmealses", formBackingObject = PersonMeals.class)
public class PersonMealsController {
}
