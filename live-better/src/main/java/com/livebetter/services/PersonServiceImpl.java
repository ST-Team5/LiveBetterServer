package com.livebetter.services;

import com.livebetter.domain.Person;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	public long countAllPersonses() {
        return Person.countPersonses();
    }

	public void deletePersons(Person person) {
        person.remove();
    }

	public Person findPersons(Long id) {
        return Person.findPersons(id);
    }

	public List<Person> findAllPersonses() {
        return Person.findAllPersonses();
    }

	public List<Person> findPersonsEntries(int firstResult, int maxResults) {
        return Person.findPersonsEntries(firstResult, maxResults);
    }

	public void savePersons(Person person) {
        person.persist();
    }

	public Person updatePersons(Person person) {
        return person.merge();
    }
}
