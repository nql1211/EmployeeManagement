package com.seatech.employeemanagement.service;

import com.seatech.employeemanagement.model.Employee;
import com.seatech.employeemanagement.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> findAllEmployee(){
        return employeeRepo.findAll();
    }

    public void addNewEmployee(Employee employee){
        employeeRepo.save(employee);
    }

    public List<Employee> search(String s){
        return employeeRepo.findByNameContaining(s);
    }

    public void deleteEmployee(Long id){
        employeeRepo.deleteById(id);
    }

    public void saveEmployee(Employee employee){
        employeeRepo.save(employee);
    }

    public Optional<Employee> findById(Long id){
        return employeeRepo.findById(id);
    }
}
