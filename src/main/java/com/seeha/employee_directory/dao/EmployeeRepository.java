package com.seeha.employee_directory.dao;

import com.seeha.employee_directory.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// just extend the JpaRepository interface and pass it the entity type and PK type
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // gives full support of CRUD operations without a single line of code
}
