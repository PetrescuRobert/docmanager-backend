package com.docmanager.docmanagerbackend.taskupdate;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskUpdateService {
    private final TaskUpdateRepository repository;

    public boolean deleteTaskUpdateById(int id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
