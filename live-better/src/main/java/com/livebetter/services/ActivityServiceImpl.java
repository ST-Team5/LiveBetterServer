package com.livebetter.services;

import com.livebetter.domain.Activity;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {

	public long countAllActivitieses() {
        return Activity.countActivitieses();
    }

	public void deleteActivities(Activity activity) {
        activity.remove();
    }

	public Activity findActivities(Long id) {
        return Activity.findActivities(id);
    }

	public List<Activity> findAllActivitieses() {
        return Activity.findAllActivitieses();
    }

	public List<Activity> findActivitiesEntries(int firstResult, int maxResults) {
        return Activity.findActivitiesEntries(firstResult, maxResults);
    }

	public void saveActivities(Activity activity) {
        activity.persist();
    }

	public Activity updateActivities(Activity activity) {
        return activity.merge();
    }
}
