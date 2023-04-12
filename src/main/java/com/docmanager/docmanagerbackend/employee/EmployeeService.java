package com.docmanager.docmanagerbackend.employee;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;

    private EmployeeDTO mapEntityToDto(Employee employee) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    private List<EmployeeDTO> mapEntitiesToDtos(List<Employee> employees) {
        return employees
                .stream()
                .map(employee -> mapEntityToDto(employee))
                .collect(Collectors.toList());
    }

    public List<EmployeeDTO> getAllEmployees() {
        return mapEntitiesToDtos(repository.findAll());
    }

    public EmployeeDTO getEmployeeById(int id) {
        Optional<Employee> queryResult = repository
                .findById(id);
        if (queryResult.isPresent())
            return mapEntityToDto(queryResult.get());
        else
            return null;
    }

    public Employee getEmployeeByEmail(String email) {
        Optional<Employee> employee = repository.findByEmail(email);
        return employee.isPresent() ? employee.get() : null;
    }


    public void save(Employee employee) {
        repository.save(employee);
    }
}
