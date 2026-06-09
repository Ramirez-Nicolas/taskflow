package com.example.taskservice.services;

import com.example.taskservice.entities.Task;
import com.example.taskservice.entities.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskService {

    private final Map<Integer, Task> tasks = new HashMap<>();
    private int nextId = 1;

    public Task addTask(Task task) {
        task.setId(nextId++);
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }
        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Task updateStatus(int id, TaskStatus status) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setStatus(status);
        }
        return task;
    }

    public boolean deleteTask(int id) {
        return tasks.remove(id) != null;
    }
}
