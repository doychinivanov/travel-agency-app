package com.travelapp.service.impl;

import com.travelapp.models.Country;
import com.travelapp.repositories.CountryRepository;
import com.travelapp.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
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
}
