package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.entities.Payment;
import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.enums.PaymentStatus;
import com.rOushAn.cabcore.exceptions.ResourceNotFoundException;
import com.rOushAn.cabcore.repositories.PaymentRepository;
import com.rOushAn.cabcore.service.PaymentService;
import com.rOushAn.cabcore.strategies.strategyManagers.PaymentStrategyManager;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;

    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentStrategyManager paymentStrategyManager) {
        this.paymentRepository = paymentRepository;
        this.paymentStrategyManager = paymentStrategyManager;
    }

    @Override
    public void processPayment(Ride ride) {

        Payment payment = paymentRepository.findByRide(ride).orElseThrow(() -> new ResourceNotFoundException("Payment not found!"));

        paymentStrategyManager
                .paymentStrategy(payment.getPaymentMethod())
                .processPayment(payment);

        updatePaymentStatus(payment, PaymentStatus.CONFIRMED);
    }

    @Override
    public Payment createNewPayment(Ride ride) {

        Payment payment = new Payment
                .PaymentBuilder()
                .amount(ride.getFare())
                .paymentMethod(ride.getPaymentMethod())
                .paymentStatus(PaymentStatus.PENDING)
                .ride(ride)
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus) {
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
    }
}