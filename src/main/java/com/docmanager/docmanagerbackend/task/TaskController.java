package com.docmanager.docmanagerbackend.task;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/api/tasks/new-task")
    public ResponseEntity createNewTask(@RequestBody TaskDTO taskDTO) {
        System.out.println(taskDTO);
        TaskDTO createdTask = taskService.createNewTask(taskDTO);
       return new ResponseEntity(createdTask, HttpStatus.CREATED);
    }
    //method that return a list of all tasks assigned to an employee
    @GetMapping("/api/tasks/emp_id={id}")
    public ResponseEntity getAllTasksByEmployeeId(@PathVariable int id) {
        return taskService.getAllTasksByEmployeeId(id) != null ?
                ResponseEntity.ok(taskService.getAllTasksByEmployeeId(id)) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee does not have any tasks assigned");
    }

    @GetMapping("/api/tasks/{id}")
    public ResponseEntity getTaskById(@PathVariable int id) {
        TaskDTO queryResult = taskService.getTaskById(id);
        return queryResult != null ?
                ResponseEntity.status(HttpStatus.OK).body(queryResult) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task does not exist");
    }

    @PutMapping("/api/tasks")
    public ResponseEntity updateTask(@RequestBody TaskDTO taskDTO) {
        boolean updateTaskResponse = taskService.updateTask(taskDTO);
        return updateTaskResponse ?
                ResponseEntity.ok("Task was updated successfully!") :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task not found!");
    }

    @DeleteMapping("/api/tasks/delete/{id}")
    public ResponseEntity deleteTaskById(@PathVariable int id) {
        return taskService.deleteTaskById(id) ?
                ResponseEntity.status(HttpStatus.OK).body("Task with id: " + id + " was deleted") :
                ResponseEntity.status(HttpStatus.NO_CONTENT).body("Task with id: " + id + " does not exists");
    }
}
