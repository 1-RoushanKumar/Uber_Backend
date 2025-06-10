package com.rOushAn.cabcore.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.locationtech.jts.geom.Point;

import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "idx_driver_vehicle_id", columnList = "current_vehicle_id"),
        @Index(name = "idx_driver_user", columnList = "user_id")
})
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    @NotNull(message = "User must be associated with the driver")
    private User user;

    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating cannot be less than 0")
    @Max(value = 5, message = "Rating cannot be more than 5")
    private Double rating;

    @NotNull(message = "Availability status is required")
    private Boolean available;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point currentLocation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_vehicle_id")
    private Vehicle currentVehicle;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> registeredVehicles;

    public Driver() {
    }

    public Driver(DriverBuilder driverBuilder) {
        this.id = driverBuilder.id;
        this.user = driverBuilder.user;
        this.rating = driverBuilder.rating;
        this.available = driverBuilder.available;
        this.currentLocation = driverBuilder.currentLocation;
        this.currentVehicle = driverBuilder.currentVehicle;
        this.registeredVehicles = driverBuilder.registeredVehicles;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Point getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }

    public List<Vehicle> getRegisteredVehicles() {
        return registeredVehicles;
    }

    public void setRegisteredVehicles(List<Vehicle> registeredVehicles) {
        this.registeredVehicles = registeredVehicles;
    }

    public static class DriverBuilder {

        private Long id;
        private User user;
        private Double rating;
        private Boolean available;
        private Point currentLocation;
        private Vehicle currentVehicle;
        private List<Vehicle> registeredVehicles;

        public DriverBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DriverBuilder user(User user) {
            this.user = user;
            return this;
        }

        public DriverBuilder rating(Double rating) {
            this.rating = rating;
            return this;
        }

        public DriverBuilder available(Boolean available) {
            this.available = available;
            return this;
        }

        public DriverBuilder currentLocation(Point currentLocation) {
            this.currentLocation = currentLocation;
            return this;
        }

        public DriverBuilder currentVehicle(Vehicle currentVehicle) {
            this.currentVehicle = currentVehicle;
            return this;
        }

        public DriverBuilder registeredVehicles(List<Vehicle> registeredVehicles) {
            this.registeredVehicles = registeredVehicles;
            return this;
        }


        public Driver build() {
            return new Driver(this);
        }
    }
}