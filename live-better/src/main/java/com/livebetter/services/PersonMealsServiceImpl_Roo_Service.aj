// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livebetter.services;

import com.livebetter.domain.PersonMeals;
import com.livebetter.services.PersonMealsServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PersonMealsServiceImpl_Roo_Service {
    
    declare @type: PersonMealsServiceImpl: @Service;
    
    declare @type: PersonMealsServiceImpl: @Transactional;
    
    public long PersonMealsServiceImpl.countAllPersonMealses() {
        return PersonMeals.countPersonMealses();
    }
    
    public void PersonMealsServiceImpl.deletePersonMeals(PersonMeals personMeals) {
        personMeals.remove();
    }
    
    public PersonMeals PersonMealsServiceImpl.findPersonMeals(Long id) {
        return PersonMeals.findPersonMeals(id);
    }
    
    public List<PersonMeals> PersonMealsServiceImpl.findAllPersonMealses() {
        return PersonMeals.findAllPersonMealses();
    }
    
    public List<PersonMeals> PersonMealsServiceImpl.findPersonMealsEntries(int firstResult, int maxResults) {
        return PersonMeals.findPersonMealsEntries(firstResult, maxResults);
    }
    
    public void PersonMealsServiceImpl.savePersonMeals(PersonMeals personMeals) {
        personMeals.persist();
    }
    
    public PersonMeals PersonMealsServiceImpl.updatePersonMeals(PersonMeals personMeals) {
        return personMeals.merge();
    }
    
}
