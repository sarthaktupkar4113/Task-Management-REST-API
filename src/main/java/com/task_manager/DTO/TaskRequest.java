package com.task_manager.DTO;

import java.time.LocalDateTime;

import com.task_manager.Entity.Task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequest {

    @NotBlank(message = "Title is reuired ")
    @Size (max = 150, message = "Title cannot exceed 150 charaters")
    private String title;

    @NotBlank 
    @Size (max = 2000 , message = "cannot exceed 2000 words")
    private String description;

    private Task.Status status;

    private Task.Priority priority;

    private LocalDateTime dueDate;

        public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Task.Status getStatus() { return status; }
    public void setStatus(Task.Status status) { this.status = status; }

    public Task.Priority getPriority() { return priority; }
    public void setPriority(Task.Priority priority) { this.priority = priority; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate;
    }

}
