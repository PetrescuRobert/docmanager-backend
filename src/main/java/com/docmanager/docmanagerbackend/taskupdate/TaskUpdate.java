package com.docmanager.docmanagerbackend.taskupdate;

import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.task.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TaskUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Employee author;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;
    private String description;

    @Override
    public String toString() {
        return "TaskUpdate{" +
                "id=" + id +
                ", author=" + author.getId() +
                ", task=" + task.getId() +
                ", description='" + description + '\'' +
                '}';
    }
}
