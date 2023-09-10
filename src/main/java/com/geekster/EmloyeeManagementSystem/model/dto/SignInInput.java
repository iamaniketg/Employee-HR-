package com.geekster.EmloyeeManagementSystem.model.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInInput {

    @Pattern(regexp = "^.+@(?![Ii][Nn][Ss][Tt][Aa][Dd][Mm][Ii][Nn]\\.[Cc][Oo][Mm]$).+$")
    private String email;
    private String password;
}