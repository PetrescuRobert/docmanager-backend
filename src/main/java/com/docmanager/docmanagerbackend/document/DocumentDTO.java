package com.docmanager.docmanagerbackend.document;

import com.docmanager.docmanagerbackend.employee.EmployeeDTO;
import lombok.Data;

@Data
public class DocumentDTO {
    private Integer id;
    private String name;
    private EmployeeDTO author;
    private String path;
}
