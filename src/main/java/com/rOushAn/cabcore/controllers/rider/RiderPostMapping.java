package com.rOushAn.cabcore.controllers.rider;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.RatingDto;
import com.rOushAn.cabcore.dtos.RideDto;
import com.rOushAn.cabcore.dtos.RideRequestDto;
import com.rOushAn.cabcore.service.RatingManagementService;
import com.rOushAn.cabcore.service.RiderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
@Secured("ROLE_RIDER")
public class RiderPostMapping {

    private final RiderService riderService;

    public RiderPostMapping(RiderService riderService, RatingManagementService ratingManagementService) {
        this.riderService = riderService;
    }

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto) {
        RideRequestDto requestedRide = riderService.requestRide(rideRequestDto);
        return ResponseEntity.ok(requestedRide);
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/rateDriver/{rideId}")
    public ResponseEntity<DriverDto> rateDriver(@PathVariable Long rideId, @RequestBody RatingDto ratingDto) {
        return ResponseEntity.ok(riderService.rateDriver(rideId, ratingDto.getRating()));
    }
}
