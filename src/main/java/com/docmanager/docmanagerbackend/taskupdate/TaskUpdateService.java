package com.docmanager.docmanagerbackend.taskupdate;

import com.docmanager.docmanagerbackend.task.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskUpdateService {
    private final TaskUpdateRepository repository;
    private final TaskRepository taskRepository;

    public boolean deleteTaskUpdateById(int id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean createTaskUpdate(int taskId, TaskUpdateDTO taskUpdateDTO) {
        if (taskRepository.findById(taskId).isPresent()) {
            ModelMapper modelMapper = new ModelMapper();
            repository.save(modelMapper.map(taskUpdateDTO, TaskUpdate.class));
            System.out.println(repository.findById(1));
            return true;
        }
        return false;
    }
}
