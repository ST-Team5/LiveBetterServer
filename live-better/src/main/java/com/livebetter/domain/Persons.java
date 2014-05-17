package com.livebetter.domain;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "persons", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "personActivitieses", "personDrinkss", "personMealss", "usersToPersonss", "metabolismId" })
public class Persons {
}
