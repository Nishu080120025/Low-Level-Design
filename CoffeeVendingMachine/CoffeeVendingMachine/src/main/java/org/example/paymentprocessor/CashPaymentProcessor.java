package org.example.paymentprocessor;

import org.example.models.Payment;

public class CashPaymentProcessor implements PaymentProcessor {

    @Override
    public void pay(Payment payment){
        System.out.println("Processing cash payment of amount: " + payment.getAmount());
        // Implement cash payment processing logic here
    }
}
