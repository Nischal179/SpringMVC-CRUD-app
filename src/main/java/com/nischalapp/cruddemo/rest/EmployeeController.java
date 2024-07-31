package com.nischalapp.cruddemo.rest;


import com.nischalapp.cruddemo.entity.Employee;
import com.nischalapp.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable Integer id) {

        Employee theEmployee = employeeService.findById(id);

        if(theEmployee == null) {
            throw new RuntimeException("Employee id not found - "+ id);
        }
        return theEmployee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        // also just in case they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee( @PathVariable int id, @RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.update(theEmployee, id);
        return dbEmployee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee tempEmployee = employeeService.findById(id);

        if (tempEmployee == null) {
            throw new RuntimeException("Employee id not found - "+ id);
        }
        employeeService.deleteById(id);
        return ("Deleted employee id - "+id);
    }
}
