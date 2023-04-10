package com.docmanager.docmanagerbackend.task;

import com.docmanager.docmanagerbackend.document.Document;
import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.taskupdate.TaskUpdate;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Employee author; //todo: this should be an object(Employee) same in employee -> field assigned for his tasks

    @ManyToOne
    @JoinColumn(name = "employee_assigned_id", referencedColumnName = "id", nullable = false)
    private Employee employeeAssigned;

    @OneToMany(mappedBy = "author")
    private List<TaskUpdate> taskUpdates;

    private Date postDate;
    private String title;
    private String description;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "task_doc_joined",
            joinColumns = @JoinColumn(name ="task_id"),
            inverseJoinColumns = @JoinColumn(name = "doc_id")
    )
    private Set<Document> relatedDocuments;
}
