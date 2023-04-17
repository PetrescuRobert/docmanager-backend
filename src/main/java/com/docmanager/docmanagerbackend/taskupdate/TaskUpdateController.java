package com.docmanager.docmanagerbackend.taskupdate;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskUpdateController {
    private final TaskUpdateService taskUpdateService;

    @DeleteMapping("/api/task-updates/{id}")
    public ResponseEntity deleteTaskUpdateById(@PathVariable int id) {
        return taskUpdateService.deleteTaskUpdateById(id) ?
                ResponseEntity.ok().body("TaskUpdate Deleted") :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("No task update with id: " + id);
    }
}
