package org.example.state;

import org.example.CoffeeMachine;
import org.example.models.Coffee;
import org.example.models.PaymentMethod;

import java.util.Map;

public class DispenseState implements CoffeeState {

    private CoffeeMachine coffeeMachine;

    public DispenseState(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public synchronized void selectCoffee(String coffeeName) {
        System.out.println("Dispensing in progress. Please wait until the current coffee is dispensed.");

    }

    @Override
    public synchronized void insertPayment(double amount, PaymentMethod method) {
        System.out.println("Dispensing in progress. Please wait until the current coffee is dispensed.");
    }

    @Override
    public synchronized void dispenseCoffee() {
        Coffee selectedCoffee = coffeeMachine.getSelectedCoffee();
        for (String ingredients : selectedCoffee.getRecipe().getIngredients().keySet()) {
            int requiredAmount = selectedCoffee.getRecipe().getIngredients().get(ingredients);
            boolean isOutOfStockIngredient = coffeeMachine.checkIfIngredientOutOfStock(ingredients);
            if (isOutOfStockIngredient) {
                System.out.println("Cannot dispense coffee. Ingredient " + ingredients + " is out of stock.");
                System.out.println("Refund the amount " + coffeeMachine.getSelectedCoffee().getPrice());
                coffeeMachine.setSelectedCoffee(null);
                coffeeMachine.setCurrentState(coffeeMachine.getOutOfStockState());
                return;
            }
            boolean hasAvalaiblity = coffeeMachine.checkIngredientAvailability(ingredients, requiredAmount);
            if (!hasAvalaiblity) {
                System.out.println("Cannot dispense coffee. Ingredient " + ingredients + " is insufficient for the selected coffee.");
                System.out.println("Refund the amount " + coffeeMachine.getSelectedCoffee().getPrice());
                coffeeMachine.setSelectedCoffee(null);
                coffeeMachine.setCurrentState(coffeeMachine.getIdleState());
                return;
            }
        }
        System.out.println("Dispensing coffee: " + selectedCoffee.getName());
        for (String ingredient : selectedCoffee.getRecipe().getIngredients().keySet()) {
            int amountRequired = selectedCoffee.getRecipe().getIngredients().get(ingredient);
            coffeeMachine.updateInventory(ingredient, amountRequired);
        }
        coffeeMachine.setSelectedCoffee(null);
        coffeeMachine.setCurrentState(coffeeMachine.getIdleState());

    }

    @Override
    public synchronized void cancelOrder() {
        System.out.println("Dispensing in progress. Cannot cancel the order at this stage.");
    }

    @Override
    public synchronized void refillIngredients(Map<String, Integer> ingredients) {
        System.out.println("Dispensing in progress. Cannot refill ingredients at this stage.");
    }
}
