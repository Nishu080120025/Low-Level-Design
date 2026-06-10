package org.example.strategy;

public class UPIPaymentProcessor implements PaymentProcessor{

    @Override
    public void processPayment(double amount){
        // Logic to process UPI payment
        System.out.println("Processing UPI payment of amount: $ " + amount);
    }

}
