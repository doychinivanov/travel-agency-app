package com.travelapp.service;

import com.travelapp.models.Country;
import com.travelapp.models.Trip;
import com.travelapp.models.dto.CreateTripDTO;
import com.travelapp.models.dto.EditTripDTO;
import com.travelapp.models.dto.TripCardDTO;
import com.travelapp.models.dto.TripDetailsDTO;

import java.util.List;

public interface TripService {

    void createTrip(CreateTripDTO createTripDTO, Country country) throws Exception;

    void updateTrip(EditTripDTO editTripDTO, Country newCountry) throws Exception;

    List<TripCardDTO> getTripsForCountry(String countryName);

    TripDetailsDTO getTripById(long id) throws Exception;

    EditTripDTO getEditInfo(long id) throws Exception;

    void deleteTrip(long id) throws Exception;

    List<TripCardDTO> getMostBookedTrips(int limit);

    List<TripCardDTO> getRandomTrips(int limit);
}
