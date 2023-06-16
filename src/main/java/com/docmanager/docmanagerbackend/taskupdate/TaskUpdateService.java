package com.docmanager.docmanagerbackend.taskupdate;

import com.docmanager.docmanagerbackend.attachedDocument.AttachedDocumentService;
import com.docmanager.docmanagerbackend.document.Document;
import com.docmanager.docmanagerbackend.employee.EmployeeRepository;
import com.docmanager.docmanagerbackend.task.Task;
import com.docmanager.docmanagerbackend.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskUpdateService {
    private final TaskUpdateRepository repository;
    private final TaskRepository taskRepository;
    private final AttachedDocumentService attachedDocumentService;
    private final EmployeeRepository employeeRepository;

    private TaskUpdateDto mapEntityToDto(TaskUpdate taskUpdate) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(taskUpdate, TaskUpdateDto.class);
    }
    public ResponseEntity createTaskUpdate(int taskId, MultipartFile file, String message) {
        TaskUpdate taskUpdate = new TaskUpdate();
        Task task = taskRepository.findById(taskId).get();
        List<Document> documents = task.getRelatedDocuments();
//        System.out.println(employee.get);
        if (task == null)
            return ResponseEntity.badRequest().body("Task not found");
        taskUpdate.setTask(task);
        taskUpdate.setMessage(message);
        taskUpdate.setPostDate(new java.util.Date());
        attachedDocumentService.saveAttachedDocuments(file, taskUpdate, documents.get(0));
//        repository.save(taskUpdate);
        return ResponseEntity.ok().body("Task update created successfully");
    }

    public ResponseEntity getTaskUpdateByTaskId(int taskId) {
        Task task = taskRepository.findById(taskId).get();
        if (task == null)
            return ResponseEntity.badRequest().body("Task not found");
        List<TaskUpdate> taskUpdates = repository.findByTask(task);
        //map the task updates to task update dtos
        List<TaskUpdateDto> taskUpdateDtos = new ArrayList<>();
        for(TaskUpdate taskUpdate: taskUpdates) {
            //i will create a task deto instance and set the values of the task update dto
            //for the attached document i will create an attached document dto instance and set the values of the attached document dto
            //then i will set the attached document dto to the task update dto
            TaskUpdateDto taskUpdateDto = new TaskUpdateDto(
                    taskUpdate.getTaskUpdateId(),
                    taskUpdate.getMessage(),
                    taskUpdate.getPostDate(),
                    taskUpdate.getAttachedDocuments().isEmpty() ? null : attachedDocumentService.mapEntityToDto(taskUpdate.getAttachedDocuments().get(0))
            );
            taskUpdateDtos.add(taskUpdateDto);
        }
        return ResponseEntity.ok().body(taskUpdateDtos);
    }

    public void deleteTaskUpdate(TaskUpdate taskUpdate) {
        repository.delete(taskUpdate);
    }
}
