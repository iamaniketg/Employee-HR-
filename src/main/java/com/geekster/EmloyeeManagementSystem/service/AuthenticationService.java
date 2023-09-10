package com.geekster.EmloyeeManagementSystem.service;


import com.geekster.EmloyeeManagementSystem.model.AuthenticationToken;
import com.geekster.EmloyeeManagementSystem.model.Employee;
import com.geekster.EmloyeeManagementSystem.repository.IAuthenticationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthenticationRepo authenticationRepo;

    public boolean authenticate(String email, String authTokenValue)
    {
        AuthenticationToken authToken = authenticationRepo.findFirstByTokenValue(authTokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenConnectedEmail = authToken.getEmployee().getEmployeeEmail();;

        return tokenConnectedEmail.equals(email);
    }

    public void saveAuthToken(AuthenticationToken authToken)
    {
        authenticationRepo.save(authToken);
    }

    public AuthenticationToken findFirstByEmployee(Employee employee) {
        return authenticationRepo.findFirstByEmployee(employee);
    }

    public void removeToken(AuthenticationToken token) {
        authenticationRepo.delete(token);
    }
}
