// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livebetter.services;

import com.livebetter.domain.Meals;
import com.livebetter.services.MealsService;
import java.util.List;

privileged aspect MealsService_Roo_Service {
    
    public abstract long MealsService.countAllMealses();    
    public abstract void MealsService.deleteMeals(Meals meals);    
    public abstract Meals MealsService.findMeals(Long id);    
    public abstract List<Meals> MealsService.findAllMealses();    
    public abstract List<Meals> MealsService.findMealsEntries(int firstResult, int maxResults);    
    public abstract void MealsService.saveMeals(Meals meals);    
    public abstract Meals MealsService.updateMeals(Meals meals);    
}
