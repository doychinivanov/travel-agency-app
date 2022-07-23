package com.travelapp.web;

import com.travelapp.models.dto.CreateTripDTO;
import com.travelapp.service.S3Service;
import com.travelapp.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/trip")
public class TripController {
    private TripService tripService;
    private S3Service s3Service;

    @Autowired
    public TripController(TripService tripService, S3Service s3Service) {
        this.tripService = tripService;
        this.s3Service = s3Service;
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
    public String createTrip(@RequestParam("image") MultipartFile file,
                             @Valid CreateTripDTO createTripDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || file.isEmpty()) {
            redirectAttributes.addFlashAttribute("createTripDto", createTripDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createTripDto", bindingResult);
            redirectAttributes.addFlashAttribute("imageErr", "Image is required");

            return "redirect:/trip/create";
        }

        try {
            s3Service.uploadFile(file);
            this.tripService.createTrip(createTripDTO);
        } catch (Exception err) {
            redirectAttributes.addFlashAttribute("failedToCreate", err.getMessage());
            return "redirect:/trip/create";
        }
        return "redirect:/";
    }
}
