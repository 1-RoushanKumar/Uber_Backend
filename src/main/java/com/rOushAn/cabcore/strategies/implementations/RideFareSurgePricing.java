package com.rOushAn.cabcore.strategies.implementations;

import com.rOushAn.cabcore.entities.RideRequest;
import com.rOushAn.cabcore.service.DistanceCalculationService;
import com.rOushAn.cabcore.strategies.RideFareCalculation;
import org.springframework.stereotype.Service;

@Service
public class RideFareSurgePricing implements RideFareCalculation {

    private final DistanceCalculationService distanceCalculationService;

    public RideFareSurgePricing(DistanceCalculationService distanceCalculationService) {
        this.distanceCalculationService = distanceCalculationService;
    }

    private static final double SURGE_FACTOR = 2;

    @Override
    public double calculateFare(RideRequest rideRequest) {

        Double distance = distanceCalculationService.calculateDistance(rideRequest.getPickUpLocation(), rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER * SURGE_FACTOR;

    }
}