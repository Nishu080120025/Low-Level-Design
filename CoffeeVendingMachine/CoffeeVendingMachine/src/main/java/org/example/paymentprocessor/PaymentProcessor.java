package org.example.paymentprocessor;

import org.example.models.Payment;

public interface PaymentProcessor {

    void pay(Payment payment);
}
