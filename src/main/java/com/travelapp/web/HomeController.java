package com.travelapp.web;

import com.travelapp.models.dto.CountryNameDTO;
import com.travelapp.models.dto.TripCardDTO;
import com.travelapp.service.CountryService;
import com.travelapp.service.TripService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private CountryService countryService;
    private TripService tripService;

    public HomeController(CountryService countryService, TripService tripService) {
        this.countryService = countryService;
        this.tripService = tripService;
    }

    @GetMapping("/")
    public String home(Model model, @Param("country") String country) {
        List<CountryNameDTO> allCountryNames = countryService.getAllCountryNames();
        List<TripCardDTO> mostBookedTrips = tripService.getMostBookedTrips(6);
        List<TripCardDTO> randomTrips = this.tripService.getRandomTrips(6);
        boolean noEnoughTripsInDb = mostBookedTrips.isEmpty() || mostBookedTrips.size() < 3 || randomTrips.isEmpty() || randomTrips.size() < 3;

        model.addAttribute("countries", allCountryNames);

        if (noEnoughTripsInDb) {
            model.addAttribute("noEnoughTripsInDb", true);
        } else {
            model.addAttribute("noEnoughTripsInDb", false);
            model.addAttribute("mostBooked1", mostBookedTrips.subList(0, 3));
            model.addAttribute("mostBooked2", mostBookedTrips.subList(3, mostBookedTrips.size()));
            model.addAttribute("randomTrips1", randomTrips.subList(0, 3));
            model.addAttribute("randomTrips2", randomTrips.subList(3, randomTrips.size()));
        }

        if (country != null) {
            List<TripCardDTO> foundResults = this.tripService.getTripsForCountry(country);
            model.addAttribute("results", foundResults);
            model.addAttribute("searchResult", country);
            return "home";
        }

        return "index";
    }
}
