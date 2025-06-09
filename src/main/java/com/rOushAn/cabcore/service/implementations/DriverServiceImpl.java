package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.DriverRideDto;
import com.rOushAn.cabcore.dtos.RideStartDto;
import com.rOushAn.cabcore.dtos.RiderDto;
import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.service.DriverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DriverServiceImpl implements DriverService {

    @Override
    @Transactional
    public DriverRideDto acceptRide(Long rideRequestId) {
        return null;
    }

    @Override
    public DriverRideDto cancelRide(Long rideId) {

        return null;
    }

    @Override
    @Transactional
    public DriverRideDto startRide(Long rideId, RideStartDto rideStartDto) {

        return null;
    }

    @Override
    @Transactional
    public DriverRideDto endRide(Long rideId) {

        return null;
    }

    @Override
    public DriverDto getDriverProfile() {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId, Double rating) {

        return null;
    }

    @Override
    public Page<DriverRideDto> getAllMyRides(Pageable pageRequest) {
        return null;
    }

    @Override
    public Driver getCurrentDriver() {
        return null;
    }

    @Override
    public Driver getDriverById(Long driverId) {
        return null;
    }

    @Override
    public Driver updateRating(Driver driver, Double rating) {
        return null;
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        return null;
    }

    @Override
    public Driver createNewDriver(Driver createDriver) {
        return null;
    }


}