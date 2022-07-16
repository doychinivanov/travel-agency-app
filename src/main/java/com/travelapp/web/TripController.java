package com.travelapp.web;

import com.travelapp.models.dto.CreateTripDTO;
import com.travelapp.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/trip")
public class TripController {
    private TripService tripService;

    @Autowired
    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @ModelAttribute("createTripDto")
    public CreateTripDTO initCreateTripDto() {
        return new CreateTripDTO();
    }

    @GetMapping("/create")
    public String createTripForm() {
        return "create-trip";
    }

    @PostMapping("/create")
    public String createTrip(@Valid CreateTripDTO createTripDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createTripDto", createTripDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createTripDto", bindingResult);

            return "redirect:/trip/create";
        }

        try {
            this.tripService.createTrip(createTripDTO);
        } catch (Exception err) {
            redirectAttributes.addFlashAttribute("failedToCreate", err.getMessage());
            return "redirect:/trip/create";
        }
        return "redirect:/";
    }
}
