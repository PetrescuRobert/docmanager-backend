package com.docmanager.docmanagerbackend.document;

import com.docmanager.docmanagerbackend.attachedDocument.AttachedDocument;
import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Documents")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer docId;
    private String docName;
    private String path;
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "employeeId", nullable = false)
    private Employee author;
    @ManyToMany(mappedBy = "relatedDocuments")
    private Set<Task> relatedTasks;
    @OneToMany(mappedBy = "parentDocument")
    private List<AttachedDocument> attachedDocuments;
}
