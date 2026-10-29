package com.taskmaneger.taskapi.service;

import com.taskmaneger.taskapi.dto.CreateTaskRequest;
import com.taskmaneger.taskapi.dto.TaskResponse;
import com.taskmaneger.taskapi.dto.UpdateTaskRequest;

import java.util.List;

public interface TaskService {


        TaskResponse createTask(CreateTaskRequest request);

        List<TaskResponse> getAllTasks();

        TaskResponse getTaskById(Long id);

        TaskResponse updateTask(Long id, UpdateTaskRequest request);

        void deleteTask(Long id);
    }

