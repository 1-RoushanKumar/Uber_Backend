package com.rOushAn.cabcore.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class OnboardDriverDto {

    @NotNull(message = "Current location is required for driver onboarding")
    private PointDto currentLocation;

    @Valid
    @NotNull(message = "Vehicle details are required for driver onboarding")
    private AddVehicleDto vehicleDetails;

    public OnboardDriverDto() {
    }

    public OnboardDriverDto(PointDto currentLocation, AddVehicleDto vehicleDetails) {
        this.currentLocation = currentLocation;
        this.vehicleDetails = vehicleDetails;
    }

    public PointDto getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(PointDto currentLocation) {
        this.currentLocation = currentLocation;
    }

    public AddVehicleDto getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(AddVehicleDto vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }
}