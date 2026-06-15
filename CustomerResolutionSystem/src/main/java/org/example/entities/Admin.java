package org.example.entities;

import org.example.entities.enums.UserRole;

public class Admin extends Users {

    public Admin(String adminId, String name, String email, String phoneNumber) {
        super(adminId, name, UserRole.ADMIN, email, phoneNumber);
    }
}
