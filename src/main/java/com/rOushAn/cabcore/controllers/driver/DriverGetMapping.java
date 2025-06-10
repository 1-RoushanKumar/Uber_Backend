package com.rOushAn.cabcore.controllers.driver;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.DriverRideDto;
import com.rOushAn.cabcore.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/driver")
@Secured("ROLE_DRIVER")
@Tag(name = "Driver - Profile & Rides", description = "Endpoints for drivers to manage their profile and view ride history.") // Tag for the controller
@SecurityRequirement(name = "bearerAuth") // Apply JWT security to all endpoints in this controller
public class DriverGetMapping {

    private final DriverService driverService;
    private final int PAGE_SIZE = 4;

    public DriverGetMapping(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/getMyProfile")
    @Operation(summary = "Get driver's own profile",
            description = "Retrieves the detailed profile information for the authenticated driver.")
    public ResponseEntity<DriverDto> getDriverProfile() {
        return ResponseEntity.ok(driverService.getDriverProfile());
    }

    @GetMapping("/getMyRides")
    @Operation(summary = "Get driver's ride history",
            description = "Retrieves a paginated list of all rides driven by the authenticated driver.")
    public ResponseEntity<List<DriverRideDto>> getAllMyRides(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "1") Integer pageNumber) {
        Pageable pageRequest = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy).ascending());
        Page<DriverRideDto> rides = driverService.getAllMyRides(pageRequest);
        return ResponseEntity.ok(rides.getContent());
    }
}