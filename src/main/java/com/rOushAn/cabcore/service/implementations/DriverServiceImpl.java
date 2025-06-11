package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.dtos.*;
import com.rOushAn.cabcore.entities.*;
import com.rOushAn.cabcore.entities.enums.RideRequestStatus;
import com.rOushAn.cabcore.entities.enums.RideStatus;
import com.rOushAn.cabcore.exceptions.ResourceNotFoundException;
import com.rOushAn.cabcore.exceptions.RuntimeConflictException;
import com.rOushAn.cabcore.repositories.DriverRepository;
import com.rOushAn.cabcore.repositories.VehicleRepository;
import com.rOushAn.cabcore.service.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Service
public class DriverServiceImpl implements DriverService {

    private static final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final PaymentService paymentService;
    private final RatingManagementService ratingManagementService;
    private final VehicleRepository vehicleRepository;

    public DriverServiceImpl(RideRequestService rideRequestService, RatingManagementService ratingManagementService, DriverRepository driverRepository, RideService rideService, ModelMapper modelMapper, PaymentService paymentService, VehicleRepository vehicleRepository) {
        this.rideRequestService = rideRequestService;
        this.driverRepository = driverRepository;
        this.rideService = rideService;
        this.modelMapper = modelMapper;
        this.paymentService = paymentService;
        this.ratingManagementService = ratingManagementService;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    @Transactional
    public DriverRideDto acceptRide(Long rideRequestId) {

        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        Driver driver = getCurrentDriver();

        validateRequest(rideRequest, driver);

        Driver savedDriver = updateDriverAvailability(driver, false);

        Ride ride = rideService.createNewRide(rideRequest, savedDriver);

        return modelMapper.map(ride, DriverRideDto.class);
    }

    @Override
    public DriverRideDto cancelRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        validateRide(ride, driver, RideStatus.CONFIRMED);
        updateDriverAvailability(driver, true);

        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.CANCELLED);


        return modelMapper.map(savedRide, DriverRideDto.class);
    }

    @Override
    @Transactional
    public DriverRideDto startRide(Long rideId, RideStartDto rideStartDto) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        validateRide(ride, driver, RideStatus.CONFIRMED);
        if (!rideStartDto.getOtp().equals(ride.getOtp())) {
            throw new RuntimeConflictException("Invalid OTP");
        }

        ride.setStartedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ONGOING);

        paymentService.createNewPayment(savedRide);
        ratingManagementService.createNewRating(savedRide);

        return modelMapper.map(savedRide, DriverRideDto.class);
    }

    @Override
    @Transactional
    public DriverRideDto endRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        validateRide(ride, driver, RideStatus.ONGOING);

        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide = rideService.updateRideStatus(ride, RideStatus.ENDED);
        updateDriverAvailability(driver, true);
        paymentService.processPayment(savedRide);
        return modelMapper.map(savedRide, DriverRideDto.class);
    }

    @Override
    public DriverDto getDriverProfile() {
        Driver driver = getCurrentDriver();
        return modelMapper.map(driver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Long rideId, Double rating) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        validateRide(ride, driver, RideStatus.ENDED);

        RiderDto riderDto = ratingManagementService.rateRider(ride, ride.getRider(), rating);

        return riderDto;
    }

    @Override
    public Page<DriverRideDto> getAllMyRides(Pageable pageRequest) {
        Driver driver = getCurrentDriver();
        return rideService
                .getAllRidesOfDriver(driver, pageRequest)
                .map(ride -> modelMapper.map(ride, DriverRideDto.class));
    }

    @Override
    public Driver getCurrentDriver() {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return driverRepository
                .findByUser(user)
                .orElseThrow(() -> new AuthenticationServiceException("No driver was found!"));
    }

    @Override
    public Driver getDriverById(Long driverId) {
        return driverRepository
                .findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("No driver was found with ID: " + driverId));
    }

    @Override
    public Driver updateRating(Driver driver, Double rating) {
        driver.setRating(rating);
        Driver savedDriver = driverRepository.save(driver);
        return savedDriver;
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean available) {
        driver.setAvailable(available);
        Driver savedDriver = driverRepository.save(driver);
        return savedDriver;
    }

    @Override
    public Driver createNewDriver(Driver createDriver) {
        return driverRepository.save(createDriver);
    }

    private void validateRequest(RideRequest rideRequest, Driver driver) {
        if (!rideRequest.getStatus().equals(RideRequestStatus.PENDING)) {
            throw new RuntimeConflictException("Ride request is not pending");
        }
        if (!driver.getAvailable()) {
            throw new RuntimeConflictException("Driver is not available");
        }
    }

    public static void validateRide(Ride ride, Driver driver, RideStatus expectedStatus) {
        if (ride == null) {
            throw new ResourceNotFoundException("Ride cannot be null.");
        }
        if (driver == null) {
            throw new ResourceNotFoundException("Driver cannot be null.");
        }
        if (!ride.getStatus().equals(expectedStatus)) {
            throw new RuntimeConflictException(
                    String.format("Invalid ride status! Expected: %s, Found: %s", expectedStatus, ride.getStatus())
            );
        }
        if (ride.getDriver() == null || !ride.getDriver().equals(driver)) {
            throw new RuntimeConflictException("The provided driver does not own this ride.");
        }
    }

    @Override
    public Page<DriverDto> getAllDrivers(Pageable pageable) {
        return driverRepository.findAll(pageable)
                .map(driver -> modelMapper.map(driver, DriverDto.class));
    }

    @Override
    public DriverDto getDriverDetails(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Driver not found with ID: " + id));
        return modelMapper.map(driver, DriverDto.class);
    }

    @Transactional
    @Override
    public Driver createNewDriver(Driver createDriver, AddVehicleDto initialVehicleDetails) {
        Driver savedDriver = driverRepository.save(createDriver);

        Vehicle initialVehicle = modelMapper.map(initialVehicleDetails, Vehicle.class);
        initialVehicle.setDriver(savedDriver);
        initialVehicle.setIsActive(true);

        Vehicle savedVehicle = vehicleRepository.save(initialVehicle);

        savedDriver.setCurrentVehicle(savedVehicle);

        if (savedDriver.getRegisteredVehicles() == null) {
            savedDriver.setRegisteredVehicles(new ArrayList<>());
        }
        savedDriver.getRegisteredVehicles().add(savedVehicle);

        return driverRepository.save(savedDriver);
    }


    @Override
    @Transactional
    public DriverDto toggleDriverAvailability() {
        Driver driver = getCurrentDriver();
        boolean newAvailability = !driver.getAvailable(); // Toggle the current status
        driver.setAvailable(newAvailability);
        driverRepository.save(driver);

        logger.info("Driver {} (ID: {}) availability toggled to {}", driver.getUser().getEmail(), driver.getId(), newAvailability);

        return modelMapper.map(driver, DriverDto.class);
    }
}