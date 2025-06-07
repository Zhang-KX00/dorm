package org.xsy.dorm.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xsy.dorm.model.admin.Admin;
import org.xsy.dorm.repository.AdminRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);


    public Admin registerAdmin(Admin admin) {
        if (admin.getPasswordHash() == null || admin.getPasswordHash().isEmpty()) {
            try {
                throw new IllegalAccessException("密码不能为空");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        if (adminRepository.existsByUsername(admin.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }
        if (adminRepository.existsByEmail(admin.getEmail())) {
            throw new RuntimeException("邮箱已存在");
        }

        admin.setPasswordHash(admin.getPasswordHash());
        return adminRepository.save(admin);
//        admin.setPasswordHash(passwordEncoder.encode(admin.getPasswordHash()));
//        admin.setRole(AdminRole.ADMIN);
//        return adminRepository.save(admin);
    }

    // 添加密码验证方法
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Integer id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("管理员不存在"));
    }

    public Admin updateAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);
    }

    public void recordLogin(Integer adminId) {
        Admin admin = getAdminById(adminId);
        admin.setLastLogin(LocalDateTime.now());
        adminRepository.save(admin);
    }

    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户未找到"));
    }
    public long countAdmins() {
        return adminRepository.countAdmins();
    }
}