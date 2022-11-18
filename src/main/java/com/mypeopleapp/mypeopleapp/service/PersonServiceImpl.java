package com.mypeopleapp.mypeopleapp.service;

import com.mypeopleapp.mypeopleapp.model.Person;
import com.mypeopleapp.mypeopleapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    public Person getPersonById(long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if(optionalPerson.isPresent()) {
            return optionalPerson.get();
        }

        return null;
    }

    @Override
    public Person savePerson(Person person) {
        personRepository.save(person);
        return person;
    }

    @Override
    public Person updatePersonById(long id, Person person) {

        Person aperson = getPersonById(id);
        if (person != null) {
            aperson.setFirstName(person.getFirstName());
            aperson.setLastName(person.getLastName());
            aperson.setAge(person.getAge());
            aperson.setEmail(person.getEmail());
            aperson.setPhoneNum(person.getPhoneNum());

            return aperson;
        }
        return null;
    }

    @Override
    public void deletePersonById(long id) {
        personRepository.deleteById(id);
    }
}
