package com.livebetter.services;
import com.livebetter.domain.UserToPerson;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.livebetter.domain.UserToPerson.class })
public interface UserToPersonService {

	public abstract long countAllUsersToPersonses();


	public abstract void deleteUsersToPersons(UserToPerson userToPerson);


	public abstract UserToPerson findUsersToPersons(Long id);


	public abstract List<UserToPerson> findAllUsersToPersonses();


	public abstract List<UserToPerson> findUsersToPersonsEntries(int firstResult, int maxResults);


	public abstract void saveUsersToPersons(UserToPerson userToPerson);


	public abstract UserToPerson updateUsersToPersons(UserToPerson userToPerson);

}
