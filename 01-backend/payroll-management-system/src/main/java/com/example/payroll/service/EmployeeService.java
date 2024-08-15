package com.example.payroll.service;

import com.example.payroll.converter.EmployeeMapper;
import com.example.payroll.dao.EmployeeRepository;
import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        try {
            Employee employee = EmployeeMapper.toEntity(employeeDTO);
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
        try {
            Employee employee = EmployeeMapper.toEntity(employeeDTO);
            employee.setId(id);
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean removeEmployeeById(Long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public EmployeeDTO fetchEmployeeById(Long id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        return existingEmployee.map(EmployeeMapper::toDTO).orElse(null);
    }

    public List<EmployeeDTO> fetchAllEmployees() {
        List<Employee> existingEmployees = employeeRepository.findAll();
        return existingEmployees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
