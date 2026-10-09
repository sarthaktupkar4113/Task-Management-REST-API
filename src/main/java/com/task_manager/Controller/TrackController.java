package com.task_manager.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_manager.DTO.TaskRequest;
import com.task_manager.DTO.TaskResponse;
import com.task_manager.Entity.Task;
import com.task_manager.Service.Taskservice;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/tasks")
public class TrackController {

    private final Taskservice taskService;

    public TrackController (Taskservice taskservice){ 
        this.taskService = taskservice;
    }


    @PostMapping 
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody  TaskRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(request));
    }

    @GetMapping("/{id}") 
    public ResponseEntity<TaskResponse> getTaskbyId(@PathVariable Long id ){
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @GetMapping 
    public  ResponseEntity<List<TaskResponse>> getAllTask(){
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<TaskResponse> updateTask( @PathVariable  Long id ,@Valid @RequestBody TaskRequest task){
    // }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {

        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return new  ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping ("/status/{status}")
    public ResponseEntity<List<TaskResponse>> getTaskByStatus(@PathVariable Task.Status status){

        return ResponseEntity.ok(taskService.getTasksByStatus(status));
    }
}