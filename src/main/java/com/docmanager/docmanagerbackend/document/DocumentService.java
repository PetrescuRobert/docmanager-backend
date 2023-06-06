package com.docmanager.docmanagerbackend.document;

import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.employee.EmployeeService;
import com.docmanager.docmanagerbackend.task.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository repository;
    private final EmployeeService employeeService;

    private DocumentDTO mapDocumentToDocumentDto(Document document) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(document, DocumentDTO.class);
    }
    private void saveDocument(String fileName, String downloadPath, Set<Task> relatedTasks) {
        String authorEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee author = employeeService.getEmployeeByEmail(authorEmail);
        Document document = Document
                .builder()
                .uploadDate(new Date())
                .docName(fileName)
                .author(author)
//                .relatedTasks(relatedTasks)
                .path(downloadPath)
                .build();
        repository.save(document);
    }
    public ResponseEntity uploadDocument(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get("doc_uploads", fileName);
        //check if the directory "uploads" exists
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        //if exists write file into "uploads" directory
        System.out.println(path);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //save the new document data in database
        String downloadPath = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                        .path("/doc_uploads/")
                                .path(fileName)
                                        .toUriString(); //path to download the file
        saveDocument(fileName, downloadPath, null);


        return ResponseEntity.ok("File uploaded!");
    }

    public ResponseEntity uploadDocuments(MultipartFile[] files) {
        Arrays.asList(files)
                .stream()
                .forEach(file -> uploadDocument(file));
        return ResponseEntity.ok("Files uploaded!");
    }

    public DocumentDTO getDocumentByName(String name) {
        Optional<Document> queryResponse = repository.findByDocName(name);
        return queryResponse.isPresent() ? mapDocumentToDocumentDto(queryResponse.get()) : null;
    }

    public DocumentDTO getDocumentById(int id) {
        Optional<Document> queryResponse = repository.findById(id);
        return queryResponse.isPresent() ? mapDocumentToDocumentDto(queryResponse.get()) : null;
    }

    public List<DocumentDTO> getDocumentsByEmployeeId(int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        List<Document> queryResponse = repository.findByAuthor(employee);
        if (!queryResponse.isEmpty())
            return queryResponse.stream()
                    .map(document -> mapDocumentToDocumentDto(document))
                    .collect(Collectors.toList());
        return null;
    }

    public boolean deleteDocumentById(int id) {
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<DocumentDTO> getAllDocuments() {
        List<Document> queryResponse = repository.findAll();
        if (!queryResponse.isEmpty())
            return queryResponse.stream()
                    .map(document -> mapDocumentToDocumentDto(document))
                    .collect(Collectors.toList());
        return null;
    }

    public ResponseEntity downloadDocument(String fileName) {
        Resource resource =  new PathResource("doc_uploads/" + fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
