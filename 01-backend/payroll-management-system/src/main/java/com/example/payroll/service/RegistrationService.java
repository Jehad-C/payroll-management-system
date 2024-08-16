package com.example.payroll.service;

import com.example.payroll.dto.AdminDTO;
import com.example.payroll.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final AdminService adminService;

    @Autowired
    public RegistrationService(AdminService adminService) {
        this.adminService = adminService;
    }

    public boolean registerAdmin(AdminDTO adminDTO) {
        try {
            String username = adminDTO.getUsername();
            String password = adminDTO.getPassword();

            if (!isUsernameValid(username) || !isPasswordValid(password)) {
                return false;
            }

            adminDTO.setPassword(PasswordUtil.encryptPassword(password));
            return adminService.addAdmin(adminDTO);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUsernameValid(String username) {
        return !adminService.existsByUsername(username);
    }

    public boolean isPasswordValid(String password) {
        return password != null && !password.isBlank();
    }
}
