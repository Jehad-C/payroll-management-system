package com.example.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EarningDTO {
    private BigDecimal basicSalary;
    private BigDecimal totalHoursWorked;
    private BigDecimal totalOvertimeWorked;
    private BigDecimal totalHolidayWorked;
    private BigDecimal bonuses;
}
