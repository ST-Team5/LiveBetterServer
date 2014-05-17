package com.livebetter.services;
import com.livebetter.domain.SystemUser;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.livebetter.domain.SystemUser.class })
public interface SystemUserService {

	public abstract long countAllSystemUserses();


	public abstract void deleteSystemUsers(SystemUser systemUser);


	public abstract SystemUser findSystemUsers(Long id);


	public abstract List<SystemUser> findAllSystemUserses();


	public abstract List<SystemUser> findSystemUsersEntries(int firstResult, int maxResults);


	public abstract void saveSystemUsers(SystemUser systemUser);


	public abstract SystemUser updateSystemUsers(SystemUser systemUser);

}
