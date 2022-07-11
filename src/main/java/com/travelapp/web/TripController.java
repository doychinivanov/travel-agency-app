package com.travelapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trip")
public class TripController {

    @GetMapping("/create")
    public String createTripForm() {
        return "create-trip";
    }
}
