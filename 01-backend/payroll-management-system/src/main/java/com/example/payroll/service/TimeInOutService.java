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

    public boolean recordTimeInOut(TimeInOutDTO timeInOutDTO) {
        try {
            TimeInOut timeInOut = TimeInOutMapper.toEntity(timeInOutDTO);
            timeInOutRepository.save(timeInOut);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TimeInOutDTO> getTimeInOuts(Long id, String entryType) {
        List<TimeInOut> timeInOuts = timeInOutRepository.findByEmployeeIdAndEntryType(id, entryType);
        return timeInOuts.stream()
                .map(TimeInOutMapper::toDTO)
                .collect(Collectors.toList());
    }
}
