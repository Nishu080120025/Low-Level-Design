package org.example.service.impl;

import org.example.entity.User;
import org.example.entity.UserRole;
import org.example.repository.UserRepository;
import org.example.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(String userId, String name) {
        User user = new User(userId, name, null);
        userRepository.addUser(userId, user);
        System.out.println("User created: " + userId);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteUser(userId);
    }


    @Override
    public User getUser(String userId) {
        User user = userRepository.getUser(userId);
        if (user != null) {
            System.out.println("User details: " + user);
            return user;
        } else {
            System.out.println("User not found: " + userId);
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.getAllUsers();
        System.out.println("All users: " + userList);
        return userList;
    }

}
