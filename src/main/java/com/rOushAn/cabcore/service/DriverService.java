package com.rOushAn.cabcore.service;

import com.rOushAn.cabcore.dtos.*;
import com.rOushAn.cabcore.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DriverService {

    DriverRideDto acceptRide(Long rideRequestId);

    DriverRideDto cancelRide(Long rideId);

    DriverRideDto startRide(Long rideId, RideStartDto rideStartDto);

    DriverRideDto endRide(Long rideId);

    DriverDto getDriverProfile();

    RiderDto rateRider(Long rideId, Double rating);

    Page<DriverRideDto> getAllMyRides(Pageable pageRequest);

    Driver getCurrentDriver();

    Driver getDriverById(Long driverId);

    Driver updateRating(Driver driver, Double rating);

    Driver updateDriverAvailability(Driver driver, boolean available);

    Driver createNewDriver(Driver createDriver);

    Page<DriverDto> getAllDrivers(Pageable pageable);

    DriverDto getDriverDetails(Long id);

    VehicleDto addVehicle(AddVehicleDto addVehicleDto);

    VehicleDto updateVehicle(Long vehicleId, AddVehicleDto updateVehicleDto);

    void removeVehicle(Long vehicleId);

    List<VehicleDto> getMyVehicles();

    VehicleDto selectActiveVehicle(Long vehicleId);

    VehicleDto getCurrentActiveVehicle();

    @Transactional
    Driver createNewDriver(Driver createDriver, AddVehicleDto initialVehicleDetails);

    @Transactional
    VehicleDto deactivateVehicle(Long vehicleId);

    DriverDto toggleDriverAvailability();
}
