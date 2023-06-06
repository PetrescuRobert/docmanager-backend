package com.docmanager.docmanagerbackend.documenttypes;

import org.springframework.data.jpa.repository.JpaRepository;

//repository for the document types
public interface DocumentTypesRepository extends JpaRepository<DocumentTypes, Integer> {
}
