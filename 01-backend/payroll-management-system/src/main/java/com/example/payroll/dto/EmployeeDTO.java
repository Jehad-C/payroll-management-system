package com.example.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String phoneNumber;
    private String address;
    private String jobTitle;
    private BigDecimal basicSalary;
    private LocalDate hireDate;
    private String employmentStatus;
    private String department;
    private byte[] image;
}
