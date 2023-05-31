package com.docmanager.docmanagerbackend.document;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/api/doc/upload-file")
    public ResponseEntity uploadDocument(@RequestParam("file") MultipartFile file) {
        return documentService.uploadDocument(file);
    }

    @PostMapping("/api/doc/upload-files")
    public ResponseEntity uploadDocuments(@RequestParam("files") MultipartFile[] files) {
        return documentService.uploadDocuments(files);
    }

    @GetMapping("/api/doc/id={id}")
    public ResponseEntity getDocumentById(@PathVariable int id) {
        DocumentDTO documentDTO = documentService.getDocumentById(id);
        return documentDTO != null ?
                ResponseEntity.ok(documentDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/doc/name={name}")
    public ResponseEntity getDocumentById(@PathVariable String name) {
        DocumentDTO documentDTO = documentService.getDocumentByName(name);
        return documentDTO != null ?
                ResponseEntity.ok(documentDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/doc/emp_id={id}")
    public ResponseEntity getDocumentByEmployeeId(@PathVariable int id) {
        List<DocumentDTO> documentDTO = documentService.getDocumentsByEmployeeId(id);
        return documentDTO != null ?
                ResponseEntity.ok(documentDTO) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/doc/{id}")
    public ResponseEntity deleteDocumentById(@PathVariable int id) {
        return documentService.deleteDocumentById(id) ?
                ResponseEntity.ok("Document with id: " + id + " was deleted.") :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("Document with idd: " + id + " does not exists.");
    }

}
