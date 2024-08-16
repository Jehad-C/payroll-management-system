package com.example.payroll.service;

import com.example.payroll.converter.AdminMapper;
import com.example.payroll.dao.AdminRepository;
import com.example.payroll.dto.AdminDTO;
import com.example.payroll.entity.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public boolean addAdmin(AdminDTO adminDTO) {
        try {
            Admin admin = AdminMapper.toEntity(adminDTO);
            adminRepository.save(admin);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateAdminById(Long id, AdminDTO adminDTO) {
        try {
            Admin admin = AdminMapper.toEntity(adminDTO);
            admin.setId(id);
            adminRepository.save(admin);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeAdminById(Long id) {
        try {
            adminRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AdminDTO fetchAdminById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.map(AdminMapper::toDTO).orElse(null);
    }

    public long fetchAdminIdByUsername(String username) {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        return admin.map(AdminMapper::toDTO).map(AdminDTO::getId).orElse(-1L);
    }

    public AdminDTO fetchAdminByUsername(String username) {
        long adminId = fetchAdminIdByUsername(username);
        return fetchAdminById(adminId);
    }

    public List<AdminDTO> fetchAllAdmin() {
        List<Admin> admins = adminRepository.findAll();
        return admins.stream()
                .map(AdminMapper::toDTO)
                .collect(Collectors.toList());
    }

    public boolean existsByUsername(String username) {
        return adminRepository.existsByUsername(username);
    }
}
