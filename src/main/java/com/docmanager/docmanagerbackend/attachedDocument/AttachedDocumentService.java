package com.docmanager.docmanagerbackend.attachedDocument;

import com.docmanager.docmanagerbackend.document.Document;
import com.docmanager.docmanagerbackend.taskupdate.TaskUpdate;
import com.docmanager.docmanagerbackend.taskupdate.TaskUpdateDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttachedDocumentService {
    private final AttachedDocumentRepository repository;

    public AttachedDocumentDto mapEntityToDto(AttachedDocument attachedDocument) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(attachedDocument, AttachedDocumentDto.class);
    }

    public void saveAttachedDocument(AttachedDocument attachedDocument) {
        repository.save(attachedDocument);
    }

    //method that will upload the attached document and store it in the database
    public String uploadAttachedDocument(MultipartFile file) {
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
        return downloadPath;
    }

    public List<String> uploadAttachedDocuments(MultipartFile[] files) {
        List<String> downloadPaths = null;
        for (MultipartFile file : files) {
            downloadPaths.add(uploadAttachedDocument(file));
        }
        return downloadPaths;
    }

    public void saveAttachedDocuments(MultipartFile file, TaskUpdate taskUpdate, Document parentDocument) {
        String downloadPath = uploadAttachedDocument(file);
        AttachedDocument attachedDocument = new AttachedDocument();
        attachedDocument.setPath(downloadPath);
        attachedDocument.setTaskUpdate(taskUpdate);
        attachedDocument.setDocName(file.getOriginalFilename());
        //set the parent documents
        attachedDocument.setParentDocument(parentDocument);
        saveAttachedDocument(attachedDocument);
    }
}
