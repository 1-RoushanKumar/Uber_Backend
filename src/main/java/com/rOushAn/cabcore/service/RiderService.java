package com.rOushAn.cabcore.service;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.RideDto;
import com.rOushAn.cabcore.dtos.RideRequestDto;
import com.rOushAn.cabcore.dtos.RiderDto;
import com.rOushAn.cabcore.entities.Rider;
import com.rOushAn.cabcore.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    RiderDto getRiderProfile();

    Page<RideDto> getAllMyRides(Pageable pageRequest);

    DriverDto rateDriver(Long rideId, Double rating);

    void createNewRider(User savedUser);

    Rider getCurrentRider();

    Rider getRiderById(Long id);

    Rider updateRating(Rider rider, Double rating);
}
