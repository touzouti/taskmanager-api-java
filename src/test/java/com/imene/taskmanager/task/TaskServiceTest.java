package com.imene.taskmanager.task;

import com.imene.taskmanager.task.dto.CreateTaskRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void shouldCreateTask() {
        CreateTaskRequest request = new CreateTaskRequest(
                "Apprendre Spring Boot",
                "Construire une API professionnelle"
        );

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskService.create(request);

        assertEquals("Apprendre Spring Boot", result.getTitle());
        assertEquals(
                "Construire une API professionnelle",
                result.getDescription()
        );
        assertEquals(TaskStatus.PENDING, result.getStatus());
    }

    @Test
    void shouldThrowExceptionWhenTaskDoesNotExist() {
        when(taskRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                TaskNotFoundException.class,
                () -> taskService.findById(99L)
        );
    }
}
