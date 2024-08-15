package com.example.payroll.converter;

import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.entity.Employee;

public class EmployeeMapper {
    public static Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setGender(employeeDTO.getGender());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setAddress(employeeDTO.getAddress());
        employee.setJobTitle(employeeDTO.getJobTitle());
        employee.setBasicSalary(employeeDTO.getBasicSalary());
        employee.setHireDate(employeeDTO.getHireDate());
        employee.setEmploymentStatus(employeeDTO.getEmploymentStatus());
        employee.setImage(employeeDTO.getImage());
        return employee;
    }

    public static EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setDateOfBirth(employee.getDateOfBirth());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPhoneNumber(employee.getPhoneNumber());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setJobTitle(employee.getJobTitle());
        employeeDTO.setBasicSalary(employee.getBasicSalary());
        employeeDTO.setHireDate(employee.getHireDate());
        employeeDTO.setEmploymentStatus(employee.getEmploymentStatus());
        employeeDTO.setImage(employee.getImage());
        return employeeDTO;
    }
}
