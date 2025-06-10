package com.rOushAn.cabcore.controllers.admin;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.OnboardDriverDto;
import com.rOushAn.cabcore.dtos.UserDto;
import com.rOushAn.cabcore.service.AuthService;
import com.rOushAn.cabcore.service.DriverService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    private final AuthService authService;
    private final DriverService driverService;

    public AdminController(AuthService authService, DriverService driverService) {
        this.authService = authService;
        this.driverService = driverService;
    }

    @PostMapping(path = "/onboardDriver/{userId}")
    public ResponseEntity<DriverDto> onboardDriver(@PathVariable Long userId, @RequestBody OnboardDriverDto onboardDriverDto) {
        DriverDto driver = authService.onboardNewDriver(userId, onboardDriverDto);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @GetMapping("/drivers")
    public ResponseEntity<Page<DriverDto>> getAllDrivers(Pageable pageable) {
        return ResponseEntity.ok(driverService.getAllDrivers(pageable));
    }

    @GetMapping("/drivers/{driverId}")
    public ResponseEntity<DriverDto> getDriverDetails(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.getDriverDetails(driverId));
    }

    @GetMapping("/users")
    public ResponseEntity<Page<UserDto>> getAllUsers(Pageable pageable) {
        return ResponseEntity.ok(authService.getAllUsers(pageable));
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deactivateUser(@PathVariable Long userId) {
        authService.deactivateUser(userId);
        return ResponseEntity.ok().build();
    }

}
