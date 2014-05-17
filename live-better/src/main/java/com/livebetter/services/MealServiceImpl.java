package com.livebetter.services;

import com.livebetter.domain.Meal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MealServiceImpl implements MealService {

	public long countAllMealses() {
        return Meal.countMealses();
    }

	public void deleteMeals(Meal meal) {
        meal.remove();
    }

	public Meal findMeals(Long id) {
        return Meal.findMeals(id);
    }

	public List<Meal> findAllMealses() {
        return Meal.findAllMealses();
    }

	public List<Meal> findMealsEntries(int firstResult, int maxResults) {
        return Meal.findMealsEntries(firstResult, maxResults);
    }

	public void saveMeals(Meal meal) {
        meal.persist();
    }

	public Meal updateMeals(Meal meal) {
        return meal.merge();
    }
}
