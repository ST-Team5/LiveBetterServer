package com.livebetter.controllers;
import com.livebetter.domain.*;
import com.livebetter.services.DrinkService;
import com.livebetter.services.PersonDrinkService;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
@RequestMapping("/drinks")
public class DrinkController {

	@Autowired
    DrinkService drinkService;

	@Autowired
    PersonDrinkService personDrinkService;
//
//	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
//    public String create(@Valid Drink drink, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
//        if (bindingResult.hasErrors()) {
//            populateEditForm(uiModel, drink);
//            return "drinkses/create";
//        }
//        uiModel.asMap().clear();
//        drinkService.saveDrinks(drink);
//        return "redirect:/drinkses/" + encodeUrlPathSegment(drink.getId().toString(), httpServletRequest);
//    }
//
//	@RequestMapping(params = "form", produces = "text/html")
//    public String createForm(Model uiModel) {
//        populateEditForm(uiModel, new Drink());
//        return "drinkses/create";
//    }
//
//	@RequestMapping(value = "/{id}", produces = "text/html")
//    public String show(@PathVariable("id") Long id, Model uiModel) {
//        addDateTimeFormatPatterns(uiModel);
//        uiModel.addAttribute("drinks", drinkService.findDrinks(id));
//        uiModel.addAttribute("itemId", id);
//        return "drinkses/show";
//    }
//
//	@RequestMapping(produces = "text/html")
//    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
//        if (page != null || size != null) {
//            int sizeNo = size == null ? 10 : size.intValue();
//            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
//            uiModel.addAttribute("drinkses", Drink.findDrinksEntries(firstResult, sizeNo, sortFieldName, sortOrder));
//            float nrOfPages = (float) drinkService.countAllDrinkses() / sizeNo;
//            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
//        } else {
//            uiModel.addAttribute("drinkses", Drink.findAllDrinkses(sortFieldName, sortOrder));
//        }
//        addDateTimeFormatPatterns(uiModel);
//        return "drinkses/list";
//    }
//
//	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
//    public String update(@Valid Drink drink, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
//        if (bindingResult.hasErrors()) {
//            populateEditForm(uiModel, drink);
//            return "drinkses/update";
//        }
//        uiModel.asMap().clear();
//        drinkService.updateDrinks(drink);
//        return "redirect:/drinkses/" + encodeUrlPathSegment(drink.getId().toString(), httpServletRequest);
//    }
//
//	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
//    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
//        populateEditForm(uiModel, drinkService.findDrinks(id));
//        return "drinkses/update";
//    }
//
//	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
//    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
//        Drink drink = drinkService.findDrinks(id);
//        drinkService.deleteDrinks(drink);
//        uiModel.asMap().clear();
//        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
//        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
//        return "redirect:/drinkses";
//    }
//
//	void addDateTimeFormatPatterns(Model uiModel) {
//        uiModel.addAttribute("drinks_createddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
//        uiModel.addAttribute("drinks_modifieddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
//    }
//
//	void populateEditForm(Model uiModel, Drink drink) {
//        uiModel.addAttribute("drinks", drink);
//        addDateTimeFormatPatterns(uiModel);
//        uiModel.addAttribute("persondrinkses", personDrinkService.findAllPersonDrinkses());
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
    void insertDrink(@RequestBody Drink drinkAsJson) {
        Drink drink = new Drink();
        drink.setName(drinkAsJson.getName());
        drink.setCalories(drinkAsJson.getCalories());
        drink.setCarbohydrates(drinkAsJson.getCarbohydrates());
        drink.setFat(drinkAsJson.getFat());
        drink.setProteins(drinkAsJson.getProteins());
        drink.setCreatedBy(1337l);
        drink.setCreatedDatetime(Calendar.getInstance());
        drink.setModifiedDatetime(Calendar.getInstance());
        drink.setType(drinkAsJson.getType());
        drinkService.saveDrinks(drink);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    List<Drink> list() {
        final List<Drink> drinkList = Drink.findAllDrinkses();
        for (Drink drink : drinkList) {
            drink.setPersonDrinkss(null);
        }
        return drinkList;
    }

	@RequestMapping(value = "list/frequent/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	java.util.List<Drink> listFrequent(@PathVariable("id") Long id) {
		final List<Drink> drinkList = Drink.findFrequentDrinksForUser(id);
		for (Drink drink : drinkList) {
			drink.setPersonDrinkss(null);
		}
		return drinkList;
	}

	@RequestMapping(value = "list/recent/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	java.util.List<Drink> listRecent(@PathVariable("id") Long id) {
		final List<Drink> drinkList = Drink.findRecentDrinksForUser(id);
		for (Drink drink : drinkList) {
			drink.setPersonDrinkss(null);
		}
		return drinkList;
	}

    @RequestMapping(value = "/{id}", method=RequestMethod.POST, headers = {"Content-type=application/json"})
    public @ResponseBody void addPersonDrinks(@PathVariable("id") Long id, @RequestBody Long[] drinkIds) {
        final Person person = Person.findPersons(id);
        for (Long drinkId : drinkIds) {
            Drink drink = Drink.findDrinks(drinkId);
            PersonDrink personDrink = new PersonDrink();
            personDrink.setCreatedBy(id);
            personDrink.setPersonId(person);
            personDrink.setIsConsumed(true);
            personDrink.setCreatedDatetime(Calendar.getInstance());
            personDrink.setDrinkId(drink);
            personDrink.setDatetimeOfConsumtion(Calendar.getInstance());
            personDrink.setQuantity(BigDecimal.ONE);
            personDrinkService.savePersonDrinks(personDrink);
        }
    }
}
