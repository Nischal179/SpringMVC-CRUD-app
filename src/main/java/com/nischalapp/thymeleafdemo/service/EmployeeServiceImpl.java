package com.nischalapp.thymeleafdemo.service;

import com.nischalapp.thymeleafdemo.entity.Employee;
import com.nischalapp.thymeleafdemo.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theemployeeRepository) {

        employeeRepository = theemployeeRepository;
    }

    @Override
    public List<Employee> findAll() {

        return employeeRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if(result.isPresent()) {
            theEmployee =result.get();
        }
        else {
            throw new RuntimeException("Did not find employee id - "+ theId);
        }

        return theEmployee; //delegate calls to DAO
    }

    @Override
    public Employee save(Employee theEmployee) {

        return employeeRepository.save(theEmployee); //delegate calls to DAO
    }


    @Override
    public Employee update(Employee theEmployee, int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.setFirstName(theEmployee.getFirstName());
        employee.setLastName(theEmployee.getLastName());
        employee.setEmail(theEmployee.getEmail());
        return employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int theID) {
        employeeRepository.deleteById(theID); //delegate calls to DAO
    }
}
