package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.RideRequest;
import com.rOushAn.cabcore.entities.Rider;
import com.rOushAn.cabcore.entities.enums.RideStatus;
import com.rOushAn.cabcore.service.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class RideServiceImpl implements RideService {

    @Override
    public Ride getRideById(Long rideId) {
        return null;
    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        return null;
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, Pageable pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, Pageable pageRequest) {
        return null;
    }

}