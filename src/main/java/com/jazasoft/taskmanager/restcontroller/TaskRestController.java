package com.jazasoft.taskmanager.restcontroller;

import com.jazasoft.taskmanager.model.Label;
import com.jazasoft.taskmanager.model.Task;
import com.jazasoft.taskmanager.service.TaskService;
import com.jazasoft.taskmanager.utils.ApiUrls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiUrls.ROOT_URL_TASKS)
public class TaskRestController {

    @Autowired
    TaskService taskService;

    /*Save Task*/
    /*Save Label*/
    @PostMapping
    public ResponseEntity<?> saveTask(@Valid @RequestBody Task task) {
        task=taskService.save(task);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
        return ResponseEntity.created(location).body(task);
    }

    /*Get All Tasks*/
    @GetMapping
    public List<Task> getAll() {
        return taskService.findAll();
    }

    /*Get Task by Id*/
    @GetMapping(ApiUrls.URL_TASKS_TASK)
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "taskId") Long id) {
        Task task = taskService.findOne(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(task);
    }

    /*Updating Task*/
    @PutMapping(ApiUrls.URL_TASKS_TASK)
    public ResponseEntity<Task> updateTask(@PathVariable(value = "taskId") Long id, @Valid @RequestBody Task taskDetail) {
        Task task = taskService.findOne(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        task.setName(taskDetail.getName());
        Task updateTask = taskService.save(task);
        return ResponseEntity.ok().body(task);
    }

    /*Deleting Tasks*/
    @DeleteMapping(ApiUrls.URL_TASKS_TASK)
    public ResponseEntity<Task> deleteTask(@PathVariable(value = "taskId") Long id) {
        Task task = taskService.findOne(id);
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        taskService.delete(task);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
