package org.xsy.dorm;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.xsy.dorm.model.admin.Admin;
import org.xsy.dorm.repository.AdminRepository;

@SpringBootApplication
public class DormApplication {

    public static void main(String[] args) {
        SpringApplication.run(DormApplication.class, args);
    }

    @Bean
    CommandLineRunner testAuth(AdminRepository repo, PasswordEncoder encoder) {
        return args -> {
            Admin admin = repo.findByUsername("xsy").orElseThrow();
            System.out.println("数据库密码: " + admin.getPasswordHash());
            System.out.println("密码匹配结果: " + encoder.matches("123456", admin.getPasswordHash()));
        };
    }

    @Bean
    CommandLineRunner resetPassword(AdminRepository repo, PasswordEncoder encoder) {
        return args -> {
            Admin admin = repo.findByUsername("xsy").orElseThrow();
            admin.setPasswordHash(encoder.encode("123456"));
            repo.save(admin);
            System.out.println("密码已重置为: 123456");
        };
    }
}
