package org.example.paymentprocessor;

import org.example.models.Payment;

public class UpiPaymentProcessor implements PaymentProcessor{

    @Override
    public void pay(Payment payment){
        System.out.println("Processing UPI payment of amount: " + payment.getAmount());
        // Implement UPI payment processing logic here
    }
}
