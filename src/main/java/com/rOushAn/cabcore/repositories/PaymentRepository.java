package com.rOushAn.cabcore.repositories;

import com.rOushAn.cabcore.entities.Payment;
import com.rOushAn.cabcore.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByRide(Ride ride);
}