package com.livebetter.controllers;
import com.livebetter.domain.Person;
import com.livebetter.services.MetabolismService;
import com.livebetter.services.PersonActivityService;
import com.livebetter.services.PersonDrinkService;
import com.livebetter.services.PersonMealService;
import com.livebetter.services.PersonService;
import com.livebetter.services.UserToPersonService;
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

@RequestMapping("/personses")
@Controller
public class PersonController {

	@Autowired
    PersonService personService;

	@Autowired
    MetabolismService metabolismService;

	@Autowired
    PersonActivityService personActivityService;

	@Autowired
    PersonDrinkService personDrinkService;

	@Autowired
    PersonMealService personMealService;

	@Autowired
    UserToPersonService userToPersonService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Person person, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, person);
            return "personses/create";
        }
        uiModel.asMap().clear();
        personService.savePersons(person);
        return "redirect:/personses/" + encodeUrlPathSegment(person.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Person());
        return "personses/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("persons", personService.findPersons(id));
        uiModel.addAttribute("itemId", id);
        return "personses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("personses", Person.findPersonsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) personService.countAllPersonses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("personses", Person.findAllPersonses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "personses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Person person, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, person);
            return "personses/update";
        }
        uiModel.asMap().clear();
        personService.updatePersons(person);
        return "redirect:/personses/" + encodeUrlPathSegment(person.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, personService.findPersons(id));
        return "personses/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Person person = personService.findPersons(id);
        personService.deletePersons(person);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/personses";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("persons_dateofbirth_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("persons_createddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("persons_modifieddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Person person) {
        uiModel.addAttribute("persons", person);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("metabolismses", metabolismService.findAllMetabolismses());
        uiModel.addAttribute("personactivitieses", personActivityService.findAllPersonActivitieses());
        uiModel.addAttribute("persondrinkses", personDrinkService.findAllPersonDrinkses());
        uiModel.addAttribute("personmealses", personMealService.findAllPersonMealses());
        uiModel.addAttribute("userstopersonses", userToPersonService.findAllUsersToPersonses());
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
