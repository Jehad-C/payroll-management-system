package com.example.payroll.service;

import com.example.payroll.dto.EarningDTO;
import com.example.payroll.dto.EmployeeDTO;
import com.example.payroll.dto.PayrollDTO;
import com.example.payroll.dto.PayrollSummaryDTO;
import com.example.payroll.dto.TimeInOutDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        EarningDTO earningDTO = calculateEarnings(employeeId, payrollDTO);
        payrollSummaryDTO.setEarningDTO(earningDTO);

        BigDecimal netPay = calculateNetPay(earningDTO);
        payrollSummaryDTO.setNetPay(netPay);

        return payrollSummaryDTO;
    }

    public BigDecimal fetchBasicSalary(Long employeeId) {
        EmployeeDTO employee = employeeService.fetchEmployeeById(employeeId);
        return employee.getBasicSalary();
    }

    public BigDecimal calculateTotalHoursWorkedWithinPeriod(Long employeeId, PayrollDTO payrollDTO) {
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

    public EarningDTO calculateEarnings(Long employeeId, PayrollDTO payrollDTO) {
        EarningDTO earningDTO = new EarningDTO();

        BigDecimal basicSalary = fetchBasicSalary(employeeId);
        BigDecimal totalHoursWorked = calculateTotalHoursWorkedWithinPeriod(employeeId, payrollDTO);

        earningDTO.setBasicSalary(basicSalary);
        earningDTO.setTotalHoursWorked(totalHoursWorked);

        return earningDTO;
    }

    public BigDecimal calculateNetPay(EarningDTO earningDTO) {
        return earningDTO.getBasicSalary().multiply(earningDTO.getTotalHoursWorked());
    }
}
