package org.example.service;

import org.example.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(String username, String email);
    Optional<User> getUserById(String userId);
    List<Optional<User>> getAllUsers();
    Optional<User>getUserByUserName(String userName);
}
