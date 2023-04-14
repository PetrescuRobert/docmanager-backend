package com.docmanager.docmanagerbackend.task;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    public final TaskRepository repository;


    private TaskDTO mapTaskToTaskDto(Task task) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(task, TaskDTO.class);
    }

    public void saveTask(Task task) {
        task.setPostDate(new Date());
        repository.save(task);
    }

    public TaskDTO createNewTask(TaskDTO taskDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Task task = modelMapper.map(taskDTO, Task.class);
        saveTask(task);

        System.out.println(taskDTO);
        System.out.println(task);
        return null;
    }

    public void getTaskById(int id) {
        Optional<Task> queryResult = repository.findById(id);
        System.out.println(repository.findById(id));
    }
}
