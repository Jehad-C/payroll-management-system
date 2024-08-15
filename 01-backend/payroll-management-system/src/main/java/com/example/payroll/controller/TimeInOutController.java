package com.example.payroll.controller;

import com.example.payroll.dto.TimeInOutDTO;
import com.example.payroll.service.TimeInOutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TimeInOutController {
    private final TimeInOutService timeInOutService;

    @Autowired
    public TimeInOutController(TimeInOutService timeInOutService) {
        this.timeInOutService = timeInOutService;
    }

    @PostMapping("/time_in_out")
    public ResponseEntity<Boolean> createTimeInOut(@RequestBody TimeInOutDTO timeInOutDTO) {
        boolean success = timeInOutService.recordTimeInOut(timeInOutDTO);
        HttpStatus status = success ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(success, status);
    }
}
