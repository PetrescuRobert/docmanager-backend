package com.docmanager.docmanagerbackend.task;

import com.docmanager.docmanagerbackend.document.Document;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {

    private Integer id;
    private Integer authorId;
    private Integer employeeAssignedId;
    private Date postDate;
    private String title;
    private String description;
    private List<Document> relatedDocuments; //todo: need to add many-to-many relationship
}
