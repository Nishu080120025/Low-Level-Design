package org.example.repository;

import org.example.entity.User;

import java.util.List;

public interface UserRepository {
    void addUser(String userId, User user);
    User getUser(String userId);
    List<User> getAllUsers();
    User updateUser(String userId,User user);
    void deleteUser(String userId);
}
