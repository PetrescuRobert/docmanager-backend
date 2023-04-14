package com.docmanager.docmanagerbackend.document;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
    Optional<Document> findByName(String name);

    List<Document> findByAuthor(int id);
}
