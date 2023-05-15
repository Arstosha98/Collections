package com.example.collections.service;

import com.example.collections.exception.EmployeeAlreadyAddedException;
import com.example.collections.exception.EmployeeNotFoundException;
import com.example.collections.exception.EmployeeStorageIsFullException;
import com.example.collections.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private static final int SIZE_LIMIT = 5;
    private final Map<String, Employee> employees = new HashMap<>(SIZE_LIMIT);
    public Collection <Employee> getAll(){
        return employees.values();
    }

    public Employee add(Employee employee){
        if (employees.size()>= SIZE_LIMIT){
            throw new EmployeeStorageIsFullException();
        }
        if(employees.containsKey(createKey(employee))){
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(createKey(employee), employee);
        return employee;
    }
    public Employee find(String firstName, String lastName){
        Employee employee = employees.get(createKey(firstName, lastName));
        if (employee == null){
        throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public Employee remove(String firstName, String lastName){
        return employees.remove(createKey(firstName, lastName));
    }

    private static String createKey(Employee employee){
        return createKey(employee.getFirstName(), employee.getLastName());
    }
    private static String createKey(String firstName, String lastName) {
        return (firstName + lastName).toLowerCase();
    }
}