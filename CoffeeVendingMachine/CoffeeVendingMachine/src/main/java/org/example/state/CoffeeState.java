package org.example.state;

import org.example.models.Coffee;
import org.example.models.PaymentMethod;

import java.util.HashMap;
import java.util.Map;

public interface CoffeeState {

    void selectCoffee(String coffeeName);

    void insertPayment(double amount, PaymentMethod method);

   void dispenseCoffee();

    void cancelOrder();

    void refillIngredients(Map<String, Integer> ingredients);
}
