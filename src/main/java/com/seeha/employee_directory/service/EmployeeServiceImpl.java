package com.seeha.employee_directory.service;

import com.seeha.employee_directory.dao.EmployeeDAO;
import com.seeha.employee_directory.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDAO employeeDAO;
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee emp) {
        return employeeDAO.save(emp);
    }

    @Transactional
    @Override
    public void delete(int id) {
        employeeDAO.delete(id);
    }
}
