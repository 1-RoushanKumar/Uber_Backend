package com.rOushAn.cabcore.repositories;

import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByDriver(Driver driver);
    Optional<Vehicle> findByDriverAndIsActiveTrue(Driver driver);
    Optional<Vehicle> findByDriverAndId(Driver driver, Long vehicleId);
}