package com.livebetter.services;
import com.livebetter.domain.UserToPerson;
import java.util.List;

public interface UserToPersonService {

	public abstract long countAllUsersToPersonses();


	public abstract void deleteUsersToPersons(UserToPerson userToPerson);


	public abstract UserToPerson findUsersToPersons(Long id);


	public abstract List<UserToPerson> findAllUsersToPersonses();


	public abstract List<UserToPerson> findUsersToPersonsEntries(int firstResult, int maxResults);


	public abstract void saveUsersToPersons(UserToPerson userToPerson);


	public abstract UserToPerson updateUsersToPersons(UserToPerson userToPerson);

}
