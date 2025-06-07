package org.xsy.dorm.controller.admin;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    @GetMapping("/debug/auth")
    public String debugAuth(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "未认证";
        }
        return "已认证: " + authentication.getName() +
                ", 权限: " + authentication.getAuthorities();
    }
}
