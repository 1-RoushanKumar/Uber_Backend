package com.rOushAn.cabcore.strategies.implementations;

import com.rOushAn.cabcore.entities.Payment;
import com.rOushAn.cabcore.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(Payment payment) {

    }
}
