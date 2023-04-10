package com.docmanager.docmanagerbackend.employee;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeRepository repository;
    private final ModelMapper modelMapper = new ModelMapper();

    @GetMapping("/api/employees")
    List<EmployeeDTO> getAllEmployees() {
        return repository
                .findAll()
                .stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

}
