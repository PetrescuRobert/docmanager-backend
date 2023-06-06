package com.docmanager.docmanagerbackend.task;

import com.docmanager.docmanagerbackend.department.Department;
import com.docmanager.docmanagerbackend.document.Document;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//this will be used as a composite key for the Task entity
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "document_id", referencedColumnName = "id", nullable = false)
    private Document document;

    @Column(name = "department_id")
    private Department department;
    private Date createdDate;
    //implement equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskId)) return false;
        TaskId taskId = (TaskId) o;
        return document.equals(taskId.document) && department.equals(taskId.department) && createdDate.equals(taskId.createdDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(document, department, createdDate);
    }
    @Override
    public String toString() {
        //the document field and the department fields i want to be printed as their ids
        return "TaskId{" +
                "document=" + document.getId() +
                ", department=" + department.getId() +
                ", createdDate=" + createdDate +
                '}';
    }
}
