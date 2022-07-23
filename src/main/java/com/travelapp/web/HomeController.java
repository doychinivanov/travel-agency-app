package com.travelapp.web;

import com.travelapp.models.dto.CountryNameDTO;
import com.travelapp.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private CountryService countryService;

    public HomeController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<CountryNameDTO> allCountryNames = countryService.getAllCountryNames();

        model.addAttribute("countries", allCountryNames);

        return "index";
    }
}
