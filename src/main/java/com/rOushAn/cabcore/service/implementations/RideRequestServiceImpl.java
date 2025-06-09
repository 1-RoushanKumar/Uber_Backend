package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.entities.RideRequest;
import com.rOushAn.cabcore.exceptions.ResourceNotFoundException;
import com.rOushAn.cabcore.repositories.RideRequestRepository;
import com.rOushAn.cabcore.service.RideRequestService;
import org.springframework.stereotype.Service;

@Service
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    public RideRequestServiceImpl(RideRequestRepository rideRequestRepository) {
        this.rideRequestRepository = rideRequestRepository;
    }

    @Override
    public RideRequest findRideRequestById(Long id) {
        return rideRequestRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No ride request was found with ID: " + id));
    }

    @Override
    public void updateRideRequest(RideRequest rideRequest) {

        RideRequest request = rideRequestRepository
                .findById(rideRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No ride request was found with ID: " + rideRequest.getId()));

        rideRequestRepository.save(rideRequest);

    }
}