package com.livebetter.controllers;
import com.livebetter.domain.Drink;
import com.livebetter.domain.Meal;
import com.livebetter.services.MealService;
import com.livebetter.services.PersonMealService;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/meals")
public class MealController {

	@Autowired
    MealService mealService;
//
//	@Autowired
//    PersonMealService personMealService;
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
        return Meal.findAllMealses();
    }
}
