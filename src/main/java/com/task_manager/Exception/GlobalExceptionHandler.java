package com.task_manager.Exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException exception) {

        return buildResponse(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
            MethodArgumentNotValidException exception) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Validation failed");

        Map<String, String> errors = new LinkedHashMap<>();

        exception.getBindingResult().getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(), error.getDefaultMessage()));

        response.put("fields", errors);
        return ResponseEntity.badRequest().body(response);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(
            HttpStatus status, String message) {

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", status.value());
        response.put("error", message);

        return ResponseEntity.status(status).body(response);
    }
}
