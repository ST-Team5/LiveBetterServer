package com.livebetter.controllers;

import com.livebetter.domain.Activity;
import com.livebetter.domain.Person;
import com.livebetter.domain.PersonActivity;
import com.livebetter.services.ActivityService;
import com.livebetter.services.ActivityServiceImpl;
import com.livebetter.services.PersonActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
        java.util.List<Activity> activityList =  Activity.findAllActivitieses();
        for (Activity activity : activityList) {
            activity.setPersonActivitieses(null);
        }
        return activityList;
    }

	@RequestMapping(value = "list/frequent/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	java.util.List<Activity> listFrequent(@PathVariable("id") Long id) {
		java.util.List<Activity> activityList = Activity.findFrequentActivitiesForUser(id);
		for (Activity activity : activityList) {
			activity.setPersonActivitieses(null);
		}
		return activityList;
	}

	@RequestMapping(value = "list/recent/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
    java.util.List<Activity> listRecent(@PathVariable("id") Long id) {
		java.util.List<Activity> activityList = Activity.findRecentActivitiesForUser(id);
		for (Activity activity : activityList) {
			activity.setPersonActivitieses(null);
		}
		return activityList;
	}

    @RequestMapping(value = "/{id}", method=RequestMethod.POST, headers = {"Content-type=application/json"})
    public @ResponseBody void addPersonActivities(@PathVariable("id") Long id, @RequestBody List<Map<String, Object>> data) {
        final Person person = Person.findPersons(id);
        for (Map<String, Object> activityData : data) {
            Long activityId = Long.valueOf(((Number) activityData.get("id")).longValue());
            BigDecimal quantity =
                    BigDecimal.valueOf(((Number) activityData.get("quantity")).doubleValue())
                            .setScale(2, RoundingMode.HALF_UP);
            Activity activity = Activity.findActivities(activityId);
            PersonActivity personActivity = new PersonActivity();
            personActivity.setPersonId(person);
            personActivity.setIsConsumed(true);
            personActivity.setCreatedBy(id);
            personActivity.setCreatedDatetime(Calendar.getInstance());
            personActivity.setDatetimeOfConsumtion(Calendar.getInstance());
            personActivity.setActivityId(activity);
            personActivity.setQuantity(quantity);
            personActivityService.savePersonActivities(personActivity);
        }
    }
}
