package com.docmanager.docmanagerbackend.taskupdate;

import com.docmanager.docmanagerbackend.attachedDocument.AttachedDocument;
import com.docmanager.docmanagerbackend.attachedDocument.AttachedDocumentService;
import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.employee.EmployeeRepository;
import com.docmanager.docmanagerbackend.task.Task;
import com.docmanager.docmanagerbackend.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskUpdateService {
    private final TaskUpdateRepository repository;
    private final TaskRepository taskRepository;
    private final AttachedDocumentService attachedDocumentService;
    private final EmployeeRepository employeeRepository;
    public ResponseEntity createTaskUpdate(int taskId, MultipartFile file, String message) {
        System.out.println("Task id: " + taskId);
        System.out.println("Message: " + message);
        TaskUpdate taskUpdate = new TaskUpdate();
        Task task = taskRepository.findById(taskId).get();
//        System.out.println(employee.get);
        if (task == null)
            return ResponseEntity.badRequest().body("Task not found");
        taskUpdate.setTask(task);
        taskUpdate.setMessage(message);
        taskUpdate.setPostDate(new java.util.Date());
        attachedDocumentService.saveAttachedDocuments(file, taskUpdate);
//        repository.save(taskUpdate);
        return ResponseEntity.ok().body("Task update created successfully");
    }
}
