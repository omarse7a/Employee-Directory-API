package com.seeha.employee_directory.dao;

import com.seeha.employee_directory.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private EntityManager entityManager;

    // Injecting the entity manager from the spring container
    @Autowired
    public EmployeeDAOImpl(EntityManager em){
        entityManager = em;
    }

    @Override
    public List<Employee> findAll(){
        // defining the query using JPQL
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);
        // executing query and returning its results
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee save(Employee emp) {
        // if emp id == 0 save/insert
        // else update
        Employee dbEmployee = entityManager.merge(emp);
        return dbEmployee;  // this has the update id (in case of insertion)
    }

    @Override
    public void delete(int id) {
        // get the employee by id
        Employee emp = entityManager.find(Employee.class, id);
        // delete the employee from the DB
        entityManager.remove(emp);
    }
}
