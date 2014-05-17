package com.livebetter.controllers;
import com.livebetter.domain.Drinks;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/drinkses")
@Controller
@RooWebScaffold(path = "drinkses", formBackingObject = Drinks.class)
public class DrinksController {
}
