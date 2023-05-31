package com.docmanager.docmanagerbackend.auth;

import com.docmanager.docmanagerbackend.employee.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private EmployeeDTO userDetails;
    private String token;
}
