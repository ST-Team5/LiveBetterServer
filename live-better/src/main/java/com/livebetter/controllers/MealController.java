package com.livebetter.controllers;

import com.livebetter.domain.*;
import com.livebetter.services.MealService;
import com.livebetter.services.PersonMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/meals")
public class MealController {

	@Autowired
    MealService mealService;

	@Autowired
    PersonMealService personMealService;
//
//	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
//    public String create(@Valid Meal meal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
//        if (bindingResult.hasErrors()) {
//            populateEditForm(uiModel, meal);
//            return "mealses/create";
//        }
//        uiModel.asMap().clear();
//        mealService.saveMeals(meal);
//        return "redirect:/mealses/" + encodeUrlPathSegment(meal.getId().toString(), httpServletRequest);
//    }
//
//	@RequestMapping(params = "form", produces = "text/html")
//    public String createForm(Model uiModel) {
//        populateEditForm(uiModel, new Meal());
//        return "mealses/create";
//    }
//
//	@RequestMapping(value = "/{id}", produces = "text/html")
//    public String show(@PathVariable("id") Long id, Model uiModel) {
//        addDateTimeFormatPatterns(uiModel);
//        uiModel.addAttribute("meals", mealService.findMeals(id));
//        uiModel.addAttribute("itemId", id);
//        return "mealses/show";
//    }
//
//	@RequestMapping(produces = "text/html")
//    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
//        if (page != null || size != null) {
//            int sizeNo = size == null ? 10 : size.intValue();
//            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
//            uiModel.addAttribute("mealses", Meal.findMealsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
//            float nrOfPages = (float) mealService.countAllMealses() / sizeNo;
//            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
//        } else {
//            uiModel.addAttribute("mealses", Meal.findAllMealses(sortFieldName, sortOrder));
//        }
//        addDateTimeFormatPatterns(uiModel);
//        return "mealses/list";
//    }
//
//	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
//    public String update(@Valid Meal meal, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
//        if (bindingResult.hasErrors()) {
//            populateEditForm(uiModel, meal);
//            return "mealses/update";
//        }
//        uiModel.asMap().clear();
//        mealService.updateMeals(meal);
//        return "redirect:/mealses/" + encodeUrlPathSegment(meal.getId().toString(), httpServletRequest);
//    }
//
//	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
//    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
//        populateEditForm(uiModel, mealService.findMeals(id));
//        return "mealses/update";
//    }
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
//    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
//        Meal meal = mealService.findMeals(id);
//        mealService.deleteMeals(meal);
//        uiModel.asMap().clear();
//        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
//        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
//        return "redirect:/mealses";
//    }
//
//	void addDateTimeFormatPatterns(Model uiModel) {
//        uiModel.addAttribute("meals_createddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
//        uiModel.addAttribute("meals_modifieddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
//    }
//
//	void populateEditForm(Model uiModel, Meal meal) {
//        uiModel.addAttribute("meals", meal);
//        addDateTimeFormatPatterns(uiModel);
//        uiModel.addAttribute("personmealses", personMealService.findAllPersonMealses());
//    }
//
//	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
//        String enc = httpServletRequest.getCharacterEncoding();
//        if (enc == null) {
//            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
//        }
//        try {
//            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
//        } catch (UnsupportedEncodingException uee) {}
//        return pathSegment;
//    }

    @RequestMapping(value = "insert", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public @ResponseBody
    void insertMeal(@RequestBody Meal mealAsJson) {
        Meal meal = new Meal();
        meal.setName(mealAsJson.getName());
        meal.setCalories(mealAsJson.getCalories());
        meal.setCarbohydrates(mealAsJson.getCarbohydrates());
        meal.setFat(mealAsJson.getFat());
        meal.setProteins(mealAsJson.getProteins());
        meal.setCreatedBy(1337l);
        meal.setCreatedDatetime(Calendar.getInstance());
        meal.setModifiedDatetime(Calendar.getInstance());
        meal.setType(mealAsJson.getType());
        mealService.saveMeals(meal);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Meal> list() {
        final List<Meal> mealList = Meal.findAllMealses();
        for (Meal meal : mealList) {
            meal.setPersonMealss(null);
        }
        return mealList;
    }

	@RequestMapping(value = "list/frequent/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	java.util.List<Meal> listFrequent(@PathVariable("id") Long id) {
		final List<Meal> mealList = Meal.findFrequentMealsForUser(id);
		for (Meal meal : mealList) {
			meal.setPersonMealss(null);
		}
		return mealList;
	}

	@RequestMapping(value = "list/recent/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	java.util.List<Meal> listRecent(@PathVariable("id") Long id) {
		final List<Meal> mealList = Meal.findRecentMealssForUser(id);
		for (Meal meal : mealList) {
			meal.setPersonMealss(null);
		}
		return mealList;
	}

    @RequestMapping(value = "/{id}", method=RequestMethod.POST, headers = {"Content-type=application/json"})
    public @ResponseBody void addPersonMeals(@PathVariable("id") Long id, @RequestBody Long[] mealIds) {
        final Person person = Person.findPersons(id);
        for (Long mealId : mealIds) {
            Meal meal = Meal.findMeals(mealId);
            PersonMeal personMeal = new PersonMeal();
            personMeal.setCreatedBy(id);
            personMeal.setQuantity(BigDecimal.ONE);
            personMeal.setDatetimeOfConsumtion(Calendar.getInstance());
            personMeal.setCreatedDatetime(Calendar.getInstance());
            personMeal.setIsConsumed(true);
            personMeal.setMealId(meal);
            personMeal.setPersonId(person);
            personMealService.savePersonMeals(personMeal);
        }
    }
}
