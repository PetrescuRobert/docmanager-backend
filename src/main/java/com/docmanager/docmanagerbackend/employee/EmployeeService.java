package com.docmanager.docmanagerbackend.employee;

import com.docmanager.docmanagerbackend.department.Department;
import com.docmanager.docmanagerbackend.department.DepartmentRepository;
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
    private final DepartmentRepository departmentRepository;

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

    public Employee getEmployeeById(int id) {
        Optional<Employee> queryResult = repository
                .findById(id);
        if (queryResult.isPresent())
            return queryResult.get();
        else
            return null;
    }

    public EmployeeDTO getEmployeeDtoById(int id) {
        return mapEntityToDto(getEmployeeById(id));
    }
    public EmployeeDTO getEmployeeDtoByEmail(String email) {
        return mapEntityToDto(getEmployeeByEmail(email));
    }

    public Employee getEmployeeByEmail(String email) {
        Optional<Employee> employee = repository.findByEmail(email);
        return employee.isPresent() ? employee.get() : null;
    }


    public void save(Employee employee) {
        repository.save(employee);
    }

    public List<EmployeeDTO> getEmployeesByDepartment(int id) {
        Employee employee = getEmployeeById(id);
        Department employeeDepartment = departmentRepository.findByManager(employee);
        List<Employee> employees = repository.findByDepartment(employeeDepartment);
        //if in the list is the employee with id == id, remove it
        employees.removeIf(e -> e.getEmployeeId() == id);
        return mapEntitiesToDtos(employees);
    }
    public List<EmployeeDTO> getEmployeesByDepartmentId(int id) {
        Department department = departmentRepository.findById(id).get();
        List<Employee> employees = repository.findByDepartment(department);
        return mapEntitiesToDtos(employees);
    }
}
