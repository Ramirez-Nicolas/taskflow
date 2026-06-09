package com.example.userservice.services;

import com.example.userservice.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;

    @BeforeEach
    public void setUp() {
        userService = new UserService();
    }

    @Test
    public void testAddUser() {
        User user = new User(0, "Alice", "alice@example.com");
        User result = userService.addUser(user);
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Alice", result.getName());
        assertEquals("alice@example.com", result.getEmail());
    }

    @Test
    public void testGetAllUsers() {
        userService.addUser(new User(0, "Alice", "alice@example.com"));
        userService.addUser(new User(0, "Bob", "bob@example.com"));
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    public void testGetAllUsersEmpty() {
        assertEquals(0, userService.getAllUsers().size());
    }

    @Test
    public void testGetUser() {
        userService.addUser(new User(0, "Alice", "alice@example.com"));
        User result = userService.getUser(1);
        assertNotNull(result);
        assertEquals("Alice", result.getName());
    }

    @Test
    public void testGetUserNotFound() {
        assertNull(userService.getUser(999));
    }

    @Test
    public void testUpdateUser() {
        userService.addUser(new User(0, "Alice", "alice@example.com"));
        User updated = new User(0, "Alice Updated", "alice2@example.com");
        User result = userService.updateUser(1, updated);
        assertNotNull(result);
        assertEquals("Alice Updated", result.getName());
        assertEquals("alice2@example.com", result.getEmail());
    }

    @Test
    public void testUpdateUserNotFound() {
        User updated = new User(0, "Ghost", "ghost@example.com");
        assertNull(userService.updateUser(999, updated));
    }

    @Test
    public void testDeleteUser() {
        userService.addUser(new User(0, "Alice", "alice@example.com"));
        assertTrue(userService.deleteUser(1));
        assertNull(userService.getUser(1));
    }

    @Test
    public void testDeleteUserNotFound() {
        assertFalse(userService.deleteUser(999));
    }
}
