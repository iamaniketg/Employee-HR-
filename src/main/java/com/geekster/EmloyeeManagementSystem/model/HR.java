package com.geekster.EmloyeeManagementSystem.model;

import com.geekster.EmloyeeManagementSystem.model.enums.JobRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String HRName;

    @Enumerated(EnumType.STRING)
    private JobRole jobRole;


}