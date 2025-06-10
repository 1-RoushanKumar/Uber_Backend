package com.rOushAn.cabcore.controllers.driver;

import com.rOushAn.cabcore.dtos.AddVehicleDto;
import com.rOushAn.cabcore.dtos.VehicleDto;
import com.rOushAn.cabcore.service.DriverService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver/vehicles")
@Secured("ROLE_DRIVER")
@Tag(name = "Driver - Vehicle Management", description = "Endpoints for drivers to manage their registered vehicles.")
public class DriverVehicleController {

    private final DriverService driverService;

    public DriverVehicleController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    @Operation(summary = "Add a new vehicle",
            description = "Registers a new vehicle to the authenticated driver's profile.")
    public ResponseEntity<VehicleDto> addVehicle(@Valid @RequestBody AddVehicleDto addVehicleDto) {
        VehicleDto newVehicle = driverService.addVehicle(addVehicleDto);
        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    @PutMapping("/{vehicleId}")
    @Operation(summary = "Update vehicle details",
            description = "Updates the details of a specific vehicle owned by the authenticated driver.")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable Long vehicleId, @Valid @RequestBody AddVehicleDto updateVehicleDto) {
        VehicleDto updatedVehicle = driverService.updateVehicle(vehicleId, updateVehicleDto);
        return ResponseEntity.ok(updatedVehicle);
    }

    @DeleteMapping("/{vehicleId}")
    @Operation(summary = "Remove a vehicle",
            description = "Removes a vehicle from the authenticated driver's profile. An active vehicle cannot be removed.")
    public ResponseEntity<Void> removeVehicle(@PathVariable Long vehicleId) {
        driverService.removeVehicle(vehicleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all driver's vehicles",
            description = "Retrieves a list of all vehicles registered to the authenticated driver.")
    public ResponseEntity<List<VehicleDto>> getMyVehicles() {
        List<VehicleDto> vehicles = driverService.getMyVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @PutMapping("/selectActive/{vehicleId}")
    @Operation(summary = "Set active vehicle",
            description = "Sets one of the driver's registered vehicles as their currently active vehicle.")
    public ResponseEntity<VehicleDto> selectActiveVehicle(@PathVariable Long vehicleId) {
        VehicleDto activeVehicle = driverService.selectActiveVehicle(vehicleId);
        return ResponseEntity.ok(activeVehicle);
    }

    @GetMapping("/active")
    @Operation(summary = "Get current active vehicle",
            description = "Retrieves details of the authenticated driver's currently active vehicle.")
    public ResponseEntity<VehicleDto> getCurrentActiveVehicle() {
        VehicleDto activeVehicle = driverService.getCurrentActiveVehicle();
        if (activeVehicle == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(activeVehicle);
    }

    @PutMapping("/deactivate/{vehicleId}")
    @Operation(summary = "Deactivate a vehicle",
            description = "Deactivates a vehicle, making it unavailable for rides. If it was the active vehicle, the driver will no longer have an active vehicle.")
    public ResponseEntity<VehicleDto> deactivateVehicle(@PathVariable Long vehicleId) {
        VehicleDto deactivatedVehicle = driverService.deactivateVehicle(vehicleId);
        return ResponseEntity.ok(deactivatedVehicle);
    }
}