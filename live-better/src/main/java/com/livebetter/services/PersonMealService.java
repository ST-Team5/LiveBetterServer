package com.livebetter.services;
import com.livebetter.domain.PersonMeal;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.livebetter.domain.PersonMeal.class })
public interface PersonMealService {

	public abstract long countAllPersonMealses();


	public abstract void deletePersonMeals(PersonMeal personMeal);


	public abstract PersonMeal findPersonMeals(Long id);


	public abstract List<PersonMeal> findAllPersonMealses();


	public abstract List<PersonMeal> findPersonMealsEntries(int firstResult, int maxResults);


	public abstract void savePersonMeals(PersonMeal personMeal);


	public abstract PersonMeal updatePersonMeals(PersonMeal personMeal);

}
