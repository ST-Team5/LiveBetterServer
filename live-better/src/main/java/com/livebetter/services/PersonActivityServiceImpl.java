package com.livebetter.services;

import com.livebetter.domain.PersonActivity;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonActivityServiceImpl implements PersonActivityService {

	public long countAllPersonActivitieses() {
        return PersonActivity.countPersonActivitieses();
    }

	public void deletePersonActivities(PersonActivity personActivity) {
        personActivity.remove();
    }

	public PersonActivity findPersonActivities(Long id) {
        return PersonActivity.findPersonActivities(id);
    }

	public List<PersonActivity> findAllPersonActivitieses() {
        return PersonActivity.findAllPersonActivitieses();
    }

	public List<PersonActivity> findPersonActivitiesEntries(int firstResult, int maxResults) {
        return PersonActivity.findPersonActivitiesEntries(firstResult, maxResults);
    }

	public void savePersonActivities(PersonActivity personActivity) {
        personActivity.persist();
    }

	public PersonActivity updatePersonActivities(PersonActivity personActivity) {
        return personActivity.merge();
    }
}
