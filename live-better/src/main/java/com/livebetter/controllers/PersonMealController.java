package com.livebetter.controllers;
import com.livebetter.domain.PersonMeal;
import com.livebetter.services.MealService;
import com.livebetter.services.PersonMealService;
import com.livebetter.services.PersonService;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/personmealses")
@Controller
@RooWebScaffold(path = "personmealses", formBackingObject = PersonMeal.class)
public class PersonMealController {

	@Autowired
    PersonMealService personMealService;

	@Autowired
    MealService mealService;

	@Autowired
    PersonService personService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid PersonMeal personMeal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, personMeal);
            return "personmealses/create";
        }
        uiModel.asMap().clear();
        personMealService.savePersonMeals(personMeal);
        return "redirect:/personmealses/" + encodeUrlPathSegment(personMeal.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new PersonMeal());
        return "personmealses/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("personmeals", personMealService.findPersonMeals(id));
        uiModel.addAttribute("itemId", id);
        return "personmealses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("personmealses", PersonMeal.findPersonMealsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) personMealService.countAllPersonMealses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("personmealses", PersonMeal.findAllPersonMealses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "personmealses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PersonMeal personMeal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, personMeal);
            return "personmealses/update";
        }
        uiModel.asMap().clear();
        personMealService.updatePersonMeals(personMeal);
        return "redirect:/personmealses/" + encodeUrlPathSegment(personMeal.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, personMealService.findPersonMeals(id));
        return "personmealses/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PersonMeal personMeal = personMealService.findPersonMeals(id);
        personMealService.deletePersonMeals(personMeal);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/personmealses";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("personMeals_datetimeofconsumtion_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("personMeals_createddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, PersonMeal personMeal) {
        uiModel.addAttribute("personMeals", personMeal);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("mealses", mealService.findAllMealses());
        uiModel.addAttribute("personses", personService.findAllPersonses());
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
}
