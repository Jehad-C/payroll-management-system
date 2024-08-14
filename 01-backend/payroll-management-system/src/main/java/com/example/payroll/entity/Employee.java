package com.example.payroll.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "employee_info")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "dob")
    private Date dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "personal_phone")
    private String personalPhone;

    @Column(name = "home_phone")
    private String homePhone;

    @Column(name = "address")
    private String address;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "basic_salary")
    private BigDecimal basicSalary;

    @Column(name = "date_hired")
    private Date dateHired;

    @Column(name = "status")
    private String status;

    @Column(name = "image")
    private byte[] image;
}
