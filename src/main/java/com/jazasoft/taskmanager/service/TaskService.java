package com.jazasoft.taskmanager.service;

import com.jazasoft.taskmanager.model.Task;
import com.jazasoft.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    /*Save Task*/
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    /*Search All Tasks*/

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    /*Get One Task*/
    public Task findOne(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    /*Delete Task*/
    public void delete(Task task) {
        taskRepository.delete(task);
    }
}
