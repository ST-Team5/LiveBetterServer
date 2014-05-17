package com.livebetter.services;

import com.livebetter.domain.PersonDrink;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonDrinkServiceImpl implements PersonDrinkService {

	public long countAllPersonDrinkses() {
        return PersonDrink.countPersonDrinkses();
    }

	public void deletePersonDrinks(PersonDrink personDrink) {
        personDrink.remove();
    }

	public PersonDrink findPersonDrinks(Long id) {
        return PersonDrink.findPersonDrinks(id);
    }

	public List<PersonDrink> findAllPersonDrinkses() {
        return PersonDrink.findAllPersonDrinkses();
    }

	public List<PersonDrink> findPersonDrinksEntries(int firstResult, int maxResults) {
        return PersonDrink.findPersonDrinksEntries(firstResult, maxResults);
    }

	public void savePersonDrinks(PersonDrink personDrink) {
        personDrink.persist();
    }

	public PersonDrink updatePersonDrinks(PersonDrink personDrink) {
        return personDrink.merge();
    }
}
