package com.mypeopleapp.mypeopleapp.repository;

import com.mypeopleapp.mypeopleapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {

}
