package com.docmanager.docmanagerbackend.document;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping("/api/doc/upload")
    public ResponseEntity uploadDocument(@RequestParam("file") MultipartFile file) {
        return documentService.uploadDocument(file);
    }

    @GetMapping("/api/doc")
    public String getResponse() {
        String authorName = SecurityContextHolder.getContext().getAuthentication().getName();
        return authorName;
    }
}
