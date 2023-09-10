package com.geekster.EmloyeeManagementSystem.controller;

import com.geekster.EmloyeeManagementSystem.model.Employee;
import com.geekster.EmloyeeManagementSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HRController {

    @Autowired
    EmployeeService employeeService;

    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }






}
