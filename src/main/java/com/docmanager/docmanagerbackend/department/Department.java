package com.docmanager.docmanagerbackend.department;

import com.docmanager.docmanagerbackend.employee.Employee;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    private Integer id;
    private String name;
    private List<Employee> employees; //todo: one-to many relationship with foreign key must have the anotation @ManyToOne in the entity that have many
}
