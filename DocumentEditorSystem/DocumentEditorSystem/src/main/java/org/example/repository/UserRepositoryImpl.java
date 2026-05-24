package org.example.repository;

import org.example.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepositoryImpl implements UserRepository{
    private final HashMap<String, User> userStore;

    public UserRepositoryImpl(HashMap<String, User> userStore) {
        this.userStore = new HashMap<>();
    }

    @Override
    public void addUser(String userId,User user){
        userStore.put(userId,user);
        System.out.println("User added: " + userId);
    }

    @Override
    public User getUser(String userId){
        return userStore.get(userId);
    }

    @Override
    public List<User> getAllUsers(){
        List<User>userList=new ArrayList<>();
        for(User user : userStore.values()){
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User updateUser(String userId,User user) {
        if (userStore.containsKey(userId)) {
            userStore.put(userId, user);
            System.out.println("User updated: " + userId);
            return user;
        }

            System.out.println("User not found: " + userId);
            return null;


    }

    @Override
    public void deleteUser(String userId){
        if(userStore.containsKey(userId)){
            userStore.remove(userId);
            System.out.println("User deleted: " + userId);
        }
        else{
            System.out.println("User not found: " + userId);
        }
    }
}
