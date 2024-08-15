package com.example.payroll.service;

import com.example.payroll.converter.TimeInOutMapper;
import com.example.payroll.dao.TimeInOutRepository;
import com.example.payroll.dto.TimeInOutDTO;
import com.example.payroll.entity.TimeInOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeInOutService {
    private final TimeInOutRepository timeInOutRepository;

    @Autowired
    public TimeInOutService(TimeInOutRepository timeInOutRepository) {
        this.timeInOutRepository = timeInOutRepository;
    }

    public List<TimeInOutDTO> getTimeIns(Long id) {
        List<TimeInOut> timeIns = timeInOutRepository.findByEmployeeIdAndEntryType(id, "IN");
        return timeIns.stream()
                .map(TimeInOutMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<TimeInOutDTO> getTimeOuts(Long id) {
        List<TimeInOut> timeOuts = timeInOutRepository.findByEmployeeIdAndEntryType(id, "OUT");
        return timeOuts.stream()
                .map(TimeInOutMapper::toDTO)
                .collect(Collectors.toList());
    }
}
