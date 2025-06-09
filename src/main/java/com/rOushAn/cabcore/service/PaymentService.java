package com.rOushAn.cabcore.service;

import com.rOushAn.cabcore.entities.Payment;
import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
