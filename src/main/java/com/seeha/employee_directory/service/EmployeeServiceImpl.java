package com.seeha.employee_directory.service;

import com.seeha.employee_directory.dao.EmployeeRepository;
import com.seeha.employee_directory.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeeRepository.findById(id);
        Employee employee = null;
        if(result.isPresent()){
            employee = result.get();
        }
        else {
            throw new RuntimeException("Employee id is not found - " + id);
        }
        return employee;
    }

    // JpaRepository provide transactional management internally
//    @Transactional
    @Override
    public Employee save(Employee emp) {
        return employeeRepository.save(emp);
    }

//    @Transactional
    @Override
    public void delete(int id) {
        employeeRepository.deleteById(id);
    }
}
