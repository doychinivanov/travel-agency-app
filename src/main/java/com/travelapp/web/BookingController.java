package com.travelapp.web;

import com.travelapp.models.AuthUser;
import com.travelapp.models.dto.BookingCreateDTO;
import com.travelapp.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/booking")
public class BookingController {

    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);

    @Value("${stripe.test.public.key}")
    private String stripePublicKey;
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
            long bookingId = this.bookingService.bookTrip(bookingCreateDTO, tripId, currentAuthUser.getId());
            return "redirect:/booking/payment/" + bookingId;
        } catch (Exception err) {
            System.out.println(err.getMessage());
            return "redirect:/booking/trip/" + tripId;
        }
    }

    @GetMapping("/payment/{bookingId}")
    public String getStripeForm(@PathVariable long bookingId, Model model) throws Exception {
        try {
            BigDecimal priceForBooking = this.bookingService.getPriceForBooking(bookingId);
            model.addAttribute("stripePublicKey", stripePublicKey);
            model.addAttribute("priceForBooking", priceForBooking);
            return "stripe-form";
        } catch (Exception err) {
            logger.error(err.getMessage());
            return "redirect:/";
        }
    }
}
