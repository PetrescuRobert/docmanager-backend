package com.docmanager.docmanagerbackend.attachedDocument;

import com.docmanager.docmanagerbackend.document.Document;
import com.docmanager.docmanagerbackend.taskupdate.TaskUpdate;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "AttachedDocuments")
public class AttachedDocument {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer attachedDocumentId;
    private String docName;
    private String path;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="taskupdate_id", referencedColumnName = "taskUpdateId")
    private TaskUpdate taskUpdate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="document_id", referencedColumnName = "docId")
    private Document parentDocument;
    @Override
    public String toString() {
        return "AttachedDocument{" +
                "attachedDocumentId=" + attachedDocumentId +
                ", docName='" + docName + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
