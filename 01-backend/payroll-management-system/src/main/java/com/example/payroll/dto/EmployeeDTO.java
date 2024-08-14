package com.example.payroll.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dob;
    private String gender;
    private String email;
    private String personalPhone;
    private String homePhone;
    private String address;
    private String postalCode;
    private String jobTitle;
    private BigDecimal basicSalary;
    private Date dateHired;
    private String status;
    private byte[] image;
}
