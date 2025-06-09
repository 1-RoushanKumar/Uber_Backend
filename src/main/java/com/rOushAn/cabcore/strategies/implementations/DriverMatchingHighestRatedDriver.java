package com.rOushAn.cabcore.strategies.implementations;

import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.RideRequest;
import com.rOushAn.cabcore.repositories.DriverRepository;
import com.rOushAn.cabcore.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingHighestRatedDriver implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    public DriverMatchingHighestRatedDriver(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return driverRepository.findTenNearbyTopRatedDrivers(rideRequest.getPickUpLocation());
    }
}