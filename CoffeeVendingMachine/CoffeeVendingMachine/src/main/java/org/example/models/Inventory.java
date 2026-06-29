package org.example.models;

import lombok.Data;

import java.util.HashMap;

@Data
public class Inventory {
    private HashMap<String,Integer> quantityMap;

    public Inventory(HashMap<String,Integer> quantityMap) {
        this.quantityMap = quantityMap;
    }

    public void addIngredient(String ingredient, int quantity) {
        quantityMap.put(ingredient, quantityMap.getOrDefault(ingredient, 0) + quantity);
    }
    public void decrementIngredient(String ingredient, int quantity) {
        if (quantityMap.containsKey(ingredient)) {
            int currentQuantity = quantityMap.get(ingredient);
            if (currentQuantity >= quantity) {
                quantityMap.put(ingredient, currentQuantity - quantity);
            } else {
                throw new IllegalArgumentException("Not enough " + ingredient + " in inventory.");
            }
        } else {
            throw new IllegalArgumentException(ingredient + " not found in inventory.");
        }
    }
}
