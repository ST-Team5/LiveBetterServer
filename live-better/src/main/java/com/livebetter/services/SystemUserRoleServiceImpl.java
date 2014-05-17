package com.livebetter.services;

import com.livebetter.domain.SystemUserRole;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SystemUserRoleServiceImpl implements SystemUserRoleService {

	public long countAllSystemUserRoleses() {
        return SystemUserRole.countSystemUserRoleses();
    }

	public void deleteSystemUserRoles(SystemUserRole systemUserRole) {
        systemUserRole.remove();
    }

	public SystemUserRole findSystemUserRoles(Long id) {
        return SystemUserRole.findSystemUserRoles(id);
    }

	public List<SystemUserRole> findAllSystemUserRoleses() {
        return SystemUserRole.findAllSystemUserRoleses();
    }

	public List<SystemUserRole> findSystemUserRolesEntries(int firstResult, int maxResults) {
        return SystemUserRole.findSystemUserRolesEntries(firstResult, maxResults);
    }

	public void saveSystemUserRoles(SystemUserRole systemUserRole) {
        systemUserRole.persist();
    }

	public SystemUserRole updateSystemUserRoles(SystemUserRole systemUserRole) {
        return systemUserRole.merge();
    }
}
