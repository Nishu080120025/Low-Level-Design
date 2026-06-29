package org.example.state;

import org.example.CoffeeMachine;
import org.example.models.Coffee;

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
    public synchronized void insertPayment(double amount) {
        System.out.println("Dispensing in progress. Please wait until the current coffee is dispensed.");
    }

    @Override
    public synchronized void dispenseCoffee() {
//        Coffee selectedCoffee = coffeeMachine.getSelectedCoffee();
//        for (String ingredient : coffeeMachine.getInventory().keySet()) {
//            int amount = coffeeMachine.getInventory().get(ingredient);
//            if (amount == 0) {
//                System.out.println("Cannot dispense coffee. Ingredient " + ingredient + " is out of stock.");
//                coffeeMachine.setCurrentState(coffeeMachine.getOutOfStockState());
//                return;
//            }
//        }
//
//        for (String ingredient : selectedCoffee.getRecipe().getIngredients().keySet()) {
//            if (coffeeMachine.getInventory().get(ingredient) == null) {
//                System.out.println("Cannot dispense coffee. Ingredient " + ingredient + " is not available in the inventory.");
//                System.out.println("Refund the amount " + coffeeMachine.getSelectedCoffee().getPrice());
//                coffeeMachine.setSelectedCoffee(null);
//
//                coffeeMachine.setCurrentState(coffeeMachine.getIdleState());
//                return;
//            }
//            int val = coffeeMachine.getInventory().get(ingredient);
//            int remaining = val - selectedCoffee.getRecipe().getIngredients().get(ingredient);
//            if (remaining < 0) {
//                System.out.println("Cannot dispense this coffee ");
//                System.out.println("Ingredient " + ingredient + " is insufficient for the selected coffee.");
//                System.out.println("Refund the amount " + coffeeMachine.getSelectedCoffee().getPrice());
//                coffeeMachine.setSelectedCoffee(null);
//                coffeeMachine.setCurrentState(coffeeMachine.getIdleState());
//                return;
//            }
//            coffeeMachine.updateInventory(ingredient, remaining);
//        }
//        coffeeMachine.setCurrentState(coffeeMachine.getIdleState());
//        System.out.println("Coffee dispensed: " + selectedCoffee.getName());
//        coffeeMachine.setSelectedCoffee(null);

        Coffee selectedCoffee=coffeeMachine.getSelectedCoffee();
        for(String ingredients:selectedCoffee.getRecipe().getIngredients().keySet()) {
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
            for(String ingredient:selectedCoffee.getRecipe().getIngredients().keySet()){
                int amountRequired=selectedCoffee.getRecipe().getIngredients().get(ingredient);
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
