package org.example.models;

import lombok.Data;

import java.util.Map;

@Data
public class Recipe {
    private String description;
    private Map<String,Integer> ingredients;

    public Recipe( String description, Map<String, Integer> ingredients) {

        this.description = description;
        this.ingredients = ingredients;
    }


}
