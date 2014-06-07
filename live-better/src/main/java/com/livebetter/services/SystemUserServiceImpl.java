package com.livebetter.services;

import com.livebetter.domain.SystemUser;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class SystemUserServiceImpl implements SystemUserService {

	public long countAllSystemUserses() {
        return SystemUser.countSystemUserses();
    }

	public void deleteSystemUsers(SystemUser systemUser) {
        systemUser.remove();
    }

	public SystemUser findSystemUsers(Long id) {
        return SystemUser.findSystemUsers(id);
    }

	public List<SystemUser> findAllSystemUserses() {
        return SystemUser.findAllSystemUserses();
    }

	public List<SystemUser> findSystemUsersEntries(int firstResult, int maxResults) {
        return SystemUser.findSystemUsersEntries(firstResult, maxResults);
    }

	public void saveSystemUsers(SystemUser systemUser) {
        systemUser.persist();
    }

	public SystemUser updateSystemUsers(SystemUser systemUser) {
        return systemUser.merge();
    }

	@Override
	public SystemUser findSystemUserByUserName(String name) {
		if(StringUtils.hasLength(name)){
			TypedQuery<SystemUser> query = SystemUser.entityManager().createQuery("SELECT su FROM SystemUser su " +
					"WHERE su.username = :name", SystemUser.class);
			query.setParameter("name", name);
			query.setMaxResults(1);
			try{
				return query.getSingleResult();
			}catch (Exception e) {
				// TODO: handle exception
				return null;
			}
		}
		return null;
	}
}
