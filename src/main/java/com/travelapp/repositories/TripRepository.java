package com.travelapp.repositories;

import com.travelapp.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByCountryName(String countryName);

    Optional<Trip> findById(long id);

    @Query("SELECT t FROM Trip t" +
            " ORDER BY t.bookings.size DESC")
    List<Trip> getMostBookedTrips(Pageable page);
}
