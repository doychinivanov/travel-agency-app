package com.travelapp.web;

import com.travelapp.models.AuthUser;
import com.travelapp.models.dto.BookingCreateDTO;
import com.travelapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @ModelAttribute("bookingCreateDTO")
    public BookingCreateDTO initBookingCreateDTO() {
        return new BookingCreateDTO();
    }

    @GetMapping("/trip/{tripId}")
    public String getBookingFor(@PathVariable long tripId, Model model) {
        model.addAttribute("tripId", tripId);
        return "booking-form";
    }

    @PostMapping("/trip/{tripId}")
    public String createBooking(@PathVariable long tripId,
                                @AuthenticationPrincipal AuthUser currentAuthUser,
                                @Valid BookingCreateDTO bookingCreateDTO,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bookingCreateDTO", bookingCreateDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.bookingCreateDTO", bindingResult);

            return "redirect:/booking/trip/" + tripId;
        }

        try {
            this.bookingService.bookTrip(bookingCreateDTO, tripId, currentAuthUser.getId());
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

//        redirect to my tips
        return "redirect:/";
    }
}
