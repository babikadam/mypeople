package com.mypeopleapp.mypeopleapp.controller;


import com.mypeopleapp.mypeopleapp.model.Person;
import com.mypeopleapp.mypeopleapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/people")
    public ResponseEntity<List<Person>> getAllPeople() {

        try {
            List<Person> people = personService.getAllPersons();

            if(people.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(people, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable("id") long id) {
        try {
            Person person = personService.getPersonById(id);

            if(person == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(person, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/people")
    public ResponseEntity<Person> createPerson(@RequestBody  Person person) {
        try {
            Person aperson = personService.savePerson(new Person(
                    person.getFirstName(),
                    person.getLastName(),
                    person.getAge(),
                    person.getEmail(),
                    person.getPhoneNum()
            ));
            return  new ResponseEntity<>(aperson, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePersonById(@PathVariable("id") long id, @RequestBody Person person) {
        Person aperson = personService.updatePersonById(id, person);
        if (aperson != null) {
            return new ResponseEntity<>(personService.savePerson(aperson), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/people/{id}")
    public ResponseEntity<HttpStatus> deletePersonById(@PathVariable("id") long id) {
        try {
            personService.deletePersonById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
