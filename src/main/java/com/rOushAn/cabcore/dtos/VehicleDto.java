package com.rOushAn.cabcore.dtos;

import com.rOushAn.cabcore.entities.enums.VehicleType;

public class VehicleDto {
    private Long id;
    private String make;
    private String model;
    private String licensePlate;
    private String color;
    private VehicleType vehicleType;
    private Boolean isActive;

    public VehicleDto() {
    }

    public VehicleDto(Long id, String make, String model, String licensePlate, String color, VehicleType vehicleType, Boolean isActive) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.color = color;
        this.vehicleType = vehicleType;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}