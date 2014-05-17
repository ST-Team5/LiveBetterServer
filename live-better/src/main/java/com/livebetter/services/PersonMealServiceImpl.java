package com.livebetter.services;

import com.livebetter.domain.PersonMeal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonMealServiceImpl implements PersonMealService {

	public long countAllPersonMealses() {
        return PersonMeal.countPersonMealses();
    }

	public void deletePersonMeals(PersonMeal personMeal) {
        personMeal.remove();
    }

	public PersonMeal findPersonMeals(Long id) {
        return PersonMeal.findPersonMeals(id);
    }

	public List<PersonMeal> findAllPersonMealses() {
        return PersonMeal.findAllPersonMealses();
    }

	public List<PersonMeal> findPersonMealsEntries(int firstResult, int maxResults) {
        return PersonMeal.findPersonMealsEntries(firstResult, maxResults);
    }

	public void savePersonMeals(PersonMeal personMeal) {
        personMeal.persist();
    }

	public PersonMeal updatePersonMeals(PersonMeal personMeal) {
        return personMeal.merge();
    }
}
