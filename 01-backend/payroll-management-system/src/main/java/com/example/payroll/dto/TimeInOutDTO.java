package com.example.payroll.dto;

import com.example.payroll.entity.Employee;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeInOutDTO {
    private Employee employee;
    private String entryType;
    private LocalDateTime entryTime;
}
