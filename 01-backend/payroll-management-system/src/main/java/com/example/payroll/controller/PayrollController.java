package com.example.payroll.controller;

import com.example.payroll.dto.PayrollDTO;
import com.example.payroll.dto.PayrollSummaryDTO;
import com.example.payroll.service.PayrollService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PayrollController {
    private final PayrollService payrollService;

    @Autowired
    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @PostMapping("/payroll/id/{id}")
    public ResponseEntity<PayrollSummaryDTO> getPayrollSummary(
            @PathVariable("id") Long id,
            @RequestBody PayrollDTO payrollDTO) {
        PayrollSummaryDTO payrollSummaryDTO = payrollService.generatePayroll(id, payrollDTO);
        return new ResponseEntity<>(payrollSummaryDTO, HttpStatus.OK);
    }
}
