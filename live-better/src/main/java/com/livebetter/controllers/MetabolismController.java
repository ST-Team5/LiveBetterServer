package com.livebetter.controllers;
import com.livebetter.domain.Metabolism;
import com.livebetter.services.MetabolismService;
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

@RequestMapping("/metabolismses")
@Controller
public class MetabolismController {

	@Autowired
    MetabolismService metabolismService;

	@Autowired
    PersonService personService;

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Metabolism metabolism, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, metabolism);
            return "metabolismses/create";
        }
        uiModel.asMap().clear();
        metabolismService.saveMetabolisms(metabolism);
        return "redirect:/metabolismses/" + encodeUrlPathSegment(metabolism.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Metabolism());
        return "metabolismses/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("metabolisms", metabolismService.findMetabolisms(id));
        uiModel.addAttribute("itemId", id);
        return "metabolismses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("metabolismses", Metabolism.findMetabolismsEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) metabolismService.countAllMetabolismses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("metabolismses", Metabolism.findAllMetabolismses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "metabolismses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Metabolism metabolism, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, metabolism);
            return "metabolismses/update";
        }
        uiModel.asMap().clear();
        metabolismService.updateMetabolisms(metabolism);
        return "redirect:/metabolismses/" + encodeUrlPathSegment(metabolism.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, metabolismService.findMetabolisms(id));
        return "metabolismses/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Metabolism metabolism = metabolismService.findMetabolisms(id);
        metabolismService.deleteMetabolisms(metabolism);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/metabolismses";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("metabolisms_createddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("metabolisms_modifieddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Metabolism metabolism) {
        uiModel.addAttribute("metabolisms", metabolism);
        addDateTimeFormatPatterns(uiModel);
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
