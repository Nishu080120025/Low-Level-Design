package org.example.manager;

import org.example.entities.Users;
import org.example.entities.enums.Action;
import org.example.entities.enums.UserRole;

public class PermissionManager {
    public static  boolean hasPermission(String userId, Action action, UserRole userRole){
        switch(userRole){
            case ADMIN -> {
                if(action.equals(Action.ADD_AGENT)||action.equals(Action.REMOVE_AGENT)||action.equals(Action.VIEW_AGENTS)||action.equals(Action.SHOW_HISTORY)||action.equals(Action.VIEW_ISSUES)){
                    return true;
                }
            }
            case AGENT->{
                if(action.equals(Action.UPDATE_ISSUE)||action.equals(Action.VIEW_OWN_ISSUES)||action.equals(Action.RESOLVE_ISSUE)){
                    return true;
                }
            }
            case CUSTOMER -> {
                if(action.equals(Action.CREATE_ISSUE)||action.equals(Action.VIEW_OWN_ISSUES)){
                    return true;
                }
            }
            default -> {
                return false;
            }
        }
        return false;
    }
}
