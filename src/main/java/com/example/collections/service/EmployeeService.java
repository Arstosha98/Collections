package com.example.collections.service;

import com.example.collections.exception.EmployeeAlreadyAddedException;
import com.example.collections.exception.EmployeeNotFoundException;
import com.example.collections.exception.EmployeeStorageIsFullException;
import com.example.collections.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class EmployeeService {
    private static final int SIZE_LIMIT = 5;
    private final Collection <Employee> employees = new ArrayList(SIZE_LIMIT);
    public Collection <Employee> getAll(){
        return Collections.unmodifiableCollection(employees);
    }

    public Employee add(Employee employee){
        if (employees.size()>= SIZE_LIMIT){
            throw new EmployeeStorageIsFullException();
        }
        if(employees.contains(employee)){
            throw new EmployeeAlreadyAddedException();
        }
        employees.add(employee);
        return employee;
    }
    public Employee find(String firstName, String lastName){
        for (Employee employee : employees) {
            if(employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)){
                return employee;
            }
        }
        throw new EmployeeNotFoundException();
    }
    public Employee remove(String firstName, String lastName){
        Employee employee = find(firstName, lastName);
        employees.remove(employee);
        return employee;
    }
}