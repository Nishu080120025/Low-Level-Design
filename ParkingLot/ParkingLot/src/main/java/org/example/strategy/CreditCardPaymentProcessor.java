package org.example.strategy;

public class CreditCardPaymentProcessor implements PaymentProcessor{

    @Override
    public void processPayment(double amount){
        // Logic to process credit card payment
        System.out.println("Processing credit card payment of $" + amount);
    }
}
