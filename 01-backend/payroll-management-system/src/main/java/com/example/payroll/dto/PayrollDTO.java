package com.example.payroll.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PayrollDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
