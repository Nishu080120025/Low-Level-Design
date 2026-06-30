package org.example.models;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Tags {
    private String name;
    public Tags(String name){
        this.name=name;
    }
}
