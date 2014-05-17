package com.livebetter.services;
import com.livebetter.domain.Activity;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.livebetter.domain.Activity.class })
public interface ActivityService {

	public abstract long countAllActivitieses();


	public abstract void deleteActivities(Activity activity);


	public abstract Activity findActivities(Long id);


	public abstract List<Activity> findAllActivitieses();


	public abstract List<Activity> findActivitiesEntries(int firstResult, int maxResults);


	public abstract void saveActivities(Activity activity);


	public abstract Activity updateActivities(Activity activity);

}
