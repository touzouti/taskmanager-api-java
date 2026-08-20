package com.imene.taskmanager.task;

import com.imene.taskmanager.task.dto.CreateTaskRequest;
import com.imene.taskmanager.task.dto.UpdateTaskRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task create(CreateTaskRequest request) {
        Task task = new Task(
                request.title(),
                request.description()
        );

        return taskRepository.save(task);
    }

    public Task update(Long id, UpdateTaskRequest request) {
        Task task = findById(id);

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());

        return taskRepository.save(task);
    }

    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}