package com.rOushAn.cabcore.controllers.admin;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.OnboardDriverDto;
import com.rOushAn.cabcore.dtos.RideDto;
import com.rOushAn.cabcore.dtos.UserDto;
import com.rOushAn.cabcore.service.AuthService;
import com.rOushAn.cabcore.service.DriverService;
import com.rOushAn.cabcore.service.RideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
@Tag(name = "Admin Controller", description = "Operations accessible only by administrators") // Tag for the controller
public class AdminController {

    private final AuthService authService;
    private final DriverService driverService;
    private final RideService rideService;

    public AdminController(AuthService authService, DriverService driverService, RideService rideService) {
        this.authService = authService;
        this.driverService = driverService;
        this.rideService = rideService;
    }

    @PostMapping(path = "/onboardDriver/{userId}")
    @Operation(summary = "Onboard a new driver",
            description = "Allows an admin to onboard a registered user as a new driver, linking them to a Driver profile.")
    public ResponseEntity<DriverDto> onboardDriver(@PathVariable Long userId, @RequestBody OnboardDriverDto onboardDriverDto) {
        DriverDto driver = authService.onboardNewDriver(userId, onboardDriverDto);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @GetMapping("/drivers")
    @Operation(summary = "Get all drivers",
            description = "Retrieves a paginated list of all registered drivers.")
    public ResponseEntity<Page<DriverDto>> getAllDrivers(Pageable pageable) {
        return ResponseEntity.ok(driverService.getAllDrivers(pageable));
    }

    @GetMapping("/drivers/{driverId}")
    @Operation(summary = "Get driver details by ID",
            description = "Retrieves detailed information for a specific driver.")
    public ResponseEntity<DriverDto> getDriverDetails(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.getDriverDetails(driverId));
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users",
            description = "Retrieves a paginated list of all registered users (riders and drivers).")
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(authService.getAllUsers(pageable));
    }

    @DeleteMapping("/users/{userId}")
    @Operation(summary = "Deactivate a user",
            description = "Deactivates a user account, preventing further login. This operation cannot be undone.")
    public ResponseEntity<?> deactivateUser(@PathVariable Long userId) {
        authService.deactivateUser(userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rides")
    @Operation(summary = "Get all rides",
            description = "Retrieves a paginated list of all rides in the system, regardless of status or user.")
    public ResponseEntity<Page<RideDto>> getAllRides(Pageable pageable) {
        return ResponseEntity.ok(rideService.getAllRides(pageable));
    }
}