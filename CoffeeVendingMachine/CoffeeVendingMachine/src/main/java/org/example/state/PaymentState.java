package org.example.state;

import org.example.CoffeeMachine;
import org.example.models.Payment;
import org.example.models.PaymentMethod;

public class PaymentState implements CoffeeState{

    private CoffeeMachine coffeeMachine;

    public PaymentState(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    @Override
    public synchronized  void selectCoffee(String coffeeName) {
        System.out.println("Payment already in progress. Please complete the payment or cancel the order.");
    }

    @Override
    public synchronized void insertPayment(double amount){
        double price=coffeeMachine.getSelectedCoffee().getPrice();
        Payment payment=new Payment(amount, PaymentMethod.UPI);
        if(payment.getAmount()>=price){
            double change=payment.getAmount()-price;
            coffeeMachine.getPaymentProcessorFactory().getPaymentProcessor(payment.getPaymentMethod()).pay(payment);
            System.out.println("Change returned: " + change);
            coffeeMachine.setCurrentState(coffeeMachine.getDispenseState());
        }
        else {
            System.out.println("Insufficient payment. Please insert at least: " + (price - payment.getAmount()) + " more.");
            coffeeMachine.setCurrentState(coffeeMachine.getIdleState());
        }

    }

    @Override
    public void dispenseCoffee(){
        System.out.println("Please complete the payment first before dispensing coffee.");
    }

    @Override
    public void cancelOrder(){
        System.out.println("Order cancelled. Returning to idle state.");
        coffeeMachine.setCurrentState(coffeeMachine.getIdleState());
    }

    @Override
    public void refillIngredients(java.util.HashMap<String, Integer> ingredients) {
        System.out.println("Cannot refill ingredients during payment. Please complete or cancel the payment first.");
    }


}

