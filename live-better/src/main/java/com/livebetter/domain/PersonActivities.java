package com.livebetter.domain;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "person_activities", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "activityId", "personId" })
public class PersonActivities {
}
