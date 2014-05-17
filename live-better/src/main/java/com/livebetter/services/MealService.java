package com.livebetter.services;
import com.livebetter.domain.Meal;
import java.util.List;

public interface MealService {

	public abstract long countAllMealses();


	public abstract void deleteMeals(Meal meal);


	public abstract Meal findMeals(Long id);


	public abstract List<Meal> findAllMealses();


	public abstract List<Meal> findMealsEntries(int firstResult, int maxResults);


	public abstract void saveMeals(Meal meal);


	public abstract Meal updateMeals(Meal meal);

}
