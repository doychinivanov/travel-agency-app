package com.travelapp.service.impl;

import com.travelapp.models.Trip;
import com.travelapp.models.dto.CreateTripDTO;
import com.travelapp.repositories.TripRepository;
import com.travelapp.service.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripServiceImpl implements TripService {
    private TripRepository tripRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository, ModelMapper modelMapper) {
        this.tripRepository = tripRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createTrip(CreateTripDTO createTripDTO) throws Exception {
        Trip newTrip = this.modelMapper.map(createTripDTO, Trip.class);
        this.tripRepository.save(newTrip);
    }
}
