package com.seatech.employeemanagement.repository;

import com.seatech.employeemanagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String s);
}
