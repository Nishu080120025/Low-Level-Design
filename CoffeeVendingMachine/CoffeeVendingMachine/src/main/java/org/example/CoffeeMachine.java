package org.example;

import lombok.Data;
import org.example.factory.PaymentProcessorFactory;
import org.example.models.Coffee;
import org.example.models.Inventory;
import org.example.models.Menu;
import org.example.state.*;

import java.util.HashMap;
import java.util.Map;

@Data
public class CoffeeMachine {

    private CoffeeState currentState;
    private CoffeeState idleState;
    private CoffeeState paymentState;
    private CoffeeState dispenseState;
    private CoffeeState outOfStockState;
    private Coffee selectedCoffee;
    private Inventory inventory;
    private PaymentProcessorFactory paymentProcessorFactory;
    private Menu menu;

    public CoffeeMachine() {
        this.inventory = new Inventory(new HashMap<>());
        this.idleState = new IdleState(this);
        this.paymentState = new PaymentState(this);
        this.dispenseState = new DispenseState(this);
        this.outOfStockState = new OutOfStockState(this);
        this.currentState = idleState;
        this.selectedCoffee = null;
        this.paymentProcessorFactory = getPaymentProcessorFactory();
        this.menu = new Menu();
    }

    public void refillInventory(Map<String, Integer> ingredients) {
        for (Map.Entry<String, Integer> entry : ingredients.entrySet()) {
            inventory.addIngredient(entry.getKey(), entry.getValue());
        }
    }

    public void updateInventory(String ingredient, int quantity) {
        inventory.decrementIngredient(ingredient, quantity);
    }

    public boolean checkIngredientAvailability(String ingredient, int requiredQuantity) {
        Integer availableQuantity = inventory.getQuantityMap().get(ingredient);
        return availableQuantity != null && availableQuantity >= requiredQuantity;
    }

    public boolean checkIfIngredientOutOfStock(String ingredient) {
        Integer availableQuantity = inventory.getQuantityMap().get(ingredient);
        return availableQuantity != null && availableQuantity <= 0;
    }

    public void addCoffeeToMenu(Coffee coffee) {
        menu.addCoffeeToMenu(coffee);
    }

    public String showMenu() {
        return menu.showMenu();
    }

    public synchronized void selectCoffee(String coffeeName) {
        this.currentState.selectCoffee(coffeeName);
    }

    public synchronized void insertPayment(double amount) {
        this.currentState.insertPayment(amount);
    }

    public synchronized void dispenseCoffee() {
        this.currentState.dispenseCoffee();
    }

    public synchronized void cancelOrder() {
        this.currentState.cancelOrder();
    }

    public synchronized void refillIngredients(Map<String, Integer> ingredients) {
        this.currentState.refillIngredients(ingredients);
    }


}
