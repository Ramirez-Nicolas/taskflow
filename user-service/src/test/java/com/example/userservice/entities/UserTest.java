package com.example.userservice.entities;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User(1, "Alice", "alice@example.com");
        assertEquals(1, user.getId());
        assertEquals("Alice", user.getName());
        assertEquals("alice@example.com", user.getEmail());
    }

    @Test
    public void testUserSetters() {
        User user = new User();
        user.setId(2);
        user.setName("Bob");
        user.setEmail("bob@example.com");

        assertEquals(2, user.getId());
        assertEquals("Bob", user.getName());
        assertEquals("bob@example.com", user.getEmail());
    }

    @Test
    public void testUserDefaultConstructor() {
        User user = new User();
        assertEquals(0, user.getId());
        assertNull(user.getName());
        assertNull(user.getEmail());
    }
}
