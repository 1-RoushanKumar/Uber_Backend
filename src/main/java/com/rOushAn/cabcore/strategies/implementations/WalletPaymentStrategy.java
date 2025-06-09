package com.rOushAn.cabcore.strategies.implementations;

import com.rOushAn.cabcore.entities.Payment;
import com.rOushAn.cabcore.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletPaymentStrategy implements PaymentStrategy {

    @Override
    @Transactional
    public void processPayment(Payment payment) {

    }
}