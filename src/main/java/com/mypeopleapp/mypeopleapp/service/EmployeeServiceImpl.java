package com.mypeopleapp.mypeopleapp.service;

import com.mypeopleapp.mypeopleapp.model.Employee;
import com.mypeopleapp.mypeopleapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }

        return null;
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployeeById(long id, Employee employeeRequest) {

        Employee employee = getEmployeeById(id);

        if(employee == null) {
            return null;
        }
        employee.setEmployeeNumber(employeeRequest.getEmployeeNumber());
        employee.setActive(employeeRequest.isActive());
        employee.setSalary(employeeRequest.getSalary());

        return employee;
    }
}
