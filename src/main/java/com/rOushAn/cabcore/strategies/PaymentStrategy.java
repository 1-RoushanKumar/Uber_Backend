package com.rOushAn.cabcore.strategies;

import com.rOushAn.cabcore.entities.Payment;

public interface PaymentStrategy {
    Double PLATFORM_FEE = 0.3;

    void processPayment(Payment payment);
}