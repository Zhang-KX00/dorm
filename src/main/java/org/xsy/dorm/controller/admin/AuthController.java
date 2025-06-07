package org.xsy.dorm.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xsy.dorm.model.admin.Admin;
import org.xsy.dorm.repository.AdminRepository;
import org.xsy.dorm.service.AdminService;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AdminService adminService;
    private final AdminRepository adminRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerAdmin(@ModelAttribute Admin admin, @RequestParam String password) {
        admin.setPasswordHash(password); // 确保密码被设置
        adminService.registerAdmin(admin);
        adminRepository.existsByEmail(admin.getEmail());
        admin.getFullName();
        adminRepository.save(admin);
        return "redirect:/auth/login?registered=true";
    }
}