package com.livebetter.controllers;
import antlr.collections.List;

import com.livebetter.domain.*;
import com.livebetter.services.ActivityService;
import com.livebetter.services.ActivityServiceImpl;
import com.livebetter.services.PersonActivityService;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/activities")
public class ActivityController {

	@Autowired
    ActivityService activityService = new ActivityServiceImpl();

	@Autowired
    PersonActivityService personActivityService;
	/*
	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Activity activity, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, activity);
            return "activitieses/create";
        }
        uiModel.asMap().clear();
        activityService.saveActivities(activity);
        return "redirect:/activitieses/" + encodeUrlPathSegment(activity.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Activity());
        return "activitieses/create";
    }

	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("activities", activityService.findActivities(id));
        uiModel.addAttribute("itemId", id);
        return "activitieses/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("activitieses", Activity.findActivitiesEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) activityService.countAllActivitieses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("activitieses", Activity.findAllActivitieses(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "activitieses/list";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Activity activity, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, activity);
            return "activitieses/update";
        }
        uiModel.asMap().clear();
        activityService.updateActivities(activity);
        return "redirect:/activitieses/" + encodeUrlPathSegment(activity.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, activityService.findActivities(id));
        return "activitieses/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Activity activity = activityService.findActivities(id);
        activityService.deleteActivities(activity);
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/activitieses";
    }

	void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("activities_createddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("activities_modifieddatetime_date_format", DateTimeFormat.patternForStyle("MM", LocaleContextHolder.getLocale()));
    }

	void populateEditForm(Model uiModel, Activity activity) {
        uiModel.addAttribute("activities", activity);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("personactivitieses", personActivityService.findAllPersonActivitieses());
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
	*/
	
	@RequestMapping(value = "insert-new-type", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public @ResponseBody void InsertActivity(@RequestBody Activity activityAsJson) {

		Activity act = new Activity();
		act.setName(activityAsJson.getName());
		act.setType(activityAsJson.getType());
		act.setCaloriesPerHour(activityAsJson.getCaloriesPerHour());

		activityService.saveActivities(act); 
    }

    @RequestMapping(value = "list", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    java.util.List<Activity> list() {
        return Activity.findAllActivitieses();
    }

    @RequestMapping(value = "/{id}", method=RequestMethod.POST, headers = {"Content-type=application/json"}, produces = "application/json")
    public void addPersonActivities(@PathVariable("id") Long id, @RequestBody Long[] activityIds) {
        final Person person = Person.findPersons(id);
        for (Long activityId : activityIds) {
            Activity activity = Activity.findActivities(activityId);
            PersonActivity personActivity = new PersonActivity();
            personActivity.setPersonId(person);
            personActivity.setIsConsumed(true);
            personActivity.setCreatedBy(id);
            personActivity.setCreatedDatetime(Calendar.getInstance());
            personActivity.setDatetimeOfConsumtion(Calendar.getInstance());
            personActivity.setActivityId(activity);
            personActivity.setQuantity(BigDecimal.ONE);
            personActivityService.savePersonActivities(personActivity);
        }
    }
}
