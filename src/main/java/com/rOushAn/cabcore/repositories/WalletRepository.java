package com.rOushAn.cabcore.repositories;

import com.rOushAn.cabcore.entities.User;
import com.rOushAn.cabcore.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
    void deleteByUserId(Long userId);
}
