package com.livebetter.services;
import com.livebetter.domain.PersonActivity;
import java.util.List;

public interface PersonActivityService {

	public abstract long countAllPersonActivitieses();


	public abstract void deletePersonActivities(PersonActivity personActivity);


	public abstract PersonActivity findPersonActivities(Long id);


	public abstract List<PersonActivity> findAllPersonActivitieses();


	public abstract List<PersonActivity> findPersonActivitiesEntries(int firstResult, int maxResults);


	public abstract void savePersonActivities(PersonActivity personActivity);


	public abstract PersonActivity updatePersonActivities(PersonActivity personActivity);

}
