package com.rOushAn.cabcore.strategies;

import com.rOushAn.cabcore.entities.RideRequest;

public interface RideFareCalculation {

    double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);
}
