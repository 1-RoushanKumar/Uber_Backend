package com.rOushAn.cabcore.controllers.admin;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.OnboardDriverDto;
import com.rOushAn.cabcore.service.AuthService;
import com.rOushAn.cabcore.service.DriverService;
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
    public ResponseEntity<List<DriverDto>> getAllDrivers() {
        return ResponseEntity.ok(driverService.getAllDrivers());
    }

}
