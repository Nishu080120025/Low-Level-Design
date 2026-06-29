package org.example.models;

import lombok.Data;

@Data
public class Payment {
    private double amount;
    private PaymentMethod paymentMethod;

    public Payment(double amount,PaymentMethod paymentMethod){
        this.amount = amount;
        this.paymentMethod=paymentMethod;
    }
}
