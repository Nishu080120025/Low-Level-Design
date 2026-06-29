package org.example.factory;

import org.example.models.PaymentMethod;
import org.example.paymentprocessor.CashPaymentProcessor;
import org.example.paymentprocessor.PaymentProcessor;
import org.example.paymentprocessor.UpiPaymentProcessor;

public class PaymentProcessorFactory {

    public static PaymentProcessor getPaymentProcessor(PaymentMethod paymentMethod){
        switch(paymentMethod){
            case UPI:
                return new UpiPaymentProcessor();
            case CASH:
                return new CashPaymentProcessor();
            default:
                throw new IllegalArgumentException("Invalid payment method: " + paymentMethod);
        }
    }
}
