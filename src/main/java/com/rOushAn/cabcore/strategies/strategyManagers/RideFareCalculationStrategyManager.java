package com.rOushAn.cabcore.strategies.strategyManagers;

import com.rOushAn.cabcore.strategies.RideFareCalculation;
import com.rOushAn.cabcore.strategies.implementations.RideFareDefaultCalculation;
import com.rOushAn.cabcore.strategies.implementations.RideFareSurgePricing;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class RideFareCalculationStrategyManager {

    private final RideFareDefaultCalculation rideFareDefaultCalculation;
    private final RideFareSurgePricing rideFareSurgePricing;

    public RideFareCalculationStrategyManager(RideFareDefaultCalculation rideFareDefaultCalculation, RideFareSurgePricing rideFareSurgePricing) {
        this.rideFareDefaultCalculation = rideFareDefaultCalculation;
        this.rideFareSurgePricing = rideFareSurgePricing;
    }

    public RideFareCalculation rideFareCalculation() {

        LocalTime surgeStartTime = LocalTime.of(18, 0);
        LocalTime surgeEndTime = LocalTime.of(21, 0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurge = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if (isSurge) {
            return rideFareSurgePricing;
        } else {
            return rideFareDefaultCalculation;
        }
    }
}