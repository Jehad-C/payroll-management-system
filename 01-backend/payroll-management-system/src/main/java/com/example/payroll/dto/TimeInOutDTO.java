package com.example.payroll.dto;

import com.example.payroll.entity.Employee;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TimeInOutDTO {
    private Long id;
    private Employee employee;
    private String entryType;
    private LocalDate entryTime;
}
