package com.livebetter.services;

import com.livebetter.domain.Drink;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DrinkServiceImpl implements DrinkService {

	public long countAllDrinkses() {
        return Drink.countDrinkses();
    }

	public void deleteDrinks(Drink drink) {
        drink.remove();
    }

	public Drink findDrinks(Long id) {
        return Drink.findDrinks(id);
    }

	public List<Drink> findAllDrinkses() {
        return Drink.findAllDrinkses();
    }

	public List<Drink> findDrinksEntries(int firstResult, int maxResults) {
        return Drink.findDrinksEntries(firstResult, maxResults);
    }

	public void saveDrinks(Drink drink) {
        drink.persist();
    }

	public Drink updateDrinks(Drink drink) {
        return drink.merge();
    }
}
