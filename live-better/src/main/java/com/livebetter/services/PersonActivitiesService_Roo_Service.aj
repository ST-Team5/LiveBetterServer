// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livebetter.services;

import com.livebetter.domain.PersonActivities;
import com.livebetter.services.PersonActivitiesService;
import java.util.List;

privileged aspect PersonActivitiesService_Roo_Service {
    
    public abstract long PersonActivitiesService.countAllPersonActivitieses();    
    public abstract void PersonActivitiesService.deletePersonActivities(PersonActivities personActivities);    
    public abstract PersonActivities PersonActivitiesService.findPersonActivities(Long id);    
    public abstract List<PersonActivities> PersonActivitiesService.findAllPersonActivitieses();    
    public abstract List<PersonActivities> PersonActivitiesService.findPersonActivitiesEntries(int firstResult, int maxResults);    
    public abstract void PersonActivitiesService.savePersonActivities(PersonActivities personActivities);    
    public abstract PersonActivities PersonActivitiesService.updatePersonActivities(PersonActivities personActivities);    
}
