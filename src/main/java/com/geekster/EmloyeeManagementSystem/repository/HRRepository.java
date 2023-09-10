package com.geekster.EmloyeeManagementSystem.repository;

import com.geekster.EmloyeeManagementSystem.model.HR;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HRRepository extends JpaRepository<HR,Long> {
}
