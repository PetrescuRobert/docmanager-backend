package com.docmanager.docmanagerbackend.taskupdate;

import com.docmanager.docmanagerbackend.attachedDocument.AttachedDocument;
import com.docmanager.docmanagerbackend.document.Document;
import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "TaskUpdates")
public class TaskUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskUpdateId;
    private String message;
    private Date postDate;
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
    @OneToMany(mappedBy = "taskUpdate")
    private List<AttachedDocument> attachedDocuments;
}
