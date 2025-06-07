package org.xsy.dorm.controller.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.xsy.dorm.model.admin.Admin;
import org.xsy.dorm.repository.AdminRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AdminRepository adminRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**",
                                        "/static/**",
                                        "/webjars/**",
                                        "/webjars/bootstrap/5.3.2/css/**",
                                        "/webjars/bootstrap/5.3.2/js/**",
                                        "/webjars/bootstrap-icons/1.11.1/font/**",
                                        "/webjars/chart.js/3.9.1/dist/**",
                                        "/error",
                                        "/css/**",
                                        "/js/**",
                                        "/home/**",
                                        "/admin/**",
                                        "/dorms/**",
                                        "/fragments/**",
                                        "/students/**",
                                        "/layouts/**",
                                        "/images/**",
                                       "/favicon.ico",
                                "/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/auth/login?error=true")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .invalidSessionUrl("/auth/login?invalid")
                        .maximumSessions(1)
                        .expiredUrl("/auth/login?expired")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable())  // 正确的位置和方式禁用CSRF
                .headers(headers -> headers
                        .frameOptions().sameOrigin()
                )
                .userDetailsService(username ->{
                            System.out.println("尝试加载用户: " + username);
                            Admin admin = adminRepository.findByUsername(username)
                                    .orElseThrow(() -> {
                                System.out.println("用户未找到: " + username);
                                return new UsernameNotFoundException("用户未找到");
                            });
                            System.out.println("找到用户: " + admin.getUsername() + ", 密码: " + admin.getPasswordHash());
                            return admin;

                        });
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}