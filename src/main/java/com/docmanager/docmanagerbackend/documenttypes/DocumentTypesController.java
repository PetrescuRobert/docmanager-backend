package com.docmanager.docmanagerbackend.documenttypes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//controller to expose the document types to the frontend
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class DocumentTypesController {
    private final DocumentTypesService documentTypesService;
    //method to get all the document types
    @GetMapping("/api/doc/types")
    //the method will return a 200 status code if the request is successful and the list of document types in the body
    //if the request is not successful it will return a 404 status code
    public ResponseEntity getDocumentTypes() {
        List documentTypes = documentTypesService.getDocumentTypes();
        return documentTypes != null ?
                ResponseEntity.ok(documentTypes) : ResponseEntity.notFound().build();
    }
}
