package com.travelapp.web;

import com.travelapp.models.AuthUser;
import com.travelapp.models.dto.TripCardDTO;
import com.travelapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final BigDecimal TARGET_GOAL = BigDecimal.valueOf(10000);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/bookings")
    public String getUserBookings(@AuthenticationPrincipal AuthUser currentAuthUser, Model model) {
        List<TripCardDTO> userBookings = this.userService.getUserBookings(currentAuthUser.getId());
        BigDecimal totalAmountUserSpentOnThePlatform = this.userService.getTotalAmountUserSpentOnThePlatform(currentAuthUser.getId());
        BigDecimal percentageCompleted = totalAmountUserSpentOnThePlatform.divide(TARGET_GOAL).multiply(BigDecimal.valueOf(100)).setScale(0, RoundingMode.FLOOR);

        model.addAttribute("userName", currentAuthUser.getFullName());
        model.addAttribute("userBookings", userBookings);
        model.addAttribute("totalAmountSpent", totalAmountUserSpentOnThePlatform);
        model.addAttribute("percentageCompleted", percentageCompleted.compareTo(BigDecimal.valueOf(100)) < 1 ? percentageCompleted : 100);

        return "my-trips";
    }
}
