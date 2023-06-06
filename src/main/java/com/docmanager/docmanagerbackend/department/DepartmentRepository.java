package com.docmanager.docmanagerbackend.department;

import com.docmanager.docmanagerbackend.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Department findByManager(Employee manager);
}
