package com.rOushAn.cabcore.repositories;

import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.Rating;
import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByRider(Rider rider);

    List<Rating> findByDriver(Driver driver);

    Optional<Rating> findByRide(Ride ride);
}
