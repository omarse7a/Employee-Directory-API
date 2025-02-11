package com.seeha.employee_directory.controller;

import com.seeha.employee_directory.entity.Employee;
import com.seeha.employee_directory.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;
    // injecting employee service
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // expose "/employees" endpoint - return a list of employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    // expose "/employees/{employeeId}" endpoint - return an employee by id
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);
        // if the id is not valid
        if(employee == null)
            throw new RuntimeException("Employee id is not found - " + employeeId);
        return employee;
    }

    // expose "/employees" endpoint - create an employee
    // @RequestBody handles binding the JSON at request body to an Employee object
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        // by default if id is not passed its value is set to 0 (or null if Integer class is used)
        // setting id to 0 (or null) to avoid updating an existing employee
        employee.setId(0);
        // it gives the employee an DB generated id and save it to DB
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;  // return the object saved in DB
    }

    // expose "/employees" endpoint - update an existing employee
    // an existing employee will be sent in the request body (id included)
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = employeeService.save(employee);
//        // check if the employee exists in the DB (didn't work!!!)
//        if(dbEmployee == null)
//            throw new RuntimeException("Employee does not exist" );
        // return updated employee
        return dbEmployee;
    }

    // expose "/employees" endpoint - delete an existing employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee temp = employeeService.findById(employeeId);
        // throw an exception if employee does not exists in the DB
        if(temp == null)
            throw new RuntimeException("Employee id is not found - " + employeeId);
        employeeService.delete(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
