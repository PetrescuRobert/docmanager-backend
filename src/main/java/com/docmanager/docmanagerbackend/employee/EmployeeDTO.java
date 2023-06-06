package com.docmanager.docmanagerbackend.employee;

import com.docmanager.docmanagerbackend.department.Department;
import com.docmanager.docmanagerbackend.department.DepartmentDto;
import lombok.Data;

@Data
public class EmployeeDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private DepartmentDto department;
}
