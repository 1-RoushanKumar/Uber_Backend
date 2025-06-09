package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.User;
import com.rOushAn.cabcore.entities.Wallet;
import com.rOushAn.cabcore.entities.WalletTransaction;
import com.rOushAn.cabcore.entities.enums.TransactionMethod;
import com.rOushAn.cabcore.entities.enums.TransactionType;
import com.rOushAn.cabcore.exceptions.ResourceNotFoundException;
import com.rOushAn.cabcore.repositories.WalletRepository;
import com.rOushAn.cabcore.service.WalletService;
import com.rOushAn.cabcore.service.WalletTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;

    public WalletServiceImpl(WalletRepository walletRepository, WalletTransactionService walletTransactionService) {
        this.walletRepository = walletRepository;
        this.walletTransactionService = walletTransactionService;
    }

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        Double prevAmount = wallet.getBalance();
        Double newAmount = prevAmount + amount;
        wallet.setBalance(newAmount);

        WalletTransaction walletTransaction = new WalletTransaction
                .WalletTransactionBuilder()
                .transactionId(transactionId)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.CREDIT)
                .ride(ride)
                .amount(amount)
                .wallet(wallet)
                .build();
        walletTransactionService.createNewWalletTransaction(walletTransaction);

        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet subtractMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        Double prevAmount = wallet.getBalance();
        Double newAmount = prevAmount - amount;
        wallet.setBalance(newAmount);

        WalletTransaction walletTransaction = new WalletTransaction
                .WalletTransactionBuilder()
                .transactionId(transactionId)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.DEBIT)
                .ride(ride)
                .amount(amount)
                .wallet(wallet)
                .build();

        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository
                .findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("No wallet was found with ID: " + walletId));
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository
                .findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("No wallet was found with ID: " + user.getId()));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(1000D);
        return walletRepository.save(wallet);
    }
}