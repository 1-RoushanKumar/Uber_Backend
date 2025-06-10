package com.rOushAn.cabcore.controllers.driver;

import com.rOushAn.cabcore.dtos.*;
import com.rOushAn.cabcore.service.DriverService;
import com.rOushAn.cabcore.service.RatingManagementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/driver")
@Secured("ROLE_DRIVER")
@Tag(name = "Driver - Ride Actions & Availability", description = "Endpoints for drivers to manage ride lifecycle, update location, and control availability.")
public class DriverPostMapping {

    private final DriverService driverService;


    public DriverPostMapping(DriverService driverService, RatingManagementService ratingManagementService) {
        this.driverService = driverService;
    }

    @PostMapping("/acceptRide/{rideRequestId}")
    @Operation(summary = "Accept a ride request",
            description = "Allows an available driver to accept a pending ride request.")
    public ResponseEntity<DriverRideDto> acceptRide(@PathVariable Long rideRequestId) {
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideId}")
    @Operation(summary = "Start a ride",
            description = "Marks a ride as started. Requires pickup location and destination to be provided.")
    public ResponseEntity<DriverRideDto> startRide(@PathVariable Long rideId, @RequestBody RideStartDto rideStartDto) {
        return ResponseEntity.ok(driverService.startRide(rideId, rideStartDto));
    }

    @PostMapping("/endRide/{rideId}")
    @Operation(summary = "End a ride",
            description = "Marks a ride as completed and triggers fare calculation (if applicable).")
    public ResponseEntity<DriverRideDto> endRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.endRide(rideId));
    }

    @PostMapping("/cancelRide/{rideId}")
    @Operation(summary = "Cancel an ongoing ride",
            description = "Allows a driver to cancel a ride before completion.")
    public ResponseEntity<DriverRideDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }

    @PostMapping("/rateRider/{rideId}")
    @Operation(summary = "Rate a rider",
            description = "Allows a driver to submit a rating for a completed ride's rider.")
    public ResponseEntity<RiderDto> rateRider(@PathVariable Long rideId, @RequestBody RatingDto ratingDto) {
        return ResponseEntity.ok(driverService.rateRider(rideId, ratingDto.getRating()));
    }

    //Allows drivers to explicitly control their availability, which is crucial for managing ride requests and dispatching.
    @PutMapping("/toggleAvailability")
    @Operation(summary = "Toggle driver availability",
            description = "Switches the authenticated driver's availability status between available and unavailable.")
    public ResponseEntity<DriverDto> toggleAvailability() {
        DriverDto driverDto = driverService.toggleDriverAvailability();
        return ResponseEntity.ok(driverDto);
    }
}