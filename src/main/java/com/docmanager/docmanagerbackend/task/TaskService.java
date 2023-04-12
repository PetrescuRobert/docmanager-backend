package com.docmanager.docmanagerbackend.task;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    public final TaskRepository repository;

    private TaskDTO mapEntitytoDto(Task task) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(task, TaskDTO.class);
    }

    private List<TaskDTO> mapEntitiesToDtos(List<Task> tasks) {
        return tasks
                .stream()
                .map(task -> mapEntitytoDto(task))
                .collect(Collectors.toList());
    }


}
