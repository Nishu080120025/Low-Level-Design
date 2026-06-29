package org.example.state;

import org.example.CoffeeMachine;
import org.example.models.Coffee;

import java.util.Map;

public class IdleState implements CoffeeState {

    private CoffeeMachine coffeeMachine;

    public IdleState(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public synchronized void selectCoffee(String coffeeName){
        Coffee selectedCoffee=coffeeMachine.getMenu().getMenuMap().get(coffeeName);
        if(selectedCoffee==null){
            System.out.println("Selected coffee is not available in the menu.");

        }
        coffeeMachine.setSelectedCoffee(selectedCoffee);
        coffeeMachine.setCurrentState(coffeeMachine.getPaymentState());
        System.out.println("Coffee selected: " + coffeeName + ". Please insert payment.");

    }

    @Override
    public synchronized void insertPayment(double amount){

        System.out.println("Please select a coffee first before inserting payment.");
    }

    @Override
    public synchronized void dispenseCoffee(){
        System.out.println("Please select a coffee and insert payment before dispensing.");
    }

    @Override
    public synchronized void cancelOrder(){
        System.out.println("No order to cancel. Please select a coffee first.");
    }

    @Override
    public synchronized void refillIngredients(Map<String, Integer> ingredients) {
        coffeeMachine.refillInventory(ingredients);
        System.out.println("Ingredients refilled. The machine is now operational.");
    }
}
