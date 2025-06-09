package com.rOushAn.cabcore.strategies.implementations;

import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.Payment;
import com.rOushAn.cabcore.entities.enums.TransactionMethod;
import com.rOushAn.cabcore.service.WalletService;
import com.rOushAn.cabcore.strategies.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;

    public CashPaymentStrategy(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public void processPayment(Payment payment) {

        Driver driver = payment.getRide().getDriver();
        double paymentCommission = payment.getAmount() * PLATFORM_FEE;

        walletService.subtractMoneyFromWallet(driver.getUser(),
                paymentCommission,
                null, // Cash on delivery payments do not have transaction ids
                payment.getRide(),
                TransactionMethod.RIDE);
    }
}

