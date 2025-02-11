package com.seeha.employee_directory.dao;

import com.seeha.employee_directory.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee emp);
    void delete(int id);
}
