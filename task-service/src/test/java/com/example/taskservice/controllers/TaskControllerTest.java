package com.example.taskservice.controllers;

import com.example.taskservice.entities.Task;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TaskControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testHello() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddTask() throws Exception {
        Task task = new Task(0, "New Task", "Description", 1);
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Task"))
                .andExpect(jsonPath("$.status").value("TODO"));
    }

    @Test
    public void testGetAllTasks() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTaskById() throws Exception {
        Task task = new Task(0, "My Task", "Desc", 1);
        MvcResult result = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andReturn();

        Task created = objectMapper.readValue(result.getResponse().getContentAsString(), Task.class);
        mockMvc.perform(get("/tasks/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("My Task"));
    }

    @Test
    public void testUpdateStatus() throws Exception {
        Task task = new Task(0, "Task", "Desc", 1);
        MvcResult result = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andReturn();

        Task created = objectMapper.readValue(result.getResponse().getContentAsString(), Task.class);
        mockMvc.perform(put("/tasks/" + created.getId() + "/status")
                .param("status", "IN_PROGRESS"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        Task task = new Task(0, "Task", "Desc", 1);
        MvcResult result = mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task)))
                .andReturn();

        Task created = objectMapper.readValue(result.getResponse().getContentAsString(), Task.class);
        mockMvc.perform(delete("/tasks/" + created.getId()))
                .andExpect(status().isOk());
    }
}
