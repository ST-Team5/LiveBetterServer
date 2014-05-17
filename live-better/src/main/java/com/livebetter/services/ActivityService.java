package com.livebetter.services;
import com.livebetter.domain.Activity;
import java.util.List;

public interface ActivityService {

	public abstract long countAllActivitieses();


	public abstract void deleteActivities(Activity activity);


	public abstract Activity findActivities(Long id);


	public abstract List<Activity> findAllActivitieses();


	public abstract List<Activity> findActivitiesEntries(int firstResult, int maxResults);


	public abstract void saveActivities(Activity activity);


	public abstract Activity updateActivities(Activity activity);

}
