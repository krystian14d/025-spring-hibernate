package com.example.cruddemo.service;

import com.example.cruddemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findALl();

    Employee findById(long id);

    Employee save(Employee employee);

    void deleteById(long id);
}
