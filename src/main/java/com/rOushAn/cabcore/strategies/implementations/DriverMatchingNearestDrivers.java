package com.rOushAn.cabcore.strategies.implementations;

import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.RideRequest;
import com.rOushAn.cabcore.strategies.DriverMatchingStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverMatchingNearestDrivers implements DriverMatchingStrategy {

    @Override
    public List<Driver> findMatchingDrivers(RideRequest rideRequest) {
        return null;
    }
}
