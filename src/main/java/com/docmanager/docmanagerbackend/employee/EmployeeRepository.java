package com.docmanager.docmanagerbackend.employee;

import com.docmanager.docmanagerbackend.department.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findByDepartment(Department employeeDepartment);
}
