package com.travelapp.service.impl;

import com.travelapp.models.Country;
import com.travelapp.models.Trip;
import com.travelapp.models.dto.CountryNameDTO;
import com.travelapp.repositories.CountryRepository;
import com.travelapp.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    private boolean countryExists(String countryName) {
        Optional<Country> potentialCountry = this.countryRepository.findByName(countryName);

        return potentialCountry.isPresent();
    }

    @Override
    public Country createCountry(String countryName) {
        boolean hasSuchCountry = countryExists(countryName);

        if (!hasSuchCountry) {
            Country newCountry = new Country(countryName);
            this.countryRepository.save(newCountry);
            return newCountry;
        } else {
            Optional<Country> existingCountry = this.countryRepository.findByName(countryName);
            return existingCountry.get();
        }

    }

    @Override
    public List<CountryNameDTO> getAllCountryNames() {
        List<Country> allCountries = this.countryRepository.findAll();
        return Arrays.stream(this.modelMapper.map(allCountries, CountryNameDTO[].class)).toList();
    }

    @Override
    public void deleteCountryIfNoTrips(Country country) {
        if (country.getTrips().isEmpty()){
            this.countryRepository.delete(country);
        }
    }
}
