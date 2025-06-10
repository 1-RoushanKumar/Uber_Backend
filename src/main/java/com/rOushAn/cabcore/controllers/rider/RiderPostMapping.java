package com.rOushAn.cabcore.controllers.rider;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.RatingDto;
import com.rOushAn.cabcore.dtos.RideDto;
import com.rOushAn.cabcore.dtos.RideRequestDto;
import com.rOushAn.cabcore.service.RatingManagementService;
import com.rOushAn.cabcore.service.RiderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
@Secured("ROLE_RIDER")
@Tag(name = "Rider - Ride Actions", description = "Endpoints for riders to request and manage rides.")
@SecurityRequirement(name = "bearerAuth") // Apply JWT security to all endpoints in this controller
public class RiderPostMapping {

    private final RiderService riderService;

    public RiderPostMapping(RiderService riderService, RatingManagementService ratingManagementService) {
        this.riderService = riderService;
    }

    @PostMapping("/requestRide")
    @Operation(summary = "Request a new ride",
            description = "Submits a new ride request with pickup and drop-off locations.")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        RideRequestDto requestedRide = riderService.requestRide(rideRequestDto);
        return ResponseEntity.ok(requestedRide);
    }

    @PostMapping("/cancelRide/{rideId}")
    @Operation(summary = "Cancel an ongoing ride",
            description = "Allows a rider to cancel their ongoing ride.")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver/{rideId}")
    @Operation(summary = "Rate a driver",
            description = "Allows a rider to submit a rating for a completed ride's driver.")
    public ResponseEntity<DriverDto> rateDriver(@PathVariable Long rideId, @RequestBody RatingDto ratingDto) {
        return ResponseEntity.ok(riderService.rateDriver(rideId, ratingDto.getRating()));
    }
}
