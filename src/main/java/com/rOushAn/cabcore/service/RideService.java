package com.rOushAn.cabcore.service;

import com.rOushAn.cabcore.dtos.RideDto;
import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.RideRequest;
import com.rOushAn.cabcore.entities.Rider;
import com.rOushAn.cabcore.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, Pageable pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver, Pageable pageRequest);

    Page<RideDto> getAllRides(Pageable pageable);
}
