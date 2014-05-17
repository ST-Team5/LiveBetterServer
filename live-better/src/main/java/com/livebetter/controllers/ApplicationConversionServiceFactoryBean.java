package com.livebetter.controllers;

import com.livebetter.domain.Activity;
import com.livebetter.domain.Drink;
import com.livebetter.domain.Meal;
import com.livebetter.domain.Metabolism;
import com.livebetter.domain.PersonActivity;
import com.livebetter.domain.PersonDrink;
import com.livebetter.domain.PersonMeal;
import com.livebetter.domain.Person;
import com.livebetter.domain.SystemUserRole;
import com.livebetter.domain.SystemUser;
import com.livebetter.domain.UserToPerson;
import com.livebetter.services.ActivityService;
import com.livebetter.services.DrinkService;
import com.livebetter.services.MealService;
import com.livebetter.services.MetabolismService;
import com.livebetter.services.PersonActivityService;
import com.livebetter.services.PersonDrinkService;
import com.livebetter.services.PersonMealService;
import com.livebetter.services.PersonService;
import com.livebetter.services.SystemUserRoleService;
import com.livebetter.services.SystemUserService;
import com.livebetter.services.UserToPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

@Configurable
/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
		// Register application converters and formatters
	}

	@Autowired
    ActivityService activityService;

	@Autowired
    DrinkService drinkService;

	@Autowired
    MealService mealService;

	@Autowired
    MetabolismService metabolismService;

	@Autowired
    PersonActivityService personActivityService;

	@Autowired
    PersonDrinkService personDrinkService;

	@Autowired
    PersonMealService personMealService;

	@Autowired
    PersonService personService;

	@Autowired
    SystemUserRoleService systemUserRoleService;

	@Autowired
    SystemUserService systemUserService;

	@Autowired
    UserToPersonService userToPersonService;

	public Converter<Activity, String> getActivitiesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.Activity, java.lang.String>() {
            public String convert(Activity activity) {
                return new StringBuilder().append(activity.getName()).append(' ').append(activity.getType()).append(' ').append(activity.getCaloriesPerHour()).append(' ').append(activity.getCreatedBy()).toString();
            }
        };
    }

	public Converter<Long, Activity> getIdToActivitiesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.Activity>() {
            public com.livebetter.domain.Activity convert(java.lang.Long id) {
                return activityService.findActivities(id);
            }
        };
    }

	public Converter<String, Activity> getStringToActivitiesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.Activity>() {
            public com.livebetter.domain.Activity convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Activity.class);
            }
        };
    }

	public Converter<Drink, String> getDrinksToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.Drink, java.lang.String>() {
            public String convert(Drink drink) {
                return new StringBuilder().append(drink.getName()).append(' ').append(drink.getType()).append(' ').append(drink.getCarbohydrates()).append(' ').append(drink.getProteins()).toString();
            }
        };
    }

	public Converter<Long, Drink> getIdToDrinksConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.Drink>() {
            public com.livebetter.domain.Drink convert(java.lang.Long id) {
                return drinkService.findDrinks(id);
            }
        };
    }

	public Converter<String, Drink> getStringToDrinksConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.Drink>() {
            public com.livebetter.domain.Drink convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Drink.class);
            }
        };
    }

	public Converter<Meal, String> getMealsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.Meal, java.lang.String>() {
            public String convert(Meal meal) {
                return new StringBuilder().append(meal.getName()).append(' ').append(meal.getType()).append(' ').append(meal.getCarbohydrates()).append(' ').append(meal.getProteins()).toString();
            }
        };
    }

	public Converter<Long, Meal> getIdToMealsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.Meal>() {
            public com.livebetter.domain.Meal convert(java.lang.Long id) {
                return mealService.findMeals(id);
            }
        };
    }

	public Converter<String, Meal> getStringToMealsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.Meal>() {
            public com.livebetter.domain.Meal convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Meal.class);
            }
        };
    }

	public Converter<Metabolism, String> getMetabolismsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.Metabolism, java.lang.String>() {
            public String convert(Metabolism metabolism) {
                return new StringBuilder().append(metabolism.getName()).append(' ').append(metabolism.getCreatedBy()).append(' ').append(metabolism.getCreatedDatetime()).append(' ').append(metabolism.getModifiedDatetime()).toString();
            }
        };
    }

	public Converter<Long, Metabolism> getIdToMetabolismsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.Metabolism>() {
            public com.livebetter.domain.Metabolism convert(java.lang.Long id) {
                return metabolismService.findMetabolisms(id);
            }
        };
    }

	public Converter<String, Metabolism> getStringToMetabolismsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.Metabolism>() {
            public com.livebetter.domain.Metabolism convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Metabolism.class);
            }
        };
    }

	public Converter<PersonActivity, String> getPersonActivitiesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.PersonActivity, java.lang.String>() {
            public String convert(PersonActivity personActivity) {
                return new StringBuilder().append(personActivity.getDatetimeOfConsumtion()).append(' ').append(personActivity.getQuantity()).append(' ').append(personActivity.getCreatedBy()).append(' ').append(personActivity.getCreatedDatetime()).toString();
            }
        };
    }

	public Converter<Long, PersonActivity> getIdToPersonActivitiesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.PersonActivity>() {
            public com.livebetter.domain.PersonActivity convert(java.lang.Long id) {
                return personActivityService.findPersonActivities(id);
            }
        };
    }

	public Converter<String, PersonActivity> getStringToPersonActivitiesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.PersonActivity>() {
            public com.livebetter.domain.PersonActivity convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PersonActivity.class);
            }
        };
    }

	public Converter<PersonDrink, String> getPersonDrinksToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.PersonDrink, java.lang.String>() {
            public String convert(PersonDrink personDrink) {
                return new StringBuilder().append(personDrink.getDatetimeOfConsumtion()).append(' ').append(personDrink.getQuantity()).append(' ').append(personDrink.getCreatedBy()).append(' ').append(personDrink.getCreatedDatetime()).toString();
            }
        };
    }

	public Converter<Long, PersonDrink> getIdToPersonDrinksConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.PersonDrink>() {
            public com.livebetter.domain.PersonDrink convert(java.lang.Long id) {
                return personDrinkService.findPersonDrinks(id);
            }
        };
    }

	public Converter<String, PersonDrink> getStringToPersonDrinksConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.PersonDrink>() {
            public com.livebetter.domain.PersonDrink convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PersonDrink.class);
            }
        };
    }

	public Converter<PersonMeal, String> getPersonMealsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.PersonMeal, java.lang.String>() {
            public String convert(PersonMeal personMeal) {
                return new StringBuilder().append(personMeal.getDatetimeOfConsumtion()).append(' ').append(personMeal.getQuantity()).append(' ').append(personMeal.getCreatedBy()).append(' ').append(personMeal.getCreatedDatetime()).toString();
            }
        };
    }

	public Converter<Long, PersonMeal> getIdToPersonMealsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.PersonMeal>() {
            public com.livebetter.domain.PersonMeal convert(java.lang.Long id) {
                return personMealService.findPersonMeals(id);
            }
        };
    }

	public Converter<String, PersonMeal> getStringToPersonMealsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.PersonMeal>() {
            public com.livebetter.domain.PersonMeal convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), PersonMeal.class);
            }
        };
    }

	public Converter<Person, String> getPersonsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.Person, java.lang.String>() {
            public String convert(Person person) {
                return new StringBuilder().append(person.getFirstname()).append(' ').append(person.getMiddlename()).append(' ').append(person.getLastname()).append(' ').append(person.getDateOfBirth()).toString();
            }
        };
    }

	public Converter<Long, Person> getIdToPersonsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.Person>() {
            public com.livebetter.domain.Person convert(java.lang.Long id) {
                return personService.findPersons(id);
            }
        };
    }

	public Converter<String, Person> getStringToPersonsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.Person>() {
            public com.livebetter.domain.Person convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Person.class);
            }
        };
    }

	public Converter<SystemUserRole, String> getSystemUserRolesToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.SystemUserRole, java.lang.String>() {
            public String convert(SystemUserRole systemUserRole) {
                return new StringBuilder().append(systemUserRole.getName()).append(' ').append(systemUserRole.getDescription()).append(' ').append(systemUserRole.getCreatedBy()).append(' ').append(systemUserRole.getLevel()).toString();
            }
        };
    }

	public Converter<Long, SystemUserRole> getIdToSystemUserRolesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.SystemUserRole>() {
            public com.livebetter.domain.SystemUserRole convert(java.lang.Long id) {
                return systemUserRoleService.findSystemUserRoles(id);
            }
        };
    }

	public Converter<String, SystemUserRole> getStringToSystemUserRolesConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.SystemUserRole>() {
            public com.livebetter.domain.SystemUserRole convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), SystemUserRole.class);
            }
        };
    }

	public Converter<SystemUser, String> getSystemUsersToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.SystemUser, java.lang.String>() {
            public String convert(SystemUser systemUser) {
                return new StringBuilder().append(systemUser.getUsername()).append(' ').append(systemUser.getPassword()).append(' ').append(systemUser.getCreatedBy()).append(' ').append(systemUser.getCreatedDatetime()).toString();
            }
        };
    }

	public Converter<Long, SystemUser> getIdToSystemUsersConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.SystemUser>() {
            public com.livebetter.domain.SystemUser convert(java.lang.Long id) {
                return systemUserService.findSystemUsers(id);
            }
        };
    }

	public Converter<String, SystemUser> getStringToSystemUsersConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.SystemUser>() {
            public com.livebetter.domain.SystemUser convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), SystemUser.class);
            }
        };
    }

	public Converter<UserToPerson, String> getUsersToPersonsToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.livebetter.domain.UserToPerson, java.lang.String>() {
            public String convert(UserToPerson userToPerson) {
                return "(no displayable fields)";
            }
        };
    }

	public Converter<Long, UserToPerson> getIdToUsersToPersonsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, com.livebetter.domain.UserToPerson>() {
            public com.livebetter.domain.UserToPerson convert(java.lang.Long id) {
                return userToPersonService.findUsersToPersons(id);
            }
        };
    }

	public Converter<String, UserToPerson> getStringToUsersToPersonsConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, com.livebetter.domain.UserToPerson>() {
            public com.livebetter.domain.UserToPerson convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), UserToPerson.class);
            }
        };
    }

	public void installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getActivitiesToStringConverter());
        registry.addConverter(getIdToActivitiesConverter());
        registry.addConverter(getStringToActivitiesConverter());
        registry.addConverter(getDrinksToStringConverter());
        registry.addConverter(getIdToDrinksConverter());
        registry.addConverter(getStringToDrinksConverter());
        registry.addConverter(getMealsToStringConverter());
        registry.addConverter(getIdToMealsConverter());
        registry.addConverter(getStringToMealsConverter());
        registry.addConverter(getMetabolismsToStringConverter());
        registry.addConverter(getIdToMetabolismsConverter());
        registry.addConverter(getStringToMetabolismsConverter());
        registry.addConverter(getPersonActivitiesToStringConverter());
        registry.addConverter(getIdToPersonActivitiesConverter());
        registry.addConverter(getStringToPersonActivitiesConverter());
        registry.addConverter(getPersonDrinksToStringConverter());
        registry.addConverter(getIdToPersonDrinksConverter());
        registry.addConverter(getStringToPersonDrinksConverter());
        registry.addConverter(getPersonMealsToStringConverter());
        registry.addConverter(getIdToPersonMealsConverter());
        registry.addConverter(getStringToPersonMealsConverter());
        registry.addConverter(getPersonsToStringConverter());
        registry.addConverter(getIdToPersonsConverter());
        registry.addConverter(getStringToPersonsConverter());
        registry.addConverter(getSystemUserRolesToStringConverter());
        registry.addConverter(getIdToSystemUserRolesConverter());
        registry.addConverter(getStringToSystemUserRolesConverter());
        registry.addConverter(getSystemUsersToStringConverter());
        registry.addConverter(getIdToSystemUsersConverter());
        registry.addConverter(getStringToSystemUsersConverter());
        registry.addConverter(getUsersToPersonsToStringConverter());
        registry.addConverter(getIdToUsersToPersonsConverter());
        registry.addConverter(getStringToUsersToPersonsConverter());
    }

	public void afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
}
