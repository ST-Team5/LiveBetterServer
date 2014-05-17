// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livebetter.services;

import com.livebetter.domain.PersonDrinks;
import com.livebetter.services.PersonDrinksServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PersonDrinksServiceImpl_Roo_Service {
    
    declare @type: PersonDrinksServiceImpl: @Service;
    
    declare @type: PersonDrinksServiceImpl: @Transactional;
    
    public long PersonDrinksServiceImpl.countAllPersonDrinkses() {
        return PersonDrinks.countPersonDrinkses();
    }
    
    public void PersonDrinksServiceImpl.deletePersonDrinks(PersonDrinks personDrinks) {
        personDrinks.remove();
    }
    
    public PersonDrinks PersonDrinksServiceImpl.findPersonDrinks(Long id) {
        return PersonDrinks.findPersonDrinks(id);
    }
    
    public List<PersonDrinks> PersonDrinksServiceImpl.findAllPersonDrinkses() {
        return PersonDrinks.findAllPersonDrinkses();
    }
    
    public List<PersonDrinks> PersonDrinksServiceImpl.findPersonDrinksEntries(int firstResult, int maxResults) {
        return PersonDrinks.findPersonDrinksEntries(firstResult, maxResults);
    }
    
    public void PersonDrinksServiceImpl.savePersonDrinks(PersonDrinks personDrinks) {
        personDrinks.persist();
    }
    
    public PersonDrinks PersonDrinksServiceImpl.updatePersonDrinks(PersonDrinks personDrinks) {
        return personDrinks.merge();
    }
    
}
