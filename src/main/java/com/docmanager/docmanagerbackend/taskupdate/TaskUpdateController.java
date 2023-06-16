package com.docmanager.docmanagerbackend.taskupdate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class TaskUpdateController {
    private final TaskUpdateService taskUpdateService;
    //method that will create a task update and save it to the database
    //the request will have an attached document in the body that i will store it in the attached document table
    @PostMapping("/api/taskupdate/{taskId}")
    public ResponseEntity createTaskUpdate(@PathVariable int taskId, @RequestParam MultipartFile file, @RequestParam String message) {
        ResponseEntity response = taskUpdateService.createTaskUpdate(taskId, file, message);
        return response;
    }
    @GetMapping("/api/taskupdate/{taskId}")
    public ResponseEntity getTaskUpdateByTaskId(@PathVariable int taskId) {
        ResponseEntity response = taskUpdateService.getTaskUpdateByTaskId(taskId);
        return response;
    }
}
