package com.example.payroll.converter;

import com.example.payroll.dto.TimeInOutDTO;
import com.example.payroll.entity.TimeInOut;

public class TimeInOutMapper {
    public static TimeInOut toEntity(TimeInOutDTO timeInOutDTO) {
        TimeInOut timeInOut = new TimeInOut();
        timeInOut.setEmployee(timeInOutDTO.getEmployee());
        timeInOut.setEntryType(timeInOutDTO.getEntryType());
        timeInOut.setEntryTime(timeInOutDTO.getEntryTime());
        return timeInOut;
    }

    public static TimeInOutDTO toDTO(TimeInOut timeInOut) {
        TimeInOutDTO timeInOutDTO = new TimeInOutDTO();
        timeInOutDTO.setEmployee(timeInOut.getEmployee());
        timeInOutDTO.setEntryType(timeInOut.getEntryType());
        timeInOutDTO.setEntryTime(timeInOut.getEntryTime());
        return timeInOutDTO;
    }
}
