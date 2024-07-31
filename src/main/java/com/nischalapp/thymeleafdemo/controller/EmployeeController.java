package com.nischalapp.thymeleafdemo.controller;

import com.nischalapp.thymeleafdemo.entity.Employee;
import com.nischalapp.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees") //Base Mapping for URL requests
public class EmployeeController {

    private EmployeeService employeeService;

    // We are using constructor injection since, we have only one
    // constructor @Autowired annotation is optional
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get the employees from the db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);

        return "list-employees";
    }
}
