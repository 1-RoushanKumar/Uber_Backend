package com.rOushAn.cabcore.strategies.implementations;

import com.rOushAn.cabcore.entities.RideRequest;
import com.rOushAn.cabcore.strategies.RideFareCalculation;
import org.springframework.stereotype.Service;

@Service
public class RideFareSurgePricing implements RideFareCalculation {

    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 0.0;

    }
}