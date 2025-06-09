package com.rOushAn.cabcore.repositories;

import com.rOushAn.cabcore.entities.Driver;
import com.rOushAn.cabcore.entities.Ride;
import com.rOushAn.cabcore.entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    Page<Ride> findByRider(Rider rider, Pageable pageRequest);

    Page<Ride> findByDriver(Driver driver, Pageable pageRequest);
}
