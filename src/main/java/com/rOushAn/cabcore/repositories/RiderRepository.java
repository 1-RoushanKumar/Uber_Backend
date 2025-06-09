package com.rOushAn.cabcore.repositories;

import com.rOushAn.cabcore.entities.Rider;
import com.rOushAn.cabcore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {
    Optional<Rider> findByUser(User user);
}
