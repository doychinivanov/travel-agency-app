package com.travelapp.service.impl;

import com.travelapp.models.Country;
import com.travelapp.models.Trip;
import com.travelapp.models.dto.CreateTripDTO;
import com.travelapp.models.dto.EditTripDTO;
import com.travelapp.models.dto.TripCardDTO;
import com.travelapp.models.dto.TripDetailsDTO;
import com.travelapp.repositories.TripRepository;
import com.travelapp.service.TripService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public void createTrip(CreateTripDTO createTripDTO, Country country) throws Exception {
        Trip newTrip = this.modelMapper.map(createTripDTO, Trip.class);

        if (country != null) newTrip.setCountry(country);

        this.tripRepository.save(newTrip);
    }

    @Override
    public void updateTrip(EditTripDTO editTripDTO, Country newCountry) throws Exception {
        Trip updatedTrip = getTripEntity(editTripDTO.getId());

        updatedTrip.setDestination(editTripDTO.getDestination());
        updatedTrip.setCountry(newCountry);
        updatedTrip.setHighlight(editTripDTO.getHighlight());
        updatedTrip.setPrice(editTripDTO.getPrice());
        updatedTrip.setDays(editTripDTO.getDays());
        updatedTrip.setDescription(editTripDTO.getDescription());

        if (editTripDTO.getImg() != null) {
            updatedTrip.setImg(editTripDTO.getImg());
        }

        this.tripRepository.save(updatedTrip);
    }

    @Override
    public List<TripCardDTO> getTripsForCountry(String countryName) {
        List<Trip> tripsPerCountry = this.tripRepository.findByCountryName(countryName);
        return Arrays.stream(this.modelMapper.map(tripsPerCountry, TripCardDTO[].class)).toList();
    }

    @Override
    public TripDetailsDTO getTripById(long id) throws Exception {
        Trip trip = getTripEntity(id);
        return this.modelMapper.map(trip, TripDetailsDTO.class);
    }

    @Override
    public EditTripDTO getEditInfo(long id) throws Exception {
        Trip trip = getTripEntity(id);
        return this.modelMapper.map(trip, EditTripDTO.class);
    }

    private Trip getTripEntity(long id) throws Exception {
        Optional<Trip> potentialTrip = this.tripRepository.findById(id);

        if (potentialTrip.isEmpty()) {
            throw new Exception("No such trip");
        }

        return potentialTrip.get();
    }
}
