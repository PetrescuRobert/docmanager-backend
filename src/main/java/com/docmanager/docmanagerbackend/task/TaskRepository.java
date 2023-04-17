package com.docmanager.docmanagerbackend.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<Task> findByTitle(String title);
    List<Task> findByPostDate(Date postDate);
}
