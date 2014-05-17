package com.livebetter.controllers;
import com.livebetter.domain.PersonDrink;
import com.livebetter.services.DrinkService;
import com.livebetter.services.PersonDrinkService;
import com.livebetter.services.PersonService;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@RequestMapping("/persondrinkses")
@Controller
public class PersonDrinkController {

	@Autowired
    PersonDrinkService personDrinkService;

	@Autowired
    DrinkService drinkService;

	@Autowired
    PersonService personService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid PersonDrink personDrink, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, personDrink);
            return "persondrinkses/create";
        }
        uiModel.asMap().clear();
        personDrinkService.savePersonDrinks(personDrink);
        return "redirect:/persondrinkses/" + encodeUrlPathSegment(personDrink.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new PersonDrink());
        return "persondrinkses/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("persondrinks", personDrinkService.findPersonDrinks(id));
        uiModel.addAttribute("itemId", id);
        return "persondrinkses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("persondrinkses", PersonDrink.findPersonDrinksEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) personDrinkService.countAllPersonDrinkses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("persondrinkses", PersonDrink.findAllPersonDrinkses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "persondrinkses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid PersonDrink personDrink, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, personDrink);
            return "persondrinkses/update";
        }
        uiModel.asMap().clear();
        personDrinkService.updatePersonDrinks(personDrink);
        return "redirect:/persondrinkses/" + encodeUrlPathSegment(personDrink.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, personDrinkService.findPersonDrinks(id));
        return "persondrinkses/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        PersonDrink personDrink = personDrinkService.findPersonDrinks(id);
        personDrinkService.deletePersonDrinks(personDrink);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/persondrinkses";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("personDrinks_datetimeofconsumtion_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("personDrinks_createddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, PersonDrink personDrink) {
        uiModel.addAttribute("personDrinks", personDrink);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("drinkses", drinkService.findAllDrinkses());
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
