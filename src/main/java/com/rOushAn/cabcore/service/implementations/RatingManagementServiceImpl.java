package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.dtos.DriverDto;
import com.rOushAn.cabcore.dtos.RiderDto;
import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.Rating;
import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.Rider;
import com.rOushAn.cabcore.exceptions.ResourceNotFoundException;
import com.rOushAn.cabcore.exceptions.RuntimeConflictException;
import com.rOushAn.cabcore.repositories.DriverRepository;
import com.rOushAn.cabcore.repositories.RatingRepository;
import com.rOushAn.cabcore.repositories.RiderRepository;
import com.rOushAn.cabcore.service.RatingManagementService;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class RatingManagementServiceImpl implements RatingManagementService {


    private static final Logger log = Logger.getLogger(RatingManagementServiceImpl.class);
    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;

    public RatingManagementServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper, DriverRepository driverRepository, RiderRepository riderRepository) {
        this.ratingRepository = ratingRepository;
        this.driverRepository = driverRepository;
        this.riderRepository = riderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DriverDto rateDriver(Ride ride, Driver driver, Double rating) {
        Rating ratingObj = ratingRepository
                .findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found!"));
        log.info(ratingObj.getDriverRating());

        if (ratingObj.getDriverRating() != 0.0) throw new RuntimeConflictException("Cannot rate driver again!");

        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository
                .findByDriver(driver)
                .stream()
                .mapToDouble(rating1 -> rating1.getDriverRating())
                .average()
                .orElse(0.0);
        driver.setRating(newRating);
        Driver driverSaved = driverRepository.save(driver);
        return modelMapper.map(driverSaved, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Rider rider, Double rating) {

        Rating ratingObj = ratingRepository
                .findByRide(ride)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found!"));

        if (ratingObj.getRiderRating() != 0.0) throw new RuntimeConflictException("Cannot rate rider again!");

        ratingObj.setRiderRating(rating);
        ratingRepository.save(ratingObj);

        Double newRating = ratingRepository
                .findByRider(rider)
                .stream()
                .mapToDouble(rating1 -> rating1.getRiderRating())
                .average()
                .orElse(0.0);
        rider.setRating(newRating);
        Rider savedRider = riderRepository.save(rider);
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating = new Rating
                .RatingBuilder()
                .ride(ride)
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .driverRating(0.0)
                .riderRating(0.0)
                .build();
        ratingRepository.save(rating);
    }
}