package com.imene.taskmanager.error;

import com.imene.taskmanager.task.TaskNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiError> handleTaskNotFound(
            TaskNotFoundException exception
    ) {
        ApiError error = new ApiError(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Tâche introuvable",
                Map.of("message", exception.getMessage())
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> details = new LinkedHashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError ->
                        details.put(
                                fieldError.getField(),
                                fieldError.getDefaultMessage()
                        )
                );

        ApiError error = new ApiError(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Données invalides",
                details
        );

        return ResponseEntity.badRequest().body(error);
    }
}