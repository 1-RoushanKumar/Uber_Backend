package com.rOushAn.cabcore.controllers.driver;

import com.rOushAn.cabcore.dtos.AddVehicleDto;
import com.rOushAn.cabcore.dtos.VehicleDto;
import com.rOushAn.cabcore.service.DriverService;
import jakarta.validation.Valid; // Import for @Valid
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver/vehicles")
@Secured("ROLE_DRIVER")
public class DriverVehicleController {

    private final DriverService driverService;

    public DriverVehicleController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<VehicleDto> addVehicle(@Valid @RequestBody AddVehicleDto addVehicleDto) {
        VehicleDto newVehicle = driverService.addVehicle(addVehicleDto);
        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable Long vehicleId, @Valid @RequestBody AddVehicleDto updateVehicleDto) {
        VehicleDto updatedVehicle = driverService.updateVehicle(vehicleId, updateVehicleDto);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> removeVehicle(@PathVariable Long vehicleId) {
        driverService.removeVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<VehicleDto>> getMyVehicles() {
        List<VehicleDto> vehicles = driverService.getMyVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/selectActive/{vehicleId}")
    public ResponseEntity<VehicleDto> selectActiveVehicle(@PathVariable Long vehicleId) {
        VehicleDto activeVehicle = driverService.selectActiveVehicle(vehicleId);
        return ResponseEntity.ok(activeVehicle);
    }

    @GetMapping("/active")
    public ResponseEntity<VehicleDto> getCurrentActiveVehicle() {
        VehicleDto activeVehicle = driverService.getCurrentActiveVehicle();
        if (activeVehicle == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(activeVehicle);
    }

    @PutMapping("/deactivate/{vehicleId}")
    public ResponseEntity<VehicleDto> deactivateVehicle(@PathVariable Long vehicleId) {
        VehicleDto deactivatedVehicle = driverService.deactivateVehicle(vehicleId);
        return ResponseEntity.ok(deactivatedVehicle);
    }
}