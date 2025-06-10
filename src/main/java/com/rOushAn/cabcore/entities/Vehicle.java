package com.rOushAn.cabcore.entities;

import com.rOushAn.cabcore.entities.enums.VehicleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(indexes = {
        @Index(name = "idx_vehicle_driver", columnList = "driver_id"),
        @Index(name = "idx_vehicle_license_plate", columnList = "licensePlate", unique = true)
})
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    @NotNull(message = "Vehicle must be associated with a driver")
    private Driver driver;

    @NotBlank(message = "Make is required")
    @Size(max = 50, message = "Make cannot exceed 50 characters")
    private String make;

    @NotBlank(message = "Model is required")
    @Size(max = 50, message = "Model cannot exceed 50 characters")
    private String model;

    @NotBlank(message = "License plate is required")
    @Size(min = 4, max = 20, message = "License plate must be between 4 and 20 characters")
    @Column(unique = true)
    private String licensePlate;

    @NotBlank(message = "Color is required")
    @Size(max = 30, message = "Color cannot exceed 30 characters")
    private String color;

    @NotNull(message = "Vehicle type is required")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @NotNull(message = "Active status is required")
    private Boolean isActive;

    public Vehicle() {
    }

   public Vehicle(Long id, Driver driver, String make, String model, String licensePlate, String color, VehicleType vehicleType, Boolean isActive) {
        this.id = id;
        this.driver = driver;
        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.color = color;
        this.vehicleType = vehicleType;
        this.isActive = isActive;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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