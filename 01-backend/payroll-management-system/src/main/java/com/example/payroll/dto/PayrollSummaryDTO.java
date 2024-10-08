package com.example.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayrollSummaryDTO {
    private EmployeeDTO employeeDTO;
    private EarningDTO earningDTO;
    private DeductionDTO deductionDTO;
    private BigDecimal grossPay;
    private BigDecimal netPay;
}
