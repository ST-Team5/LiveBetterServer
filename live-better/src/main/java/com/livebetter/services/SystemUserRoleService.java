package com.livebetter.services;
import com.livebetter.domain.SystemUserRole;
import java.util.List;

public interface SystemUserRoleService {

	public abstract long countAllSystemUserRoleses();


	public abstract void deleteSystemUserRoles(SystemUserRole systemUserRole);


	public abstract SystemUserRole findSystemUserRoles(Long id);


	public abstract List<SystemUserRole> findAllSystemUserRoleses();


	public abstract List<SystemUserRole> findSystemUserRolesEntries(int firstResult, int maxResults);


	public abstract void saveSystemUserRoles(SystemUserRole systemUserRole);


	public abstract SystemUserRole updateSystemUserRoles(SystemUserRole systemUserRole);

}
