package org.example.manager;

import org.example.entity.Action;
import org.example.entity.UserRole;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PermissionManager {
    private HashMap<UserRole, List<Action>> permissions;

    public PermissionManager(HashMap<UserRole, List<Action>> permissions) {
        this.permissions = permissions;
        permissions.putIfAbsent(UserRole.OWNER, new ArrayList<>());
        permissions.putIfAbsent(UserRole.EDITOR, new ArrayList<>());
        permissions.putIfAbsent(UserRole.VIEWER, new ArrayList<>());
        permissions.get(UserRole.OWNER).add(Action.READ);
        permissions.get(UserRole.OWNER).add(Action.WRITE);
        permissions.get(UserRole.OWNER).add(Action.SHARE);
        permissions.get(UserRole.OWNER).add(Action.DELETE);
        permissions.get(UserRole.EDITOR).add(Action.READ);
        permissions.get(UserRole.EDITOR).add(Action.WRITE);
        permissions.get(UserRole.VIEWER).add(Action.READ);
    }

    public boolean hasPermission(UserRole role, Action action) {
        List<Action> allowedActions = permissions.get(role);
        return allowedActions != null && allowedActions.contains(action);
    }

    public void addPermission(UserRole role, Action action) {
        permissions.putIfAbsent(role, new ArrayList<>());
        if (permissions.get(role).stream().anyMatch(existingAction -> existingAction.equals(action))) {
            System.out.println("Permission already exists: " + role + " can already " + action);
            return;
        }
        permissions.get(role).add(action);
        System.out.println("Permission added: " + role + " can " + action);
    }

}
