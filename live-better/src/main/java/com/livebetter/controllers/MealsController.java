package com.livebetter.controllers;
import com.livebetter.domain.Meals;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/mealses")
@Controller
@RooWebScaffold(path = "mealses", formBackingObject = Meals.class)
public class MealsController {
}
