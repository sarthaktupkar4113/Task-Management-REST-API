package com.task_manager.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task_manager.DTO.TaskRequest;
import com.task_manager.DTO.TaskResponse;
import com.task_manager.Entity.Task;
import com.task_manager.Repository.TaskRepository;
import com.task_manager.Exception.ResourceNotFoundException;

@Service 
public class Taskservice {

    private final TaskRepository taskRepository;

    public Taskservice(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse createTask(TaskRequest request) {
        Task task = new Task();
        copyRequestToEntity(request, task);
        return TaskResponse.fromEntity(taskRepository.save(task));
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(TaskResponse::fromEntity)
                .toList();
    }

    public TaskResponse getTaskById(Long id) {
        Task task = findTask(id);
        return TaskResponse.fromEntity(task);
    }

    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = findTask(id);
        copyRequestToEntity(request, task);
        return TaskResponse.fromEntity(taskRepository.save(task));
    }

    public void deleteTask(Long id) {
        Task task = findTask(id);
        taskRepository.delete(task);
    }

    public List<TaskResponse> getTasksByStatus(Task.Status status) {
        return taskRepository.findByStatus(status)
                .stream()
                .map(TaskResponse::fromEntity)
                .toList();
    }

    private Task findTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Task not found with id: " + id));
    }

    private void copyRequestToEntity(TaskRequest request, Task task) {
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
        }

        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }

        task.setDueDate(request.getDueDate());
    }
}
