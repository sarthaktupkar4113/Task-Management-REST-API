package com.task_manager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_manager.Entity.Task;

public interface TaskRepository extends JpaRepository<Task,Long>{

    List<Task> findByStatus(Task.Status status);
    List<Task> findByPriority(Task.Priority priority);
    List<Task> findByTitleContainingIgnoreCase(String title);
    
}