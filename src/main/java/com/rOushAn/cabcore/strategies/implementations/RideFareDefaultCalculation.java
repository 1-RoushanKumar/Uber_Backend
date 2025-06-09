package com.rOushAn.cabcore.strategies.implementations;

import com.rOushAn.cabcore.entities.RideRequest;
import com.rOushAn.cabcore.service.DistanceCalculationService;
import com.rOushAn.cabcore.strategies.RideFareCalculation;
import org.springframework.stereotype.Service;

@Service
public class RideFareDefaultCalculation implements RideFareCalculation {

    private final DistanceCalculationService distanceCalculationService;

    public RideFareDefaultCalculation(DistanceCalculationService distanceCalculationService) {
        this.distanceCalculationService = distanceCalculationService;
    }

    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance = distanceCalculationService.calculateDistance(rideRequest.getPickUpLocation(), rideRequest.getDropOffLocation());
        return distance * RIDE_FARE_MULTIPLIER;
    }
}
