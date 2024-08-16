package com.example.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayrollSummaryDTO {
    private BigDecimal basicSalary;
    private BigDecimal totalHoursWorked;
    private BigDecimal netPay;
}
