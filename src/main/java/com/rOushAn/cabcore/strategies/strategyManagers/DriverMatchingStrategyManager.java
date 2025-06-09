package com.rOushAn.cabcore.strategies.strategyManagers;

import com.rOushAn.cabcore.strategies.DriverMatchingStrategy;
import com.rOushAn.cabcore.strategies.implementations.DriverMatchingHighestRatedDriver;
import com.rOushAn.cabcore.strategies.implementations.DriverMatchingNearestDrivers;
import org.springframework.stereotype.Component;

@Component
public class DriverMatchingStrategyManager {

    private final DriverMatchingHighestRatedDriver driverMatchingHighestRatedDriver;
    private final DriverMatchingNearestDrivers driverMatchingNearestDrivers;


    public DriverMatchingStrategyManager(DriverMatchingHighestRatedDriver driverMatchingHighestRatedDriver, DriverMatchingNearestDrivers driverMatchingNearestDrivers) {
        this.driverMatchingHighestRatedDriver = driverMatchingHighestRatedDriver;
        this.driverMatchingNearestDrivers = driverMatchingNearestDrivers;
    }

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating) {

        if (riderRating > 4.8) {
            return driverMatchingHighestRatedDriver;
        } else {
            return driverMatchingNearestDrivers;
        }
    }

}