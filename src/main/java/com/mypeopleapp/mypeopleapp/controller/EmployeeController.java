package com.mypeopleapp.mypeopleapp.controller;

import com.mypeopleapp.mypeopleapp.model.Employee;
import com.mypeopleapp.mypeopleapp.model.Person;
import com.mypeopleapp.mypeopleapp.service.EmployeeService;
import com.mypeopleapp.mypeopleapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private PersonService personService;


    @PostMapping("/people/{personId}/employee")
    public ResponseEntity<Employee> createEmployee(@PathVariable(value = "personId") long personId,
                                                   @RequestBody Employee employeeRequest) {
        Person person = personService.getPersonById(personId);

        if(person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employeeRequest.setHireDate(new Date());
        employeeRequest.setPerson(person);

        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @GetMapping({"/people/{id}/employee", "/employee/{id}"})
    public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") long id) {
        Employee employee = employeeService.getEmployeeById(id);

        if(employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "id") long id) {
        try {
            employeeService.deleteEmployeeById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long id,
                                                   @RequestBody Employee employeeRequest) {
        Employee employee = employeeService.updateEmployeeById(id, employeeRequest);

        if(employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.OK);
    }

}


