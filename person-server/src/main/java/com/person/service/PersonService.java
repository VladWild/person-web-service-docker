package com.person.service;

import com.person.asserts.AssertPerson;
import com.person.db.model.Person;
import com.person.db.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person getPerson(Long id) {
        Optional<Person> personOptional = personRepository.findById(id);
        AssertPerson.notFoundTrue(personOptional.isEmpty(), "person %s not found", id);
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Person person = personOptional.get();
        logger.info("Get person from db: {}", person);
        return person;
    }

    public Long savePerson(Person person) {
        Person personSave = personRepository.save(person);
        logger.info("Person Save: {}", personSave);
        return personSave.getId();
    }
}
