package com.docmanager.docmanagerbackend.department;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository repository;

    public List<DepartmentDto> getAllDepartments() {
        return repository.findAll()
                .stream()
                .map(department -> {
                    DepartmentDto departmentDto = new DepartmentDto();
                    departmentDto.setId(department.getDepId());
                    departmentDto.setName(department.getName());
                    return departmentDto;
                })
                .toList();
    }

    public Department getDepartmentById(int depId) {
        return repository.findById(depId).get();
    }
}
