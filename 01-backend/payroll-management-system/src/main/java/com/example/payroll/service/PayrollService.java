package com.example.payroll.service;

import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.dto.PayrollDTO;
import com.example.payroll.dto.PayrollSummaryDTO;
import com.example.payroll.dto.TimeInOutDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PayrollService {
    private final EmployeeService employeeService;
    private final TimeInOutService timeInOutService;

    @Autowired
    public PayrollService(EmployeeService employeeService, TimeInOutService timeInOutService) {
        this.employeeService = employeeService;
        this.timeInOutService = timeInOutService;
    }

    public PayrollSummaryDTO generatePayroll(Long employeeId, PayrollDTO payrollDTO) {
        PayrollSummaryDTO payrollSummaryDTO = new PayrollSummaryDTO();
        BigDecimal basicSalary = fetchBasicSalary(employeeId);
        BigDecimal totalHoursWorked = calculateTotalHoursWorked(employeeId, payrollDTO);
        BigDecimal netPay = calculateNetPay(basicSalary, totalHoursWorked);

        payrollSummaryDTO.setBasicSalary(basicSalary);
        payrollSummaryDTO.setTotalHoursWorked(totalHoursWorked);
        payrollSummaryDTO.setNetPay(netPay);
        return payrollSummaryDTO;
    }

    public BigDecimal fetchBasicSalary(Long employeeId) {
        EmployeeDTO employee = employeeService.fetchEmployeeById(employeeId);
        return employee.getBasicSalary();
    }

    public BigDecimal calculateTotalHoursWorked(Long employeeId, PayrollDTO payrollDTO) {
        LocalDateTime startDate = payrollDTO.getStartDate();
        LocalDateTime endDate = payrollDTO.getEndDate();
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        List<TimeInOutDTO> timeIns = timeInOutService.getTimeInOuts(employeeId, "IN");
        List<TimeInOutDTO> timeOuts = timeInOutService.getTimeInOuts(employeeId, "OUT");
        BigDecimal totalHoursWorked = BigDecimal.ZERO;

        for (int i = 0; i < daysBetween; i++) {
            LocalDateTime currentDate = startDate.plusDays(i);
            Optional<LocalDateTime> timeIn = timeIns.stream()
                    .map(TimeInOutDTO::getEntryTime)
                    .filter(entryTime -> isSameDay(entryTime, currentDate))
                    .findFirst();

            Optional<LocalDateTime> timeOut = timeOuts.stream()
                    .map(TimeInOutDTO::getEntryTime)
                    .filter(entryTime -> isSameDay(entryTime, currentDate))
                    .max(Comparator.naturalOrder());

            if (timeIn.isPresent() && timeOut.isPresent()) {
                long hoursWorked = ChronoUnit.HOURS.between(timeIn.get(), timeOut.get());
                totalHoursWorked = totalHoursWorked.add(BigDecimal.valueOf(hoursWorked));
            }
        }

        return totalHoursWorked;
    }

    public boolean isSameDay(LocalDateTime entryTime, LocalDateTime targetDay) {
        return !entryTime.isBefore(targetDay) && entryTime.isBefore(targetDay.plusDays(1));
    }

    public BigDecimal calculateNetPay(BigDecimal basicSalary, BigDecimal totalHoursWorked) {
        BigDecimal hourlyRate = basicSalary.divide(BigDecimal.valueOf(8), RoundingMode.HALF_UP);
        return totalHoursWorked.multiply(hourlyRate);
    }
}
