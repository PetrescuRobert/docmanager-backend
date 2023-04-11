package com.docmanager.docmanagerbackend.taskupdate;

import com.docmanager.docmanagerbackend.employee.EmployeeDTO;
import com.docmanager.docmanagerbackend.task.TaskDTO;
import lombok.Data;

@Data
public class TaskUpdateDTO {
    private Integer id;
    private EmployeeDTO author;
    private TaskDTO task;
}
