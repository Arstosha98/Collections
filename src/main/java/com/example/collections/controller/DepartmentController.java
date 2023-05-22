package com.example.collections.controller;

import com.example.collections.model.Employee;
import com.example.collections.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/min-salary")
    public Employee getMin(@RequestParam("departmentId") int department){
        return departmentService.getEmployeeMinSalary(department);
    }
    @GetMapping("/max-salary")
    public Employee getMax(@RequestParam("departmentId") int department){
        return departmentService.getEmployeeMaxSalary(department);
    }
    @GetMapping(value = "/all", params = "departmentId")
    private List<Employee> getAll(@RequestParam("departmentId")int department){
        return departmentService.getEmployeeByDepartment(department);
    }
    @GetMapping(value = "/all")
    public Map<Integer,List<Employee>> getAll(){
        return departmentService.getEmployeeGroped();
    }
}