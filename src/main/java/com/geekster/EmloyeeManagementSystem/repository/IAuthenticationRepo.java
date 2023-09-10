package com.geekster.EmloyeeManagementSystem.repository;


import com.geekster.EmloyeeManagementSystem.model.AuthenticationToken;
import com.geekster.EmloyeeManagementSystem.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long> {


    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByEmployee(Employee employee);
}