package com.example.taskservice.services;

import com.example.taskservice.entities.Task;
import com.example.taskservice.entities.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskServiceTest {

    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        taskService = new TaskService();
    }

    @Test
    public void testAddTask() {
        Task task = new Task(0, "Test Task", "Description", 1);
        Task result = taskService.addTask(task);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test Task", result.getTitle());
        assertEquals(TaskStatus.TODO, result.getStatus());
    }

    @Test
    public void testGetAllTasks() {
        taskService.addTask(new Task(0, "Task 1", "Desc 1", 1));
        taskService.addTask(new Task(0, "Task 2", "Desc 2", 2));
        assertEquals(2, taskService.getAllTasks().size());
    }

    @Test
    public void testGetAllTasksEmpty() {
        assertEquals(0, taskService.getAllTasks().size());
    }

    @Test
    public void testGetTask() {
        taskService.addTask(new Task(0, "Task", "Desc", 1));
        Task result = taskService.getTask(1);
        assertNotNull(result);
        assertEquals("Task", result.getTitle());
    }

    @Test
    public void testGetTaskNotFound() {
        assertNull(taskService.getTask(999));
    }

    @Test
    public void testUpdateStatus() {
        taskService.addTask(new Task(0, "Task", "Desc", 1));
        Task result = taskService.updateStatus(1, TaskStatus.IN_PROGRESS);
        assertNotNull(result);
        assertEquals(TaskStatus.IN_PROGRESS, result.getStatus());
    }

    @Test
    public void testUpdateStatusNotFound() {
        Task result = taskService.updateStatus(999, TaskStatus.DONE);
        assertNull(result);
    }

    @Test
    public void testDeleteTask() {
        taskService.addTask(new Task(0, "Task", "Desc", 1));
        assertTrue(taskService.deleteTask(1));
        assertNull(taskService.getTask(1));
    }

    @Test
    public void testDeleteTaskNotFound() {
        assertFalse(taskService.deleteTask(999));
    }
}
