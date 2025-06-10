package com.rOushAn.cabcore.controllers.admin;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.OnboardDriverDto;
import com.rOushAn.cabcore.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    private final AuthService authService;

    public AdminController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/onboardDriver/{userId}")
    public ResponseEntity<DriverDto> onboardDriver(@PathVariable Long userId, @RequestBody OnboardDriverDto onboardDriverDto) {
        DriverDto driver = authService.onboardNewDriver(userId, onboardDriverDto);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }
}
