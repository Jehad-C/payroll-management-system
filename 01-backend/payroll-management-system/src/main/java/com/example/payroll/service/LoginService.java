package com.example.payroll.service;

import com.example.payroll.dto.AdminDTO;
import com.example.payroll.util.PasswordUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final AdminService adminService;

    @Autowired
    public LoginService(AdminService adminService) {
        this.adminService = adminService;
    }

    public boolean authenticateLogin(AdminDTO adminDTO) {
        try {
            String username = adminDTO.getUsername();
            String password = adminDTO.getPassword();
            return isUsernameValid(username) && isPasswordValid(username, password);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUsernameValid(String username) {
        return adminService.existsByUsername(username);
    }

    public boolean isPasswordValid(String username, String password) {
        AdminDTO admin = adminService.fetchAdminByUsername(username);
        return admin.getPassword().equals(PasswordUtil.encryptPassword(password));
    }
}
