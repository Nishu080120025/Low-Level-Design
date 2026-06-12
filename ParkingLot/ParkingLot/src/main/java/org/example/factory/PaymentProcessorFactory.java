package org.example.factory;

import org.example.entities.enums.PaymentMethod;
import org.example.strategy.CreditCardPaymentProcessor;
import org.example.strategy.PaymentProcessor;
import org.example.strategy.UPIPaymentProcessor;

public class PaymentProcessorFactory {
    public static PaymentProcessor choosePaymentProcessor(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case UPI:
                return new UPIPaymentProcessor();
            case CREDIT_CARD:
                return new CreditCardPaymentProcessor();
            default:
                throw new IllegalArgumentException("Unsupported payment method: " + paymentMethod);
        }
    }
}
