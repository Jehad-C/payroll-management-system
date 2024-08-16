package com.example.payroll.controller;

import com.example.payroll.dto.AdminDTO;
import com.example.payroll.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping("/admin/id/{id}")
    public ResponseEntity<Boolean> editAdminById(
            @PathVariable("id") Long id,
            @RequestBody AdminDTO adminDTO) {
        boolean success = adminService.updateAdminById(id, adminDTO);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(success, status);
    }

    @DeleteMapping("/admin/id/{id}")
    public ResponseEntity<Boolean> deleteAdminById(@PathVariable("id") Long id) {
        boolean success = adminService.removeAdminById(id);
        HttpStatus status = success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(success, status);
    }

    @GetMapping("/admin/id/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable("id") Long id) {
        AdminDTO adminDTO = adminService.fetchAdminById(id);
        return new ResponseEntity<>(adminDTO, HttpStatus.OK);
    }

    @GetMapping("/admin/username/{username}")
    public ResponseEntity<Long> getAdminIdByUsername(@PathVariable("username") String username) {
        long adminId = adminService.fetchAdminIdByUsername(username);
        return new ResponseEntity<>(adminId, HttpStatus.OK);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<AdminDTO>> getAdmins() {
        List<AdminDTO> adminDTOs = adminService.fetchAllAdmin();
        return new ResponseEntity<>(adminDTOs, HttpStatus.OK);
    }
}
