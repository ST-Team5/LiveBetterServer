package com.livebetter.services;
import com.livebetter.domain.Drink;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.livebetter.domain.Drink.class })
public interface DrinkService {

	public abstract long countAllDrinkses();


	public abstract void deleteDrinks(Drink drink);


	public abstract Drink findDrinks(Long id);


	public abstract List<Drink> findAllDrinkses();


	public abstract List<Drink> findDrinksEntries(int firstResult, int maxResults);


	public abstract void saveDrinks(Drink drink);


	public abstract Drink updateDrinks(Drink drink);

}
