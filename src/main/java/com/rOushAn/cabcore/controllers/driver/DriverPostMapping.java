package com.rOushAn.cabcore.controllers.driver;

import com.rOushAn.cabcore.dtos.*;
import com.rOushAn.cabcore.service.DriverService;
import com.rOushAn.cabcore.service.RatingManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@Secured("ROLE_DRIVER")
public class DriverPostMapping {

    private final DriverService driverService;


    public DriverPostMapping(DriverService driverService, RatingManagementService ratingManagementService) {
        this.driverService = driverService;
    }

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<DriverRideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideId}")
    public ResponseEntity<DriverRideDto> startRide(@PathVariable Long rideId, @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideId, rideStartDto));
    }

    @PostMapping("/endRide/{rideId}")
    public ResponseEntity<DriverRideDto> endRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<DriverRideDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping("/rateRider/{rideId}")
    public ResponseEntity<RiderDto> rateRider(@PathVariable Long rideId, @RequestBody RatingDto ratingDto) {
        return ResponseEntity.ok(driverService.rateRider(rideId, ratingDto.getRating()));
    }

    //Allows drivers to explicitly control their availability, which is crucial for managing ride requests and dispatching.
    @PutMapping("/toggleAvailability")
    public ResponseEntity<DriverDto> toggleAvailability() {
        DriverDto driverDto = driverService.toggleDriverAvailability();
        return ResponseEntity.ok(driverDto);
    }
}