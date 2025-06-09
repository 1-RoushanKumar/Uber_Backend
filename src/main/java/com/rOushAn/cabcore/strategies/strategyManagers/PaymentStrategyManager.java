package com.rOushAn.cabcore.strategies.strategyManagers;

import com.rOushAn.cabcore.entities.enums.PaymentMethod;
import com.rOushAn.cabcore.strategies.PaymentStrategy;
import com.rOushAn.cabcore.strategies.implementations.CashPaymentStrategy;
import com.rOushAn.cabcore.strategies.implementations.WalletPaymentStrategy;
import org.springframework.stereotype.Component;

@Component
public class PaymentStrategyManager {

    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;

    public PaymentStrategyManager(WalletPaymentStrategy walletPaymentStrategy, CashPaymentStrategy cashPaymentStrategy) {
        this.walletPaymentStrategy = walletPaymentStrategy;
        this.cashPaymentStrategy = cashPaymentStrategy;
    }

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
        return switch (paymentMethod) {
            case CASH -> cashPaymentStrategy;
            case WALLET -> walletPaymentStrategy;
        };
    }
}