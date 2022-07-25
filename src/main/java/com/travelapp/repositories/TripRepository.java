package com.travelapp.repositories;

import com.travelapp.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByCountryName(String countryName);

    Optional<Trip> findById(long id);
}
