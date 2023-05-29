package com.docmanager.docmanagerbackend.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService; //this should be a service reference

    @GetMapping("/api/employees")
    List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/api/employee/{id}")
    ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int id) {
        EmployeeDTO requestResponse = employeeService.getEmployeeDtoById(id);
        return requestResponse != null ?
                ResponseEntity.ok(requestResponse) : ResponseEntity.notFound().build();
    }

}
