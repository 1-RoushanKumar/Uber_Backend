package com.rOushAn.cabcore.dtos;

import com.rOushAn.cabcore.entities.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddVehicleDto {
    @NotBlank(message = "Make is required")
    private String make;

    @NotBlank(message = "Model is required")
    private String model;

    @NotBlank(message = "License plate is required")
    @Size(min = 4, max = 20, message = "License plate must be between 4 and 20 characters")
    private String licensePlate;

    @NotBlank(message = "Color is required")
    private String color;

    @NotNull(message = "Vehicle type is required")
    private VehicleType vehicleType;

    public AddVehicleDto() {
    }

    public AddVehicleDto(String make, String model, String licensePlate, String color, VehicleType vehicleType) {
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.color = color;
        this.vehicleType = vehicleType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}