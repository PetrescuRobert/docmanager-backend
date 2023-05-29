package com.docmanager.docmanagerbackend.auth;

import com.docmanager.docmanagerbackend.config.JwtService;
import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.employee.EmployeeRepository;
import com.docmanager.docmanagerbackend.employee.EmployeeService;
import com.docmanager.docmanagerbackend.employee.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        var  employee = Employee.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        if (employeeService.getEmployeeByEmail(request.getEmail()) == null)
            employeeService.save(employee);
        else
            return AuthenticationResponse
                    .builder()
                    .token(null)
                    .build();
        var jwtToken = jwtService.generateToken(employee);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request.getEmail());
        System.out.println(request.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var employee = employeeService.getEmployeeByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(employee);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
