package com.livebetter.controllers;

import com.livebetter.domain.Person;
import com.livebetter.domain.PersonGender;
import com.livebetter.services.PersonService;
import com.livebetter.services.PersonServiceImpl;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("mainScreen")
public class MainScreenController {
    @Autowired
    PersonService personService = new PersonServiceImpl();

    private BigDecimal calculatePersonCaloriesPerDay(Person person) {
        final Calendar today = Calendar.getInstance();
        final Calendar deadLine = person.getGoalDeadline();
        final BigDecimal goalWeight = person.getGoalWeight();
        final BigDecimal result;
        final Long personWeight = person.getWeight();
        final Long personHeight = person.getHeight();
        final Integer personAge = Years.yearsBetween(new DateTime(person.getDateOfBirth()),
                new DateTime(today)).get(DurationFieldType.years());

        final double metabolismFactor;
        switch (person.getMetabolismType()) {
            case FAST:
                metabolismFactor = 1.5;
                break;
            case SLOW:
                metabolismFactor = 1.2;
                break;
            case MIXED:
            default:
                metabolismFactor = 1.5;
                break;
        }

        final PersonGender gender = person.getGender();
        if (goalWeight != null && deadLine != null && deadLine.after(today)) {
            int numberOfDaysUntilDeadline =
                    Days.daysBetween(new DateTime(today), new DateTime(deadLine)).getDays();
            final long desiredWeightDifference = goalWeight.intValue() - personWeight;
            if (PersonGender.FEMALE.equals(gender)) {
                result = BigDecimal.valueOf((desiredWeightDifference *
                        (3500 - personHeight - 15 * metabolismFactor)) / numberOfDaysUntilDeadline);
            } else {
                result = BigDecimal.valueOf((desiredWeightDifference *
                        (3000 - personHeight - 10 * metabolismFactor)) / numberOfDaysUntilDeadline);
            }
        } else {
            // just return the recommended values
            if (PersonGender.FEMALE.equals(gender)) {
                result = BigDecimal
                        .valueOf(655 + (9.6 * personWeight) + (1.8 * personHeight) - (4.7 * personAge));
            } else {
                result = BigDecimal
                        .valueOf(66 + (13.7 * personWeight) + (5 * personHeight) - (6.8 * personAge));
            }
        }

        return result.setScale(0, RoundingMode.HALF_UP);
    }

    @RequestMapping(value = "{uid}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Map<String, Object> mainScreenForToday(@PathVariable("uid") Long id) {
        Person person = Person.findPersons(id);

        final BigDecimal desiredDifference = calculatePersonCaloriesPerDay(person);
        final BigDecimal caloriesIn = Person.getPersonCaloricIntakeForLast24Hours(id);
        final BigDecimal caloriesOut = Person.getPersonBurnedCaloriesForLast24Hours(id);
        final BigDecimal caloriesRemaining = caloriesIn.subtract(caloriesOut).add(desiredDifference);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("caloriesConsumed", caloriesIn);
        result.put("caloriesBurned", caloriesOut);
        result.put("caloriesRemaining", caloriesRemaining);
        result.put("minutesExercised",
                Person.getPersonHoursOfTrainingForLast24Hours(id).multiply(BigDecimal.valueOf(60)));
        result.put("differencePerDay", desiredDifference);
        return result;
    }

    @RequestMapping(value = "{uid}/{y}/{m}/{d}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Map<String, Object> mainScreenWithDateFilter(@PathVariable("uid") Long id, @PathVariable("y") Long year,
                                                 @PathVariable("m") Long month, @PathVariable("d") Long day) {
        Person person = Person.findPersons(id);

        final BigDecimal desiredDifference = calculatePersonCaloriesPerDay(person);
        final BigDecimal caloriesIn = Person.getPersonCaloricIntakeForLast24Hours(id);
        final BigDecimal caloriesOut = Person.getPersonBurnedCaloriesForLast24Hours(id);
        final BigDecimal caloriesRemaining = caloriesIn.subtract(caloriesOut).add(desiredDifference);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("caloriesConsumed", caloriesIn);
        result.put("caloriesBurned", caloriesOut);
        result.put("caloriesRemaining", caloriesRemaining);
        result.put("minutesExercised",
                Person.getPersonHoursOfTrainingForLast24Hours(id).multiply(BigDecimal.valueOf(60)));
        result.put("differencePerDay", desiredDifference);
        return result;
    }
}
