package com.docmanager.docmanagerbackend.task;

import com.docmanager.docmanagerbackend.department.Department;
import com.docmanager.docmanagerbackend.document.Document;
import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.taskupdate.TaskUpdate;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskId;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "employeeId")
    private Employee author;

    @ManyToOne
    @JoinColumn(name = "employee_assigned_id", referencedColumnName = "employeeId")
    private Employee employeeAssigned;

    private Date postDate;
    private String title;
    private String description;
    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(name = "task_doc_joined",
            joinColumns = @JoinColumn(name ="task_id"),
            inverseJoinColumns = @JoinColumn(name = "doc_id")
    )
    private List<Document> relatedDocuments;

    @OneToMany(mappedBy = "task")
    private List<TaskUpdate> taskUpdates;
    @ManyToOne
    @JoinColumn(name = "current_department_id", referencedColumnName = "depId")
    private Department currentDepartment;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + taskId +
                ", author={id:" + author.getEmployeeId() + ", firstName: " + author.getFirstName() + ", lastName: " + author.getLastName() + "}"  +
                ", employeeAssigned={id" + employeeAssigned.getEmployeeId() + ", firstName: " + employeeAssigned.getFirstName() + ", lastName: " + employeeAssigned.getLastName() + "}"  +
//                ", taskUpdates=" + taskUpdates +
                ", postDate=" + postDate +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", relatedDocuments=" + relatedDocuments +
                '}';
    }
}
