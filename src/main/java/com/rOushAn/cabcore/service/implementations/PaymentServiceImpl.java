package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.entities.Payment;
import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.enums.PaymentStatus;
import com.rOushAn.cabcore.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public void processPayment(Ride ride) {
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        return null;
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
    }
}