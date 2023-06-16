package com.docmanager.docmanagerbackend.department;

import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer depId;
    private String name;
    @OneToOne
    private Employee manager;
    @OneToMany(mappedBy = "department")
    private List<Employee> employees;
    @OneToMany(mappedBy = "currentDepartment")
    private List<Task> tasks;

}
