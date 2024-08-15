package com.example.payroll.dao;

import com.example.payroll.entity.TimeInOut;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeInOutRepository extends JpaRepository<TimeInOut, Long> {
    List<TimeInOut> findByEmployeeIdAndEntryType(Long employeeId, String entryType);
}
