package com.docmanager.docmanagerbackend.document;

import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.employee.EmployeeService;
import com.docmanager.docmanagerbackend.task.Task;
import lombok.RequiredArgsConstructor;
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
import java.util.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository repository;
    private final EmployeeService employeeService;

    public ResponseEntity uploadDocument(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path path = Paths.get("doc_uploads", fileName);
        //check if the directory "uploads" exists
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException exception) {
                throw new RuntimeException("Can't initialize folder for upload");
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

    private void saveDocument(String fileName, String downloadPath, Set<Task> relatedTasks) {
        String authorEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Employee author = employeeService.getEmployeeByEmail(authorEmail);
        Document document = Document
                .builder()
                .uploadDate(new Date())
                .name(fileName)
                .author(author)
                .relatedTasks(relatedTasks)
                .path(downloadPath)
                .build();
        repository.save(document);
    }
}
