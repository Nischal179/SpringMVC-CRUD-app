package com.nischalapp.thymeleafdemo.service;

import com.nischalapp.thymeleafdemo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    Employee update(Employee theEmployee, int id);

    void deleteById(int theID);

}
