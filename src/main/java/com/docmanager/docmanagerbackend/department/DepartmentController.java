package com.docmanager.docmanagerbackend.department;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping("/api/departments/all")
    public ResponseEntity getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }
}
