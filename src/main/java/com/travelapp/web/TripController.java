package com.travelapp.web;

import com.travelapp.models.Country;
import com.travelapp.models.dto.CreateTripDTO;
import com.travelapp.models.dto.EditTripDTO;
import com.travelapp.models.dto.TripDetailsDTO;
import com.travelapp.service.CountryService;
import com.travelapp.service.S3Service;
import com.travelapp.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private CountryService countryService;

    @Autowired
    public TripController(TripService tripService, S3Service s3Service, CountryService countryService) {
        this.tripService = tripService;
        this.s3Service = s3Service;
        this.countryService = countryService;
    }

    @ModelAttribute("createTripDto")
    public CreateTripDTO initCreateTripDto() {
        return new CreateTripDTO();
    }

    @ModelAttribute("editTripDto")
    public EditTripDTO initEditTripDto() {
        return new EditTripDTO();
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
            String imgName = this.s3Service.uploadFile(file);
            createTripDTO.setImg(imgName);
            Country country = this.countryService.createCountry(createTripDTO.getCountry());
            this.tripService.createTrip(createTripDTO, country);
        } catch (Exception err) {
            redirectAttributes.addFlashAttribute("failedToCreate", err.getMessage());
            return "redirect:/trip/create";
        }
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String getInfoForTrip(@PathVariable long id, Model model) {

        try {
            TripDetailsDTO trip = this.tripService.getTripById(id);
            model.addAttribute("tripDetails", trip);
            return "trip-details";
        } catch (Exception err) {
            System.out.println(err.getMessage());
            //            Should redirect to error message
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{id}")
    public String prepareEditInfo(@PathVariable long id, Model model) {

        try {
            EditTripDTO trip = this.tripService.getEditInfo(id);
            model.addAttribute("editTripDto", trip);
            return "edit-trip";
        } catch (Exception err) {
            System.out.println(err.getMessage());
//            Should redirect to error message
            return "redirect:/";
        }
    }

    @PostMapping("/edit/{id}")
    public String editTrip(@PathVariable long id,
                           @RequestParam("image") MultipartFile file,
                           @Valid EditTripDTO editTripDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("editTripDTO", editTripDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editTripDTO", bindingResult);

            return "redirect:/trip/edit/" + id;
        }

        try {
            if (!file.isEmpty()) {
                String imgName = this.s3Service.uploadFile(file);
                editTripDTO.setImg(imgName);
            }
            Country country = this.countryService.createCountry(editTripDTO.getCountryName());
            this.tripService.updateTrip(editTripDTO, country);
            return "redirect:/trip/" + id;
        } catch (Exception err) {
            System.out.println(err.getMessage());
//            Should redirect to error message
            return "redirect:/";
        }
    }
    @GetMapping("/delete/{id}")
    public String deleteTrip(@PathVariable long id) {

        try{
            this.tripService.deleteTrip(id);
            return "redirect:/";
        } catch (Exception err) {
            System.out.println(err.getMessage());
//                  add err message
            return "redirect:/trip/" + id;
        }

    }
}
