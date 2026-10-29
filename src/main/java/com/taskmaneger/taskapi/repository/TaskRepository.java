package com.taskmaneger.taskapi.repository;

import com.taskmaneger.taskapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
