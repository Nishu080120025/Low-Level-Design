package org.example.service.impl;

import org.example.models.User;
import org.example.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserServiceImpl implements UserService {

    private final ConcurrentHashMap<String, User>userMap;
    private final ConcurrentHashMap<String,User>userNameMap;
    public UserServiceImpl(){
        this.userMap=new ConcurrentHashMap<>();
        this.userNameMap=new ConcurrentHashMap<>();
    }

    @Override
    public User createUser(String userName,String email){
        User user=new User(userName,email);
        userMap.put(user.getId(),user);
        userNameMap.put(user.getUserName(),user);
        return user;
    }

    @Override
    public Optional<User> getUserById(String userId){
        return Optional.ofNullable(userMap.getOrDefault(userId,null));
    }

    @Override
    public List<Optional<User>> getAllUsers(){
        return userMap.keySet().stream().map(this::getUserById).toList();
    }

    @Override
    public Optional<User>getUserByUserName(String userName){
        return Optional.ofNullable(userNameMap.getOrDefault(userName,null));
    }
}
