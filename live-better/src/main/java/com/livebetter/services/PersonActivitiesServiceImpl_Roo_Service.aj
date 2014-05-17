// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livebetter.services;

import com.livebetter.domain.PersonActivities;
import com.livebetter.services.PersonActivitiesServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect PersonActivitiesServiceImpl_Roo_Service {
    
    declare @type: PersonActivitiesServiceImpl: @Service;
    
    declare @type: PersonActivitiesServiceImpl: @Transactional;
    
    public long PersonActivitiesServiceImpl.countAllPersonActivitieses() {
        return PersonActivities.countPersonActivitieses();
    }
    
    public void PersonActivitiesServiceImpl.deletePersonActivities(PersonActivities personActivities) {
        personActivities.remove();
    }
    
    public PersonActivities PersonActivitiesServiceImpl.findPersonActivities(Long id) {
        return PersonActivities.findPersonActivities(id);
    }
    
    public List<PersonActivities> PersonActivitiesServiceImpl.findAllPersonActivitieses() {
        return PersonActivities.findAllPersonActivitieses();
    }
    
    public List<PersonActivities> PersonActivitiesServiceImpl.findPersonActivitiesEntries(int firstResult, int maxResults) {
        return PersonActivities.findPersonActivitiesEntries(firstResult, maxResults);
    }
    
    public void PersonActivitiesServiceImpl.savePersonActivities(PersonActivities personActivities) {
        personActivities.persist();
    }
    
    public PersonActivities PersonActivitiesServiceImpl.updatePersonActivities(PersonActivities personActivities) {
        return personActivities.merge();
    }
    
}
