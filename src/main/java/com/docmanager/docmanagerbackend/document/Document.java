package com.docmanager.docmanagerbackend.document;

import com.docmanager.docmanagerbackend.employee.Employee;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Document {
    private Integer id;
    private String name;
    private String path; //path to where the file is stored
    private Date uploadDate;
    private List<Employee> author; //one-to-many relationship one document -> many authors
    // todo finish this, read more about uploading files
}
