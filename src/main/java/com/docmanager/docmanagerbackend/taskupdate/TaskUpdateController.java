package com.docmanager.docmanagerbackend.taskupdate;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TaskUpdateController {
    private final TaskUpdateService taskUpdateService;

    @PostMapping("/api/task-updates/task={taskId}")
    public ResponseEntity createTaskUpdate(@PathVariable int taskId, @RequestBody TaskUpdateDTO taskUpdateDTO) {
        return taskUpdateService.createTaskUpdate(taskId, taskUpdateDTO) ?
                ResponseEntity.ok("Task update created") :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");

    }

    @DeleteMapping("/api/task-updates/{id}")
    public ResponseEntity deleteTaskUpdateById(@PathVariable int id) {
        return taskUpdateService.deleteTaskUpdateById(id) ?
                ResponseEntity.ok().body("TaskUpdate Deleted") :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("No task update with id: " + id);
    }
}
