package com.livebetter.domain;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooJpaActiveRecord(versionField = "", table = "system_user_roles", schema = "public")
@RooDbManaged(automaticallyDelete = true)
@RooToString(excludeFields = { "systemUserss" })
public class SystemUserRoles {
}
