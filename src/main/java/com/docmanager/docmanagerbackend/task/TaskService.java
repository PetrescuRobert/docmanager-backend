package com.docmanager.docmanagerbackend.task;

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
}
