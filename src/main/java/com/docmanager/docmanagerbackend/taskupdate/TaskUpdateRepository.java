package com.docmanager.docmanagerbackend.taskupdate;

import com.docmanager.docmanagerbackend.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskUpdateRepository extends JpaRepository<TaskUpdate, Integer> {
    List<TaskUpdate> findByTask(Task task);
}
