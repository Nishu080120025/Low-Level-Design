package org.example.service;

import org.example.entity.User;
import org.example.entity.UserRole;

import java.util.List;

public interface UserService {
    void createUser(String userId, String name);
    void deleteUser(String userId);
    void assignRole(String userId, UserRole role);
    void removeRole(String userId);
    User getUser(String userId);
    List<User> getAllUsers();
}
