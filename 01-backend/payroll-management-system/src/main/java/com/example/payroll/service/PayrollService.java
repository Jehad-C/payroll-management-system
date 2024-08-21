package com.example.payroll.service;

import com.example.payroll.dto.DeductionDTO;
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

        EmployeeDTO employeeDTO = employeeService.fetchEmployeeById(employeeId);
        payrollSummaryDTO.setEmployeeDTO(employeeDTO);

        EarningDTO earningDTO = calculateEarnings(employeeDTO, payrollDTO);
        payrollSummaryDTO.setEarningDTO(earningDTO);

        DeductionDTO deductionDTO = calculateDeductionDTO();
        payrollSummaryDTO.setDeductionDTO(deductionDTO);

        BigDecimal grossPay = calculateGrossPay(earningDTO);
        payrollSummaryDTO.setGrossPay(grossPay);

        BigDecimal netPay = calculateNetPay(grossPay, deductionDTO);
        payrollSummaryDTO.setNetPay(netPay);

        return payrollSummaryDTO;
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

    public EarningDTO calculateEarnings(EmployeeDTO employeeDTO, PayrollDTO payrollDTO) {
        EarningDTO earningDTO = new EarningDTO();
        earningDTO.setBasicSalary(employeeDTO.getBasicSalary());

        BigDecimal totalHoursWorked = calculateTotalHoursWorkedWithinPeriod(employeeDTO.getId(), payrollDTO);
        earningDTO.setTotalHoursWorked(totalHoursWorked);
        earningDTO.setTotalOvertimeWorked(BigDecimal.valueOf(0));
        earningDTO.setTotalHolidayWorked(BigDecimal.valueOf(0));
        earningDTO.setBonuses(BigDecimal.valueOf(0));

        return earningDTO;
    }

    public DeductionDTO calculateDeductionDTO() {
        DeductionDTO deductionDTO = new DeductionDTO();
        deductionDTO.setBir(BigDecimal.valueOf(0));
        deductionDTO.setPagibig(BigDecimal.valueOf(0));
        deductionDTO.setPhilhealth(BigDecimal.valueOf(0));
        deductionDTO.setSss(BigDecimal.valueOf(0));
        deductionDTO.setMiscellaneous(BigDecimal.valueOf(0));
        return deductionDTO;
    }

    public BigDecimal calculateGrossPay(EarningDTO earningDTO) {
        return earningDTO.getBasicSalary()
                .multiply(earningDTO.getTotalHoursWorked())
                .add(earningDTO.getBonuses());
    }

    public BigDecimal calculateNetPay(BigDecimal grossPay, DeductionDTO deductionDTO) {
        return grossPay
                .subtract(deductionDTO.getBir())
                .subtract(deductionDTO.getPagibig())
                .subtract(deductionDTO.getPhilhealth())
                .subtract(deductionDTO.getSss())
                .subtract(deductionDTO.getMiscellaneous());
    }
}
