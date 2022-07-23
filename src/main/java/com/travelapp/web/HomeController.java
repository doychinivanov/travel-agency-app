package com.travelapp.web;

import com.travelapp.models.dto.CountryNameDTO;
import com.travelapp.service.CountryService;
import com.travelapp.service.TripService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        model.addAttribute("countries", allCountryNames);

        if (country != null) {
            this.tripService.getTripsForCountry(country).forEach(System.out::println);
        }

        return "index";
    }
}
