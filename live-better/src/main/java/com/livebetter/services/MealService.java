package com.livebetter.services;
import com.livebetter.domain.Meal;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.livebetter.domain.Meal.class })
public interface MealService {

	public abstract long countAllMealses();


	public abstract void deleteMeals(Meal meal);


	public abstract Meal findMeals(Long id);


	public abstract List<Meal> findAllMealses();


	public abstract List<Meal> findMealsEntries(int firstResult, int maxResults);


	public abstract void saveMeals(Meal meal);


	public abstract Meal updateMeals(Meal meal);

}
