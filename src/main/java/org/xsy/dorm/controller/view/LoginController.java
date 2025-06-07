package org.xsy.dorm.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "expired", required = false) String expired,
            @RequestParam(value = "invalid", required = false) String invalid,
            Model model) {

        if (error != null) model.addAttribute("error", "用户名或密码错误");
        if (logout != null) model.addAttribute("message", "您已成功注销");
        if (expired != null) model.addAttribute("error", "会话已过期，请重新登录");
        if (invalid != null) model.addAttribute("error", "无效会话，请重新登录");

        return "auth/login";
    }
}
