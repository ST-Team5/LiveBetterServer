package com.livebetter.services;
import com.livebetter.domain.Person;
import java.util.List;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { com.livebetter.domain.Person.class })
public interface PersonService {

	public abstract long countAllPersonses();


	public abstract void deletePersons(Person person);


	public abstract Person findPersons(Long id);


	public abstract List<Person> findAllPersonses();


	public abstract List<Person> findPersonsEntries(int firstResult, int maxResults);


	public abstract void savePersons(Person person);


	public abstract Person updatePersons(Person person);

}
