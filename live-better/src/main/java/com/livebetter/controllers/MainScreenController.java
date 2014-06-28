package com.livebetter.controllers;

import com.livebetter.domain.Person;
import com.livebetter.services.PersonService;
import com.livebetter.services.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("mainScreen")
public class MainScreenController {
    @Autowired
    PersonService personService = new PersonServiceImpl();

    @RequestMapping(value = "{uid}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Map<String, Object> mainScreenForToday(@PathVariable("uid") Long id) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("caloriesConsumed", Person.getPersonCaloricIntakeForLast24Hours(id));
        result.put("caloriesBurned", Person.getPersonBurnedCaloriesForLast24Hours(id));
        result.put("caloriesRemaining", 1500);
        result.put("minutesExercised",
                Person.getPersonHoursOfTrainingForLast24Hours(id).multiply(BigDecimal.valueOf(60)));

        return result;
    }

    @RequestMapping(value = "{uid}/{y}/{m}/{d}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Map<String, Object> mainScreenWithDateFilter(@PathVariable("uid") Long id, @PathVariable("y") Long year,
                                                 @PathVariable("m") Long month, @PathVariable("d") Long day) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("caloriesConsumed", Person.getPersonCaloricIntakeForLast24Hours(id));
        result.put("caloriesBurned", Person.getPersonBurnedCaloriesForLast24Hours(id));
        result.put("caloriesRemaining", 1500);
        result.put("minutesExercised",
                Person.getPersonHoursOfTrainingForLast24Hours(id).multiply(BigDecimal.valueOf(60)));
        return result;
    }
}
