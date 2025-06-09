package com.rOushAn.cabcore.service;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.RiderDto;
import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.Rider;

public interface RatingManagementService {
    DriverDto rateDriver(Ride ride, Driver driver, Double rating);

    RiderDto rateRider(Ride ride, Rider rider, Double rating);

    void createNewRating(Ride ride);
}
