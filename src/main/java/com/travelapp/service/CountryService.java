package com.travelapp.service;

import com.travelapp.models.Country;
import com.travelapp.models.dto.CountryNameDTO;

import java.util.List;

public interface CountryService {

    Country createCountry(String countryName);

    List<CountryNameDTO> getAllCountryNames();
}
