package org.example.models;

import lombok.Data;

@Data
public class Coffee {
    private String name;
    private double price;
    private CoffeeType coffeeType;
    private Recipe recipe;

    public Coffee(String name, double price, Recipe recipe,CoffeeType coffeeType) {
        this.name = name;
        this.price = price;
        this.recipe = recipe;
        this.coffeeType=coffeeType;
    }


}
