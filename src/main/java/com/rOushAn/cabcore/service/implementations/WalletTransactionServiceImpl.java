package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.entities.WalletTransaction;
import com.rOushAn.cabcore.repositories.WalletTransactionRepository;
import com.rOushAn.cabcore.service.WalletTransactionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final ModelMapper modelMapper;
    private final WalletTransactionRepository walletTransactionRepository;

    public WalletTransactionServiceImpl(ModelMapper modelMapper, WalletTransactionRepository walletTransactionRepository) {
        this.modelMapper = modelMapper;
        this.walletTransactionRepository = walletTransactionRepository;
    }

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}