package com.example.taskservice.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testTaskConstructor() {
        Task task = new Task(1, "Fix bug", "Fix the login bug", 42);
        assertEquals(1, task.getId());
        assertEquals("Fix bug", task.getTitle());
        assertEquals("Fix the login bug", task.getDescription());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertEquals(42, task.getUserId());
    }

    @Test
    public void testTaskDefaultStatus() {
        Task task = new Task(1, "My Task", "Description", 1);
        assertEquals(TaskStatus.TODO, task.getStatus());
    }

    @Test
    public void testTaskSetters() {
        Task task = new Task();
        task.setId(5);
        task.setTitle("New Task");
        task.setDescription("Some description");
        task.setStatus(TaskStatus.IN_PROGRESS);
        task.setUserId(10);

        assertEquals(5, task.getId());
        assertEquals("New Task", task.getTitle());
        assertEquals("Some description", task.getDescription());
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
        assertEquals(10, task.getUserId());
    }

    @Test
    public void testTaskStatusDone() {
        Task task = new Task(1, "Task", "Desc", 1);
        task.setStatus(TaskStatus.DONE);
        assertEquals(TaskStatus.DONE, task.getStatus());
    }
}
