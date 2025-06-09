package com.rOushAn.cabcore.service;


import com.rOushAn.cabcore.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long id);

    void updateRideRequest(RideRequest rideRequest);
}