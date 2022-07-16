package com.travelapp.service;

import com.travelapp.models.dto.CreateTripDTO;

public interface TripService {

    void createTrip(CreateTripDTO createTripDTO) throws Exception;
}
