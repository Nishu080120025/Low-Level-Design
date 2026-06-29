package org.example.state;

import org.example.models.Coffee;

import java.util.HashMap;

public interface CoffeeState {

    void selectCoffee(String coffeeName);

    void insertPayment(double amount);

   void dispenseCoffee();

    void cancelOrder();

    void refillIngredients(HashMap<String, Integer> ingredients);
}
