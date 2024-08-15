package com.example.payroll.controller;

import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/employee")
    public ResponseEntity<Boolean> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        boolean success = employeeService.addEmployee(employeeDTO);
        HttpStatus status = success ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(success, status);
    }

    @PutMapping("/employee/id/{id}")
    public ResponseEntity<Boolean> editEmployeeById(
            @PathVariable("id") Long id,
            @RequestBody EmployeeDTO employeeDTO) {
        boolean success = employeeService.updateEmployeeById(id, employeeDTO);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(success, status);
    }

    @DeleteMapping("/employee/id/{id}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable("id") Long id) {
        boolean success = employeeService.removeEmployeeById(id);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(success, status);
    }

    @GetMapping("/employee/id/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long id) {
        EmployeeDTO employeeDTO = employeeService.fetchEmployeeById(id);
        return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        List<EmployeeDTO> employeeDTOs = employeeService.fetchAllEmployees();
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }
}
