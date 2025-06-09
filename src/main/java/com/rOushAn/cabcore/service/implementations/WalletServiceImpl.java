package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.User;
import com.rOushAn.cabcore.entities.Wallet;
import com.rOushAn.cabcore.entities.enums.TransactionMethod;
import com.rOushAn.cabcore.service.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletServiceImpl implements WalletService {

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        return null;
    }

    @Override
    @Transactional
    public Wallet subtractMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        return null;
    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return null;
    }

    @Override
    public Wallet findByUser(User user) {
        return null;
    }

    @Override
    public Wallet createNewWallet(User user) {
        return null;
    }
}