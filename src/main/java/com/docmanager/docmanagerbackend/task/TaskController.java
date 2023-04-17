package com.docmanager.docmanagerbackend.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/api/tasks/new-task")
    public ResponseEntity createNewTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createNewTask(taskDTO);
        return new ResponseEntity(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/api/tasks/{id}")
    public ResponseEntity getTaskById(@PathVariable int id) {
        TaskDTO queryResult = taskService.getTaskById(id);
        return queryResult != null ?
                ResponseEntity.status(HttpStatus.FOUND).body(queryResult) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task does not exist");
    }

    @PutMapping("/api/tasks/")
    public ResponseEntity updateTask(@RequestBody TaskDTO taskDTO) {
        boolean updateTaskResponse = taskService.updateTask(taskDTO);
        return updateTaskResponse ?
                ResponseEntity.ok("Task was updated successfully!") :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task not found!");
    }

    @DeleteMapping("/api/tasks/{id}")
    public ResponseEntity deleteTaskById(@PathVariable int id) {
        return taskService.deleteTaskById(id) ?
                ResponseEntity.status(HttpStatus.OK).body("Task with id: " + id + " was deleted") :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task with id: " + id + " does not exists");
    }
}
