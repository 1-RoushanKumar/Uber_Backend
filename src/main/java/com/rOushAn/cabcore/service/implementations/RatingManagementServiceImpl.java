package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.RiderDto;
import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.Rider;
import com.rOushAn.cabcore.service.RatingManagementService;
import org.springframework.stereotype.Service;


@Service
public class RatingManagementServiceImpl implements RatingManagementService {

    @Override
    public DriverDto rateDriver(Ride ride, Driver driver, Double rating) {
        return null;
    }

    @Override
    public RiderDto rateRider(Ride ride, Rider rider, Double rating) {

        return null;
    }

    @Override
    public void createNewRating(Ride ride) {
    }


}