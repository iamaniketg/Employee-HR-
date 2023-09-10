package com.geekster.EmloyeeManagementSystem.controller;

import com.geekster.EmloyeeManagementSystem.model.Employee;
import com.geekster.EmloyeeManagementSystem.model.dto.SignInInput;
import com.geekster.EmloyeeManagementSystem.model.dto.SignUpOutput;
import com.geekster.EmloyeeManagementSystem.service.AuthenticationService;
import com.geekster.EmloyeeManagementSystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("user/signup")
    public SignUpOutput signUpEmployee(@RequestBody Employee employee)
    {

        return employeeService.signUpEmployee(employee);
    }

    @PostMapping("user/signIn")
    public String signInEmployee(@RequestBody @Valid SignInInput signInInput)
    {
        return employeeService.signInEmployee(signInInput);
    }

    @DeleteMapping("user/signOut")
    public String signOutEmployee(String email, String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return employeeService.signOutEmployee(email);
        }
        else {
            return "Sign out not allowed for non authenticated employee.";
        }

    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployee(@PathVariable Long id) {
        return employeeService.getById(id);
    }


    @PutMapping("/{id}")
    public void updatePersonalInfo(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        employeeService.updatePersonalInfo(id, updatedEmployee);
    }



}
