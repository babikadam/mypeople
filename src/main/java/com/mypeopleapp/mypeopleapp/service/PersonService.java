package com.mypeopleapp.mypeopleapp.service;

import com.mypeopleapp.mypeopleapp.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAllPersons();

    Person getPersonById(long id);

    Person savePerson(Person person);

    Person updatePersonById(long id, Person person);

    void deletePersonById(long id);
}
