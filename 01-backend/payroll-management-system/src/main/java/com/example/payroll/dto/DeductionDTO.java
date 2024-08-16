package com.example.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DeductionDTO {
    private BigDecimal bir;
    private BigDecimal pagibig;
    private BigDecimal philhealth;
    private BigDecimal sss;
    private BigDecimal miscellaneous;
}
