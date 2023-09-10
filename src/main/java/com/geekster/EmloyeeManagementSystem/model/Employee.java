package com.geekster.EmloyeeManagementSystem.model;

import com.geekster.EmloyeeManagementSystem.model.enums.JobRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String employeeEmail;
    private String password;
    private String contact;
    private String address;
    private double salary;


    @Enumerated(EnumType.STRING)
    private JobRole jobRole;

    @OneToMany(cascade = CascadeType.ALL)
    private List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<PerformanceReview> performanceReviews = new ArrayList<>();



}
