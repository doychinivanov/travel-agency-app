package com.travelapp.service;

import com.travelapp.models.Country;
import com.travelapp.models.dto.CreateTripDTO;

public interface TripService {

    void createTrip(CreateTripDTO createTripDTO, Country country) throws Exception;
}
