package com.example.payroll.converter;

import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.entity.Employee;

public class EmployeeMapper {
    public static Employee toEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setDob(employeeDTO.getDob());
        employee.setGender(employeeDTO.getGender());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPersonalPhone(employeeDTO.getPersonalPhone());
        employee.setHomePhone(employeeDTO.getHomePhone());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPostalCode(employeeDTO.getPostalCode());
        employee.setJobTitle(employeeDTO.getJobTitle());
        employee.setBasicSalary(employeeDTO.getBasicSalary());
        employee.setDateHired(employeeDTO.getDateHired());
        employee.setStatus(employeeDTO.getStatus());
        employee.setImage(employeeDTO.getImage());
        return employee;
    }

    public static EmployeeDTO toDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setMiddleName(employee.getMiddleName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setDob(employee.getDob());
        employeeDTO.setGender(employee.getGender());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPersonalPhone(employee.getPersonalPhone());
        employeeDTO.setHomePhone(employee.getHomePhone());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setPostalCode(employee.getPostalCode());
        employeeDTO.setJobTitle(employee.getJobTitle());
        employeeDTO.setBasicSalary(employee.getBasicSalary());
        employeeDTO.setDateHired(employee.getDateHired());
        employeeDTO.setStatus(employee.getStatus());
        employeeDTO.setImage(employee.getImage());
        return employeeDTO;
    }
}
