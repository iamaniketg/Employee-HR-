package com.geekster.EmloyeeManagementSystem.service;

import com.geekster.EmloyeeManagementSystem.model.Employee;
import com.geekster.EmloyeeManagementSystem.model.HR;
import com.geekster.EmloyeeManagementSystem.repository.EmployeeRepository;
import com.geekster.EmloyeeManagementSystem.repository.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HRService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    HRRepository hrRepository;


}
