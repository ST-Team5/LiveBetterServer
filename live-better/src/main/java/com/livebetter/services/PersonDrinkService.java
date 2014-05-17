package com.livebetter.services;
import com.livebetter.domain.PersonDrink;
import java.util.List;

public interface PersonDrinkService {

	public abstract long countAllPersonDrinkses();


	public abstract void deletePersonDrinks(PersonDrink personDrink);


	public abstract PersonDrink findPersonDrinks(Long id);


	public abstract List<PersonDrink> findAllPersonDrinkses();


	public abstract List<PersonDrink> findPersonDrinksEntries(int firstResult, int maxResults);


	public abstract void savePersonDrinks(PersonDrink personDrink);


	public abstract PersonDrink updatePersonDrinks(PersonDrink personDrink);

}
