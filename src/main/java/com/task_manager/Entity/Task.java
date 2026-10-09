package com.task_manager.Entity;

import jakarta.persistence.*;

 
import java.time.LocalDateTime;

 


@Entity 
@Table (name ="tasks")
public class Task {
    
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id 
    private long id;

    @Column (nullable = false)
    private String title;

    @Column (length = 2000)
    private String description;
 
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.TODO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority = Priority.MEDIUM;

    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public enum Status {
        TODO, IN_PROGRESS, DONE
    }

    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}



