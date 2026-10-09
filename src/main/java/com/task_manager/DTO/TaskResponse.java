package com.task_manager.DTO;

import java.time.LocalDateTime;

import com.task_manager.Entity.Task;

public record TaskResponse(
    long Id,
    String title,
    String description,
    Task.Status status,
    Task.Priority priority,
    LocalDateTime dueDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
   public static TaskResponse fromEntity(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );

}}
