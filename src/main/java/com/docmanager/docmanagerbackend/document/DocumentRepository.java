package com.docmanager.docmanagerbackend.document;

import com.docmanager.docmanagerbackend.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findByDocName(String name);

    List<Document> findByAuthor(Employee employee);
}
