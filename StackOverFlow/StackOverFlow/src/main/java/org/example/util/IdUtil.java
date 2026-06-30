package org.example.util;

import java.util.UUID;

public class IdUtil {
    public static String generateId(){
        return UUID.randomUUID().toString().substring(0,6);
    }
}
