package com.docmanager.docmanagerbackend.document;

import com.docmanager.docmanagerbackend.employee.EmployeeDTO;
import lombok.Data;

import java.util.Date;

@Data
public class DocumentDTO {
    private Integer id;
    private String docName;
    private EmployeeDTO author;
    private String path;
    private Date uploadDate;
    private Date finishDate;

}
