package org.xsy.dorm.controller.view;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.xsy.dorm.service.AdminService;
import org.xsy.dorm.service.DormService;
import org.xsy.dorm.service.StudentService;

@Controller
public class HomeController {

    private final DormService dormService;
    private final StudentService studentService;
    private final AdminService adminService;

    public HomeController(DormService dormService, StudentService studentService, AdminService adminService) {
        this.dormService = dormService;
        this.studentService = studentService;
        this.adminService = adminService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/auth/login";
        }
        model.addAttribute("title", "仪表盘");
        model.addAttribute("username", "当前用户"); // 右上角显示的用户名
        model.addAttribute("activePage", "dashboard"); // 当前激活的菜单项
        model.addAttribute("activePage", "dashboard");
        model.addAttribute("dormCount",dormService.countDorms());
        model.addAttribute("studentCount",studentService.countStudents());
        model.addAttribute("adminCount",adminService.countAdmins());
        // 添加用户名到模型
        model.addAttribute("username", authentication.getName());
        return "home/dashboard"; // 明确指定模板路径
    }
}