package com.docmanager.docmanagerbackend.task;

import com.docmanager.docmanagerbackend.document.DocumentDTO;
import com.docmanager.docmanagerbackend.employee.EmployeeDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskDTO {
    private Integer id;
    private EmployeeDTO author;
    private EmployeeDTO employeeAssigned;
    private Date postDate;
    private String title;
    private String description;
    private List<DocumentDTO> relatedDocuments;
}
