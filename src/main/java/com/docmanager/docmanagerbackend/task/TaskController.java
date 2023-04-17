package com.docmanager.docmanagerbackend.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/api/task/newtask")
    public ResponseEntity createNewTask(@RequestBody TaskDTO taskDTO) {
        TaskDTO createdTask = taskService.createNewTask(taskDTO);
        return new ResponseEntity(createdTask, HttpStatus.CREATED);
    }

    @GetMapping("/api/task/{id}")
    public ResponseEntity getTaskById(@PathVariable int id) {
        TaskDTO queryResult = taskService.getTaskById(id);
        return queryResult != null ?
                ResponseEntity.status(HttpStatus.FOUND).body(queryResult) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task does not exist");
    }
}
