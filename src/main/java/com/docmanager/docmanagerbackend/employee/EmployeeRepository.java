package com.docmanager.docmanagerbackend.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByEmail(String email);

}
