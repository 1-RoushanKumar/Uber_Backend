package com.rOushAn.cabcore.service;

import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.User;
import com.rOushAn.cabcore.entities.Wallet;
import com.rOushAn.cabcore.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet subtractMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet findWalletById(Long walletId);

    Wallet findByUser(User user);

    Wallet createNewWallet(User user);
}
