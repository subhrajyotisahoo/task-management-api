package com.taskmaneger.taskapi.service;
import com.taskmaneger.taskapi.dto.CreateTaskRequest;
import com.taskmaneger.taskapi.dto.TaskResponse;
import com.taskmaneger.taskapi.dto.UpdateTaskRequest;
import com.taskmaneger.taskapi.entity.Task;
import com.taskmaneger.taskapi.mapper.TaskMapper;
import com.taskmaneger.taskapi.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.taskmaneger.taskapi.exception.TaskNotFoundException;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskServiceImpl(
            TaskRepository taskRepository,
            TaskMapper taskMapper
    ) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskResponse createTask(CreateTaskRequest request) {
        Task task = taskMapper.toEntity(request);

        Task savedTask = taskRepository.save(task);

        return taskMapper.toResponse(savedTask);
    }


    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toResponse)
                .toList();
    }



    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id)
                );

        return taskMapper.toResponse(task);
    }


    @Override
    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id)
                );

        taskMapper.updateEntity(existingTask, request);

        Task updatedTask = taskRepository.save(existingTask);

        return taskMapper.toResponse(updatedTask);
    }



    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException("Task not found with id: " + id)
                );

        taskRepository.delete(task);
    }

}
