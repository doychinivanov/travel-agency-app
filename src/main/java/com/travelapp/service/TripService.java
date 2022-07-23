package com.travelapp.service;

import com.travelapp.models.Country;
import com.travelapp.models.Trip;
import com.travelapp.models.dto.CreateTripDTO;
import com.travelapp.models.dto.TripCardDTO;

import java.util.List;

public interface TripService {

    void createTrip(CreateTripDTO createTripDTO, Country country) throws Exception;

    List<TripCardDTO> getTripsForCountry(String countryName);
}
