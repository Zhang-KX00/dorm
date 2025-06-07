package org.xsy.dorm.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.xsy.dorm.model.admin.Admin;
import org.xsy.dorm.service.AdminService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @GetMapping
    public String listAdmins(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("activePage", "admin");
        return "admin/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/create";
    }

    @PostMapping("/save")
    public String saveAdmin(@ModelAttribute Admin admin) {
        adminService.registerAdmin(admin);
        return "redirect:/admin";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("admin", adminService.getAdminById(id));
        return "admin/edit";
    }

    @PostMapping("/update/{id}")
    public String updateAdmin(@PathVariable Integer id, @ModelAttribute Admin admin) {
        admin.setId(id);
        adminService.updateAdmin(admin);
        return "redirect:/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
        return "redirect:/admin";
    }
}