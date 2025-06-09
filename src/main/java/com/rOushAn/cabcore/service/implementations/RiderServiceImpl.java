package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.RideDto;
import com.rOushAn.cabcore.dtos.RideRequestDto;
import com.rOushAn.cabcore.dtos.RiderDto;
import com.rOushAn.cabcore.entities.Rider;
import com.rOushAn.cabcore.entities.User;
import com.rOushAn.cabcore.service.RiderService;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RiderServiceImpl implements RiderService {

    private final Logger log = Logger.getLogger(RiderServiceImpl.class);


    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        return null;

    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto getRiderProfile() {
        return null;
    }

    @Override
    public Page<RideDto> getAllMyRides(Pageable pageRequest) {
        return null;
    }

    @Override
    public DriverDto rateDriver(Long rideId, Double rating) {
        return null;
    }

    @Override
    public void createNewRider(User savedUser) {

    }

    @Override
    public Rider getCurrentRider() {
        return null;
    }

    @Override
    public Rider getRiderById(Long id) {
        return null;
    }

    @Override
    public Rider updateRating(Rider rider, Double rating) {
        return null;
    }

}