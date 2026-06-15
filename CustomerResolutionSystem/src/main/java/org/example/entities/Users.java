package org.example.entities;

import org.example.entities.enums.UserRole;

public abstract class Users {
    private String userId;
    private String name;
    private UserRole userRole;
    private String email;
    private String phoneNumber;

    public Users(String userId, String name, UserRole userRole, String email, String phoneNumber) {
        this.userId = userId;
        this.name = name;
        this.userRole = userRole;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", userRole=" + userRole +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
