package com.livebetter.controllers;
import com.livebetter.domain.Activities;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/activitieses")
@Controller
@RooWebScaffold(path = "activitieses", formBackingObject = Activities.class)
public class ActivitiesController {
}
