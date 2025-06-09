package com.rOushAn.cabcore.strategies.implementations;

import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.Payment;
import com.rOushAn.cabcore.entities.Rider;
import com.rOushAn.cabcore.entities.enums.TransactionMethod;
import com.rOushAn.cabcore.service.WalletService;
import com.rOushAn.cabcore.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;

    public WalletPaymentStrategy(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    @Transactional
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        double paymentAddedToWallet = payment.getAmount() - (payment.getAmount() * PLATFORM_FEE);

        walletService.addMoneyToWallet(driver.getUser(),
                paymentAddedToWallet,
                null,
                payment.getRide(),
                TransactionMethod.RIDE);

        walletService.subtractMoneyFromWallet(rider.getUser(),
                payment.getAmount(),
                null,
                payment.getRide(),
                TransactionMethod.RIDE);

    }
}