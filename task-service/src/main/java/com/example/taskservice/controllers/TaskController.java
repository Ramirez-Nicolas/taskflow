package com.example.taskservice.controllers;

import com.example.taskservice.entities.Task;
import com.example.taskservice.entities.TaskStatus;
import com.example.taskservice.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public String hello() {
        return "Task Service is running";
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task task) {
        return taskService.addTask(task);
    }

    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public Task getTask(@PathVariable int id) {
        return taskService.getTask(id);
    }

    @PutMapping("/tasks/{id}/status")
    public Task updateStatus(@PathVariable int id, @RequestParam String status) {
        return taskService.updateStatus(id, TaskStatus.valueOf(status));
    }

    @DeleteMapping("/tasks/{id}")
    public boolean deleteTask(@PathVariable int id) {
        return taskService.deleteTask(id);
    }
}
