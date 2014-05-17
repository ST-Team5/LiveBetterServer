package com.livebetter.controllers;
import com.livebetter.domain.Metabolisms;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/metabolismses")
@Controller
@RooWebScaffold(path = "metabolismses", formBackingObject = Metabolisms.class)
public class MetabolismsController {
}
