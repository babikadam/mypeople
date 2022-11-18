package com.mypeopleapp.mypeopleapp.service;

import com.mypeopleapp.mypeopleapp.model.Employee;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee getEmployeeById(long id);

    void deleteEmployeeById(long id);

    Employee updateEmployeeById(long id, Employee employeeRequest);
}
