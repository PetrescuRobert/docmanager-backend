package com.docmanager.docmanagerbackend.task;

import com.docmanager.docmanagerbackend.department.DepartmentService;
import com.docmanager.docmanagerbackend.document.DocumentService;
import com.docmanager.docmanagerbackend.employee.Employee;
import com.docmanager.docmanagerbackend.employee.EmployeeService;
import com.docmanager.docmanagerbackend.taskupdate.TaskUpdate;
import com.docmanager.docmanagerbackend.taskupdate.TaskUpdateService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    public final TaskRepository repository;
    public final EmployeeService employeeService;
    public final DocumentService documentService;
    public final DepartmentService departmentService;
    public final TaskUpdateService taskUpdateService;

    private TaskDTO mapTaskToTaskDto(Task task) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(task, TaskDTO.class);
    }

    public void saveTask(Task task) {
        task.setPostDate(new Date());
        repository.save(task);
    }

    public TaskDTO createNewTask(TaskDTO taskDTO) {
        Employee employeeAssignedTo = employeeService.getEmployeeById(taskDTO.getEmployeeAssigned().getId());
        ModelMapper modelMapper = new ModelMapper();
        Task task = modelMapper.map(taskDTO, Task.class);
        task.setEmployeeAssigned(employeeAssignedTo);
        task.setCurrentDepartment(departmentService.getDepartmentById(task.getEmployeeAssigned().getDepartment().getDepId()));
        saveTask(task);
        return null;
    }

    public TaskDTO getTaskById(int id) {
        Optional<Task> queryResult = repository.findById(id);
        if (!queryResult.isPresent())
            return null;
        return mapTaskToTaskDto(queryResult.get());
    }

    public TaskDTO getTaskByTitle(String title) {
        Optional<Task> queryResult = repository.findByTitle(title);
        return mapTaskToTaskDto(queryResult.get());
    }

    public List<TaskDTO> getTasksByPostDate(Date postDate) {
        List<Task> queryResult = repository.findByPostDate(postDate);
        return queryResult.stream()
                .map(task -> mapTaskToTaskDto(task))
                .collect(Collectors.toList());
    }

    public boolean deleteTaskById(int id) {

        if (repository.findById(id).isPresent()) {
            Task task = repository.findById(id).get();
            List<TaskUpdate> taskUpdates = task.getTaskUpdates();
            //delete task updates
            for (TaskUpdate taskUpdate : taskUpdates) {
                    taskUpdateService.deleteTaskUpdate(taskUpdate);
            }
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateTask(TaskDTO taskDTO) {
        try {
            Task task = repository.findById(taskDTO.getId()).get();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            repository.save(task);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public List<TaskDTO> getAllTasksByEmployeeId(int id) {
        Employee employee = employeeService.getEmployeeById(id);
        List<Task> queryResult = repository.findByEmployeeAssigned(employee);
        List<Task> authorTasks = repository.findByAuthor(employee);
        queryResult.addAll(authorTasks);
        List<TaskDTO> tasks = queryResult.stream()
                .map(task -> mapTaskToTaskDto(task))
                .collect(Collectors.toList());
        return tasks;
    }

    public boolean changeTaskDepartment(int depId, int taskId) {
        Task currentTask = repository.findById(taskId).get();
        currentTask.setCurrentDepartment(departmentService.getDepartmentById(depId));
        //set employee assigned to department manager
        currentTask.setEmployeeAssigned(currentTask.getCurrentDepartment().getManager());
        repository.save(currentTask);
        return true;
    }

    public boolean assignEmployeeToTask(int empId, int taskId) {
        Task currentTask = repository.findById(taskId).get();
        currentTask.setEmployeeAssigned(employeeService.getEmployeeById(empId));
        repository.save(currentTask);
        return true;
    }
}
