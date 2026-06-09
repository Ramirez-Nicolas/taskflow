package com.example.taskservice.entities;

public class Task {

    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private int userId;

    public Task() {}

    public Task(int id, String title, String description, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.userId = userId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
}
