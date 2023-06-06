package com.docmanager.docmanagerbackend.documenttypes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

//service for the document types
@Service
@RequiredArgsConstructor
public class DocumentTypesService {
    public final DocumentTypesRepository documentTypesRepository;

    //method to get all the document types from the repository
    //return a list of just the document types without the id
    public List<DocumentTypes> getDocumentTypes() {
        List<DocumentTypes> documentTypes = documentTypesRepository.findAll();
        List documentTypesWithoutId = documentTypes.stream().map(documentType -> documentType.getName()).toList();
        return documentTypesWithoutId;
    }
}
