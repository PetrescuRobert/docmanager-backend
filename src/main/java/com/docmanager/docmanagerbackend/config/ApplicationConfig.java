package com.docmanager.docmanagerbackend.config;

import com.docmanager.docmanagerbackend.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final EmployeeRepository employeeRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> employeeRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
