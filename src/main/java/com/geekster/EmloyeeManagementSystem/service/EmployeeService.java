package com.geekster.EmloyeeManagementSystem.service;

import com.geekster.EmloyeeManagementSystem.model.AuthenticationToken;
import com.geekster.EmloyeeManagementSystem.model.Employee;
import com.geekster.EmloyeeManagementSystem.model.dto.SignInInput;
import com.geekster.EmloyeeManagementSystem.model.dto.SignUpOutput;
import com.geekster.EmloyeeManagementSystem.repository.EmployeeRepository;
import com.geekster.EmloyeeManagementSystem.service.emailUtility.EmailHandler;
import com.geekster.EmloyeeManagementSystem.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AuthenticationService authenticationService;

    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }

    public void updatePersonalInfo(Long id, Employee updatedEmployee) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            // Update personal info fields (name, email, contact, address)
            employee.setName(updatedEmployee.getName());
            employee.setEmployeeEmail(updatedEmployee.getEmployeeEmail());
            employee.setContact(updatedEmployee.getContact());
            employee.setAddress(updatedEmployee.getAddress());
            employeeRepository.save(employee);
        }
    }


    public SignUpOutput signUpEmployee(Employee employee) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = employee.getEmployeeEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this user email already exists ??
        Employee existingemployee = employeeRepository.findFirstByEmployeeEmail(newEmail);

        if(existingemployee != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(employee.getPassword());

            //saveAppointment the user with the new encrypted password

            employee.setPassword(encryptedPassword);
            employeeRepository.save(employee);

            return new SignUpOutput(signUpStatus, "Employee registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public String signInEmployee(SignInInput signInInput) {

        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this user email already exists ??
        Employee existingEmployee  = employeeRepository.findFirstByEmployeeEmail(signInEmail);

        if(existingEmployee == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingEmployee.getPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingEmployee);
                authenticationService.saveAuthToken(authToken);

                EmailHandler.sendEmail("aniket9766228627@gmail.com","email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }

    public String signOutEmployee(String email) {
        Employee employee = employeeRepository.findFirstByEmployeeEmail(email);
        AuthenticationToken token = authenticationService.findFirstByEmployee(employee);
        authenticationService.removeToken(token);
        return "Employee Signed out successfully";
    }


    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
