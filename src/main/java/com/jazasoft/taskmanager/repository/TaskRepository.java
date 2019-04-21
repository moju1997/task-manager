package com.jazasoft.taskmanager.repository;


import com.jazasoft.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
