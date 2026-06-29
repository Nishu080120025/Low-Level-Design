package org.example.state;

import org.example.CoffeeMachine;

import java.util.Map;

public class OutOfStockState implements CoffeeState{
    private CoffeeMachine coffeeMachine;

    public OutOfStockState(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public  synchronized void selectCoffee(String coffeeName) {
        System.out.println("Cannot select coffee. The machine is out of stock.");
    }

    @Override
    public synchronized void insertPayment(double amount) {
        System.out.println("Cannot insert payment. The machine is out of stock.");
    }
    @Override
    public synchronized void dispenseCoffee() {
        System.out.println("Cannot dispense coffee. The machine is out of stock.");
    }
    @Override
    public synchronized void cancelOrder() {
        System.out.println("Cannot cancel order. The machine is out of stock.");
    }

    @Override
    public synchronized void refillIngredients(Map<String, Integer> ingredients) {
        coffeeMachine.refillInventory(ingredients);
        System.out.println("Ingredients refilled. The machine is now operational.");
        coffeeMachine.setCurrentState(coffeeMachine.getIdleState());
    }
}
