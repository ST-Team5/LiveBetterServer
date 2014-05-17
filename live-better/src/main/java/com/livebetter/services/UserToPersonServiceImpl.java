package com.livebetter.services;

import com.livebetter.domain.UserToPerson;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserToPersonServiceImpl implements UserToPersonService {

	public long countAllUsersToPersonses() {
        return UserToPerson.countUsersToPersonses();
    }

	public void deleteUsersToPersons(UserToPerson userToPerson) {
        userToPerson.remove();
    }

	public UserToPerson findUsersToPersons(Long id) {
        return UserToPerson.findUsersToPersons(id);
    }

	public List<UserToPerson> findAllUsersToPersonses() {
        return UserToPerson.findAllUsersToPersonses();
    }

	public List<UserToPerson> findUsersToPersonsEntries(int firstResult, int maxResults) {
        return UserToPerson.findUsersToPersonsEntries(firstResult, maxResults);
    }

	public void saveUsersToPersons(UserToPerson userToPerson) {
        userToPerson.persist();
    }

	public UserToPerson updateUsersToPersons(UserToPerson userToPerson) {
        return userToPerson.merge();
    }
}
