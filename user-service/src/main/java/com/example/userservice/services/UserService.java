package com.example.userservice.services;

import com.example.userservice.entities.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Map<Integer, User> users = new HashMap<>();
    private int nextId = 1;

    public User addUser(User user) {
        user.setId(nextId++);
        users.put(user.getId(), user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public User getUser(int id) {
        return users.get(id);
    }

    public User updateUser(int id, User updated) {
        User user = users.get(id);
        if (user != null) {
            user.setName(updated.getName());
            user.setEmail(updated.getEmail());
        }
        return user;
    }

    public boolean deleteUser(int id) {
        return users.remove(id) != null;
    }
}
