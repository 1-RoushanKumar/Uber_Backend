package com.rOushAn.cabcore.strategies;

import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDrivers(RideRequest rideRequest);
}
