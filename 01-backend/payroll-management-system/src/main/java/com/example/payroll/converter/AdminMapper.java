package com.example.payroll.converter;

import com.example.payroll.dto.AdminDTO;
import com.example.payroll.entity.Admin;

public class AdminMapper {
    public static Admin toEntity(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setUsername(adminDTO.getUsername());
        admin.setPassword(adminDTO.getPassword());
        return admin;
    }

    public static AdminDTO toDTO(Admin admin) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setUsername(admin.getUsername());
        adminDTO.setPassword(admin.getPassword());
        return adminDTO;
    }
}
